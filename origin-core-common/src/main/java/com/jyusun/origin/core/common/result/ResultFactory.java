package com.jyusun.origin.core.common.result;


import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.exception.WarnException;
import com.jyusun.origin.core.common.util.AssemblerUtil;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
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
     * @param data    {@code Object} 承载数据
     * @param <E>     {@code E} 泛型标记
     * @return {@link AbstractResult} 响应结果
     */
    public static <E extends Serializable> AbstractResult<E> data(String code, String message, E data) {
        return Optional.ofNullable(data)
                .map(obj -> new RespResult<>(code, message, true, obj))
                .orElseThrow(() -> new WarnException(SystemResultEnum.SUCCESS_DATA_WARN));
    }

    /**
     * 数据传输结果响应
     *
     * @param baseResultCode {@link BaseResultCode} 基础响应结果
     * @param data           {@code Object} 承载数据
     * @param <E>            {@code E} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E extends Serializable> AbstractResult<E> data(BaseResultCode baseResultCode, E data) {
        return data(baseResultCode.code(), baseResultCode.message(), data);

    }

    /**
     * 数据传输结果响应
     *
     * @param data {@code Object} 承载数据
     * @param <E>  {@code E} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E extends Serializable> AbstractResult<E> data(E data) {
        return data(SystemResultEnum.SUCCESS, data);
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
    public static <E extends Serializable> AbstractResult<E> status(String code, String message, boolean sign) {
        if (!sign) {
            throw new WarnException(SystemResultEnum.SUCCESS_STATUS_WARN);
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
    private static <E extends Serializable> AbstractResult<E> status(BaseResultCode baseResultCode, boolean sign) {
        return status(baseResultCode.code(), baseResultCode.message(), sign);
    }

    /**
     * 数据操作状态
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    public static <E extends Serializable> AbstractResult<E> status(boolean sign) {
        return status(SystemResultEnum.SUCCESS, sign);
    }

    /**
     * 数据操作状态-创建数据
     *
     * @param sign 操作标记（true-成功,false-失败）
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    public static <E extends Serializable> AbstractResult<E> create(boolean sign) {
        return status(SystemResultEnum.SUCCESS_CREATE, sign);
    }

    /**
     * 数据操作状态-操作成功
     *
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    public static <E extends Serializable> AbstractResult<E> success() {
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
     * @param links          {@link Links} 链接信息
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> error(BaseResultCode baseResultCode, Links links) {
        return error(baseResultCode.code(), baseResultCode.message(), links);
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
     * @param links          {@link Links} 请求连接
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(BaseResultCode baseResultCode, String message, Links links) {
        return new WarnResult<>(baseResultCode.code(), message, links);
    }

    /**
     * 警告信息
     *
     * @param baseResultCode {@link BaseResultCode} 枚举消息
     * @param links          {@link Links} 请求连接
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Serializable> warn(BaseResultCode baseResultCode, Links links) {
        return new WarnResult<>(baseResultCode, links);
    }

    /**
     * 接口数据处理，一般情况下如果feign接口统一包装后，获取数据使用
     *
     * @param abstractResult {@link AbstractResult<E>}
     * @return {@link <E>} 泛型标记数据
     */
    public static <E extends Serializable> E dataHandle(AbstractResult<E> abstractResult) {
        if (!abstractResult.getSign()) {
            throw new BusinessException(abstractResult.getCode(), abstractResult.getMessage());
        }
        return ((RespResult<E>) abstractResult).getData();
    }
}
