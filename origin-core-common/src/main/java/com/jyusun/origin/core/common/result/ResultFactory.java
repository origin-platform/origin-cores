package com.jyusun.origin.core.common.result;


import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.util.AssemblerUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UriUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 作用描述： 响应结果静态工厂
 *
 * @author jyusun at 2020/3/9 21:10
 * @since 1.0.0
 */
@UtilityClass
public class ResultFactory {

    /**
     * 数据传输结果响应
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param body    {@code Object} 承载数据
     * @param <T>     {@code T} 泛型标记
     * @return {@link AbstractResult} 响应结果
     */
    public static <T extends Serializable> AbstractResult<T> data(String code, String message, T body) {
        return Optional.ofNullable(body)
                .map(obj -> new RespResult<>(code, message, true, obj))
                .orElseThrow(() -> new BusinessException(SystemResultEnum.SUCCESS_DATA_WARN));
    }

    /**
     * 数据传输结果响应
     *
     * @param baseResultCode {@link BaseResultCode} 基础响应结果
     * @param body           {@code Object} 承载数据
     * @param <T>            {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <T extends Serializable> AbstractResult<T> data(BaseResultCode baseResultCode, T body) {
        return data(baseResultCode.code(), baseResultCode.message(), body);

    }

    /**
     * 数据传输结果响应
     *
     * @param body {@code Object} 承载数据
     * @param <T>  {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <T extends Serializable> AbstractResult<T> data(T body) {
        return data(SystemResultEnum.SUCCESS, body);
    }

    /**
     * 数据传输结果响应
     *
     * @param <T> {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <T extends Serializable> AbstractResult<List<T>> datas(String code, String message, List<T> datas) {
        return Optional.ofNullable(datas)
                .map(obj -> new RespResult<>(code, message, true, obj))
                .orElseThrow(() -> new BusinessException(SystemResultEnum.SUCCESS_DATA_WARN));
    }

    /**
     * 数据传输结果响应
     *
     * @param datas {@code Object} 承载数据
     * @param <T>   {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <T extends Serializable> AbstractResult<List<T>> datas(BaseResultCode baseResultCode, List<T> datas) {
        return datas(baseResultCode.code(), baseResultCode.message(), datas);
    }

    /**
     * 数据传输结果响应
     *
     * @param datas {@code Object} 承载数据
     * @param <T>   {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <T extends Serializable> AbstractResult<List<T>> datas(List<T> datas) {
        return datas(SystemResultEnum.SUCCESS, datas);
    }

    /**
     * 数据传输结果响应 数据转换
     *
     * @param data  {@code Object} 承载数据
     * @param clazz {@link  Class<E>} 转换类型
     * @param <E>   {@code E }  泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E extends Serializable> AbstractResult<E> dataConvert(Object data, Class<E> clazz) {
        return data(AssemblerUtil.convert(data, clazz));
    }

    /**
     * 数据操作状态
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param sign    操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    public static <T extends Serializable> AbstractResult<T> status(String code, String message, boolean sign) {
        if (!sign) {
            throw new BusinessException(SystemResultEnum.SUCCESS_NO_CONTENT);
        }
        return new RespResult<>(code, message, true);
    }

    /**
     * 数据操作状态
     *
     * @param baseResultCode {@link BaseResultCode} 操作标记（true-成功,false-失败）
     * @param sign           {@code Boolean} 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    private static <T extends Serializable> AbstractResult<T> status(BaseResultCode baseResultCode, boolean sign) {
        return status(baseResultCode.code(), baseResultCode.message(), sign);
    }

    /**
     * 数据操作状态
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    public static <T extends Serializable> AbstractResult<T> status(boolean sign) {
        return status(SystemResultEnum.SUCCESS, sign);
    }

    /**
     * 数据操作状态-创建数据
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    public static <T extends Serializable> AbstractResult<T> create(boolean sign) {
        return status(SystemResultEnum.SUCCESS_CREATE, sign);
    }

    /**
     * 数据操作状态-操作成功
     *
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    public static <T extends Serializable> AbstractResult<T> success() {
        return status(SystemResultEnum.SUCCESS, true);
    }

    /**
     * 错误信息
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param links   {@link Links} 链接信息
     * @param title   {@code String} 消息标题
     * @param detail  {@code String} 消息明细
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Serializable> error(String code, String message, Links links,
                                                     String title, String detail) {
        return new ErrorResult<>(code, message, links, title, detail);
    }

    /**
     * 错误信息
     *
     * @param baseResultCode {@code BaseResultCode} 枚举消息
     * @param links          {@link Links} 链接信息
     * @param title          {@code String} 消息标题
     * @param detail         {@code String} 消息明细
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Serializable> error(BaseResultCode baseResultCode, Links links,
                                                     String title, String detail) {
        return error(baseResultCode.code(), baseResultCode.message(), links, title, detail);
    }

    /**
     * 错误信息
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param links   {@link Links} 链接信息
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Serializable> error(String code, String message, Links links) {
        return new ErrorResult<>(code, message, links);
    }

    /**
     * 错误信息
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @return {@link ErrorResult}响应结果
     */
    public static AbstractResult<Serializable> error(String code, String message) {
        return new ErrorResult<>(code, message);
    }

    /**
     * 错误信息
     *
     * @param baseResultCode {@link BaseResultCode}  枚举结果
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> error(BaseResultCode baseResultCode) {
        return error(baseResultCode.code(), baseResultCode.message(), links());
    }

    /**
     * 异常信息
     *
     * @param message {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> error(String message) {
        return error(SystemResultEnum.INTERNAL_SERVER_ERROR.code(), message);
    }

    /**
     * 警告信息
     *
     * @param message {@code String} 消息描述
     * @param links   {@link Links} 请求连接
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(String code, String message, Links links) {
        return new WarnResult<>(code, message, links);
    }

    /**
     * 警告信息
     *
     * @param baseResultCode {@link BaseResultCode} 枚举消息
     * @param message        {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(BaseResultCode baseResultCode, String message) {
        return warn(baseResultCode.code(), message, links());
    }

    /**
     * 警告信息
     *
     * @param baseResultCode {@link BaseResultCode} 枚举消息
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(BaseResultCode baseResultCode) {
        return warn(baseResultCode.code(), baseResultCode.message(), links());
    }

    /**
     * 警告信息
     *
     * @param message {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(String code, String message) {
        return warn(code, message, links());
    }

    /**
     * 接口数据处理，一般情况下如果feign接口统一包装后，获取数据使用
     *
     * @param abstractResult {@link AbstractResult<T>}
     * @return {@link <T>} 泛型标记数据
     */
    public static <T extends Serializable> T dataHandle(AbstractResult<T> abstractResult) {
        if (!abstractResult.getSign()) {
            throw new BusinessException(abstractResult.getCode(), abstractResult.getMessage());
        }
        return ((RespResult<T>) abstractResult).getBody();
    }

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
}
