package com.jyusun.origin.base.logger.common.util;


import com.alibaba.fastjson.JSON;
import com.jyusun.origin.base.logger.entity.LoggerEntity;
import com.jyusun.origin.base.logger.entity.value.RequestInfo;
import com.jyusun.origin.base.logger.entity.value.UserAgentInfo;
import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.result.AbstractResult;
import com.jyusun.origin.core.common.result.RespResult;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UriUtil;
import com.jyusun.origin.core.common.util.UserAgentUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.secure.common.util.SecureUtil;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * 工具
 * <p>
 * 作用描述：日志处理
 *
 * @author jyusun
 * @date 2021/7/3 16:21
 * @since 1.0.0
 */
@UtilityClass
public class LoggerUtil {

    /**
     * 异常信息格式化字符串
     */
    private static final String STR_FORMAT_ERROR = new StringJoiner(StringUtil.LF)
            .add(StringUtil.LF)
            .add("############################## [Exception - Begin] ##############################")
            .add(" CODE     : %s")
            .add(" MESSAGE  : %s")
            .add(" TITLE    : %s")
            .add(" DETAIL   : %s")
            .add("############################## [Exception - End  ] ##############################")
            .toString();

    /**
     * 警告信息格式化字符串
     */
    private static final String STR_FORMAT_WARN = new StringJoiner(StringUtil.LF)
            .add(StringUtil.LF)
            .add("****************************** [WarnException - Begin] ******************************")
            .add(" CODE   : %s")
            .add(" MESSAGE: %s")
            .add("****************************** [WarnException - End  ] ******************************")
            .toString();

    /**
     * 请求信息格式化字符串
     */
    private static final String STR_FORMAT_REQUEST = new StringJoiner(StringUtil.LF)
            .add(StringUtil.LF)
            .add("============================== [RequestInfo - Begin] ==============================")
            .add(" 日志主题 : %s")
            .add(" 操 作 人 : %s")
            .add(" 请求时间 : %s")
            .add(" 执行耗时 : %s")
            .add(" 请求响应 : %s")
            .add("============================== [RequestInfo - End  ] ==============================")
            .toString();

    /**
     * 请求信息格式化字符串 报错错误
     */
    private static final String STR_FORMAT_REQUEST_ERROR = new StringJoiner(StringUtil.LF)
            .add(StringUtil.LF)
            .add("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#= [RequestException - Begin] =#=#=#=#=#=#=#=#=#=#=#=#=#=#=#")
            .add(" 日志主题 : %s")
            .add(" 操 作 人 : %s")
            .add(" 请求时间 : %s")
            .add(" 请求信息 : %s")
            .add("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#= [RequestException - End  ] =#=#=#=#=#=#=#=#=#=#=#=#=#=#=#")
            .toString();

    public static String logMessageError(String code, String message, String title, String detail) {
        return String.format(STR_FORMAT_ERROR, code, message, title, detail);
    }

    public static String logMessageError(
            BaseResultCode baseResultCode,
            String title, String detail) {
        return logMessageError(baseResultCode.code(), baseResultCode.message(), title, detail);
    }

    public static String logMessageWarn(String code, String message) {
        return String.format(STR_FORMAT_WARN, code, message);
    }

    public static String logMessageWarn(BaseResultCode baseResultCode) {
        return logMessageWarn(baseResultCode.code(), baseResultCode.message());
    }

    public static String logMessageWarn(BaseResultCode baseResultCode, String message) {
        return logMessageWarn(baseResultCode.code(), baseResultCode.message() + "，" + message);

    }

    public static String logMessageRequest(HttpServletRequest request, LoggerEntity logger,
                                           Map<String, Object> argsParams, String className,
                                           String method, AbstractResult<Serializable> result) {

        RequestInfo requestInfo = Optional.ofNullable(request)
                .map(req -> getRequestInfo(req, argsParams, className, method)).orElse(new RequestInfo());

        UserAgentInfo userAgent = Optional.ofNullable(request)
                .map(LoggerUtil::getUserAgent).orElse(new UserAgentInfo());

        logger.setOperator(String.valueOf(SecureUtil.getUser().getUserId()))
                .setRequestInfo(requestInfo)
                // 代理信息 应该用户登录日志记录一次即可
                .setUserAgent(userAgent)
                .setResult((RespResult<Serializable>) result);
        return String.format(STR_FORMAT_REQUEST, logger.getTitle(),
                logger.getOperator(),
                logger.getBeginTime(),
                logger.getTimeConsuming(),
                JSON.toJSONString(logger));
    }


    public static String logMessageRequestError(HttpServletRequest request, LoggerEntity logger,
                                                Map<String, Object> params, String className,
                                                String method) {

        RequestInfo requestInfo = Optional.ofNullable(request)
                .map(req -> getRequestInfo(req, params, className, method)).orElse(new RequestInfo());
        UserAgentInfo userAgent = Optional.ofNullable(request)
                .map(LoggerUtil::getUserAgent).orElse(new UserAgentInfo());

        logger.setOperator(String.valueOf(SecureUtil.getUser().getUserId()))
                .setRequestInfo(requestInfo)
                // 代理信息 应该用户登录日志记录一次即可
                .setUserAgent(userAgent);

        return String.format(STR_FORMAT_REQUEST_ERROR, logger.getTitle(),
                logger.getOperator(), logger.getBeginTime(), JSON.toJSONString(logger));
    }

    /**
     * 获取request的信息
     *
     * @param request    {@link HttpServletRequest} Http请求信息
     * @param className  {@code String} 类名称
     * @param methodName {@code String} 方法名称
     */
    public static RequestInfo getRequestInfo(HttpServletRequest request, Map<String, Object> params,
                                             String className, String methodName) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setRemoteAddr(WebUtil.getIpAddr(request))
                .setRequestUri(UriUtil.getPath(request.getRequestURI()))
                .setHttpMethod(request.getMethod())
                .setClassName(className)
                .setMethodName(methodName)
                .setRequestParams(WebUtil.getParams(request))
                .setParams(params);
        return requestInfo;
    }

    /**
     * 添加用户代理信息
     *
     * @param request {@link HttpServletRequest}
     */
    public static UserAgentInfo getUserAgent(HttpServletRequest request) {
        String userAgent = UserAgentUtil.getUserAgent(request);
        UserAgentInfo info = new UserAgentInfo();
        info.setBrowserManufacturer(UserAgentUtil.getBrowserManufacturer(userAgent));
        info.setBrowserGroup(UserAgentUtil.getBrowserGroup(userAgent));
        info.setBrowserName(UserAgentUtil.getBrowserName(userAgent));
        info.setBrowserType(UserAgentUtil.getBrowserType(userAgent));
        info.setBrowserVersion(UserAgentUtil.getBrowserVersion(userAgent));

        info.setDeviceManufacturer(UserAgentUtil.getDeviceManufacturer(userAgent));
        info.setDeviceType(UserAgentUtil.getDevicetype(userAgent));
        info.setOs(UserAgentUtil.getOs(userAgent));

        info.setOsName(UserAgentUtil.getOsName(userAgent));
        info.setOsVersion(UserAgentUtil.getOsVersion(userAgent));
        info.setBrowserRenderingEngine(UserAgentUtil.getBorderRenderingEngine(userAgent));
        return info;

    }
}
