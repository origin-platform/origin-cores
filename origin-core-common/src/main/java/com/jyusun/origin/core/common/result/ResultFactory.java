package com.jyusun.origin.core.common.result;

import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.base.SystemResultCode;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.util.AssemblerUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 作用描述： 响应结果静态工厂
 *
 * @author jyusun at 2020/3/9 21:10
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResultFactory {

    /**
     * 数据传输结果响应
     *
     * @param code    {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param data    {@code Object} 承载数据
     * @param <E>     {@code E} 泛型标记
     * @return {@link AbstractResult} 响应结果
     */
    public static <E> AbstractResult<E> data(String code, String message, Boolean sign, E data) {
        return new RespResult<>(code, message, sign, data);
    }

    /**
     * 数据传输结果响应
     *
     * @param baseResultCode {@link BaseResultCode} 基础响应结果
     * @param data           {@code Object} 承载数据
     * @param <E>            {@code E} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E> AbstractResult<E> data(BaseResultCode baseResultCode, Boolean sign, E data) {
        String message = sign ? baseResultCode.message() : SystemResultCode.SUCCESS_WARN.message();
        return new RespResult<>(baseResultCode.code(), message, sign, data);

    }

    /**
     * 数据传输结果响应
     *
     * @param data {@code Object} 承载数据
     * @param <E>  {@code E} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E> AbstractResult<E> data(E data) {
        return data(SystemResultCode.SUCCESS, Objects.nonNull(data), data);
    }

    /**
     * 数据传输结果响应 数据转换
     *
     * @param data  {@code Object} 承载数据
     * @param clazz {@link  Class<E>} 转换类型
     * @param <E>   {@code E} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E> AbstractResult<E> dataConvert(Object data, Class<E> clazz) {
        E convert = AssemblerUtils.convert(data, clazz);
        return data(convert);
    }


    /**
     * 数据操作状态
     *
     * @param code    {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param sign    操作标记（true-成功,false-失败）
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Boolean> status(String code, String message, Boolean sign) {
        return new RespResult<>(code, message, sign);
    }

    /**
     * 数据操作状态
     *
     * @param baseResultCode {@link BaseResultCode}操作标记（true-成功,false-失败）
     * @param sign           {@code Boolean} 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult}响应结果
     */
    private static AbstractResult<Boolean> status(BaseResultCode baseResultCode, Boolean sign) {
        return new RespResult<>(baseResultCode.code(), baseResultCode.message(), sign);
    }

    /**
     * 数据操作状态
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Boolean> status(Boolean sign) {
        return status(SystemResultCode.SUCCESS, sign);
    }

    /**
     * 数据操作状态-创建数据
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link RespResult}响应结果
     */
    public static AbstractResult<Boolean> create(Boolean sign) {
        return status(SystemResultCode.SUCCESS_CREATE, sign);
    }


    /**
     * 错误信息
     *
     * @param code    {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param links   {@link Links} 链接信息
     * @param title   {@code String} 消息标题
     * @param detail  {@code String} 消息明细
     * @return {@link AbstractResult}响应结果
     */
    public static <E extends Serializable> AbstractResult<E> error(String code, String message, Links links,
                                                                   String title, String detail) {
        return new ErrorResult<>(code, message, links, title, detail);
    }

    /**
     * 错误信息
     *
     * @param code    {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @return {@link ErrorResult}响应结果
     */
    public static <E> AbstractResult<E> error(String code, String message) {
        return new ErrorResult<>(code, message);
    }

    /**
     * 错误信息
     *
     * @param baseResultCode {@link BaseResultCode}  枚举结果
     * @return {@link AbstractResult} 响应结果
     */
    public static <E> AbstractResult<E> error(BaseResultCode baseResultCode) {
        return error(baseResultCode.code(), baseResultCode.message());
    }


    /**
     * 异常信息
     *
     * @param message {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    public static <E> AbstractResult<E> error(String message) {
        return error(SystemResultCode.INTERNAL_SERVER_ERROR.code(), message);
    }


    /**
     * 接口数据处理
     * <p>
     * 作用描述：一般情况下如果feign接口统一包装后，获取数据使用
     *
     * @param abstractResult {@link AbstractResult<E>}
     * @return {@link <E>} 泛型标记数据
     */
    public static <E> E dataHandle(AbstractResult<E> abstractResult) {
        if (!abstractResult.getSign()) {
            throw new BusinessException(abstractResult.getCode(), abstractResult.getMessage());
        }
        return ((RespResult<E>) abstractResult).getData();
    }

}
