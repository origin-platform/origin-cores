package com.jyusun.origin.base.logger.exception;


import com.jyusun.origin.base.logger.common.util.LoggerUtil;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.exception.SecureException;
import com.jyusun.origin.core.common.exception.ServiceException;
import com.jyusun.origin.core.common.exception.UtilException;
import com.jyusun.origin.core.common.result.AbstractResult;
import com.jyusun.origin.core.common.result.Links;
import com.jyusun.origin.core.common.result.ResultFactory;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UriUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 全局的的异常拦截器
 * <p>
 * 作用描述：处理可预见的异常问题，只有在Web环境下拦截生效 {@link ConditionalOnWebApplication}
 *
 * @author jyusun at 2021-7-3 12:40:19
 * @since 1.0.0
 */
@Slf4j
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * HttpServletRequest 中 获取请求链接
     *
     * @return {@link Links} 链接信息
     */
    private static Links links() {
        String self = Optional.ofNullable(WebUtil.getRequest())
                .map(request -> UriUtil.getPath(request.getRequestURI()))
                .orElse(StringUtil.EMPTY);
        return Links.builder().self(self).build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(MissingServletRequestParameterException e) {
        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.BAD_REQUEST_PARAM_MISS, e.getMessage()));
        return ResultFactory.warn(SystemResultEnum.BAD_REQUEST_PARAM_MISS, message, links());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(MethodArgumentTypeMismatchException e) {
        String message = String.format("请求参数格式错误: %s", e.getName());
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.BAD_REQUEST_PARAM_TYPE_ERROR, e.getMessage()));
        return ResultFactory.warn(SystemResultEnum.BAD_REQUEST_PARAM_TYPE_ERROR, message, links());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(MethodArgumentNotValidException e) {
        return handleError(e.getBindingResult());
    }

    /**
     * 请求参数绑定错误
     *
     * @param e {@link BindException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(BindException e) {
        return handleError(e.getBindingResult());
    }

    private AbstractResult<Serializable> handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", Objects.requireNonNull(error).getField(), error.getDefaultMessage());
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.BAD_REQUEST_PARAM_BIND_ERROR, message));
        return ResultFactory.warn(SystemResultEnum.BAD_REQUEST_PARAM_BIND_ERROR, message, links());
    }

    /**
     * 参数校验失败
     *
     * @param e {@link ConstraintViolationException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.BAD_REQUEST_PARAM_VALID_ERROR, message));
        return ResultFactory.warn(SystemResultEnum.BAD_REQUEST_PARAM_VALID_ERROR, links());
    }

    /**
     * 404未找到请求 (RPC 调用可能会触发 )
     *
     * @param e {@link NoHandlerFoundException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AbstractResult<Serializable> handleError(NoHandlerFoundException e) {
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.NOT_FOUND), e);
        return ResultFactory.warn(SystemResultEnum.NOT_FOUND, links());
    }

    /**
     * 不支持当前请求方法
     *
     * @param e {@link HttpRequestMethodNotSupportedException}
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public AbstractResult<Serializable> handleError(HttpRequestMethodNotSupportedException e) {
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.METHOD_NOT_SUPPORTED, e.getMessage()), e);
        return ResultFactory.error(SystemResultEnum.METHOD_NOT_SUPPORTED, links());
    }

    /**
     * 数据类型错误 或 RPC 调用时，有时候参数没有实现 {@link java.io.Serializable} 接口，导致数据无法反序列化，会抛出此问题
     *
     * @param e {@link HttpMessageNotReadableException } 消息无法正常读取
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(HttpMessageNotReadableException e) {
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.BAD_REQUEST_MSG_NOT_READABLE, e.getMessage()));
        return ResultFactory.warn(SystemResultEnum.BAD_REQUEST_MSG_NOT_READABLE, links());
    }


    /**
     * 不支持当前媒体类型
     *
     * @param e {@link HttpMediaTypeNotSupportedException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public AbstractResult<Serializable> handleError(HttpMediaTypeNotSupportedException e) {
        log.warn(LoggerUtil.logMessageWarn(SystemResultEnum.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage()));
        return ResultFactory.warn(SystemResultEnum.MEDIA_TYPE_NOT_SUPPORTED, links());
    }

    /**
     * 服务异常信息
     *
     * @param e {@link ServiceException}
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(ServiceException e) {
        log.warn(LoggerUtil.logMessageWarn(e.getCode(), e.getMessage()));
        return ResultFactory.warn(e.getCode(), e.getMessage(), links());
    }

    /**
     * 业务异常
     *
     * @param e {@link BusinessException}业务异常
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Serializable> handleError(BusinessException e) {
        log.warn(LoggerUtil.logMessageWarn(e.getCode(), e.getMessage()), e);
        return ResultFactory.warn(e.getCode(), e.getMessage(), links());
    }

    /**
     * 安全认证异常
     *
     * @param e {@link SecureException} 安全认证
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(SecureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AbstractResult<Serializable> handleError(SecureException e) {
        log.warn(LoggerUtil.logMessageWarn(e.getCode(), e.getMessage()));
        return ResultFactory.warn(SystemResultEnum.UN_AUTHORIZED, links());
    }


    /**
     * 工具类异常（确认自己是否正确使用工具类 或 工具原本就存在问题）
     *
     * @param e {@link UtilException} 工具异常
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(UtilException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AbstractResult<Serializable> handleError(com.jyusun.origin.core.common.exception.UtilException e) {
        log.error(LoggerUtil.logMessageError(e.getCode(), e.getMessage(),
                e.getClass().getSimpleName(), e.getMessage()), e);
        return ResultFactory.error(SystemResultEnum.INTERNAL_SERVER_ERROR, links());
    }

    /**
     * 拦截所有未知的异常信息，避免暴露给用户
     *
     * @param e {@link Throwable} 异常超类
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AbstractResult<Serializable> handleError(Throwable e) {
        log.error(LoggerUtil.logMessageError(SystemResultEnum.INTERNAL_SERVER_ERROR,
                e.getClass().getSimpleName(), e.getMessage()), e);
        return ResultFactory.error(SystemResultEnum.INTERNAL_SERVER_ERROR, links());
    }
}