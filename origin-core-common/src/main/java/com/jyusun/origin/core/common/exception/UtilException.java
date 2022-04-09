package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultEnum;
import com.jyusun.origin.core.common.enums.SystemResultEnum;

/**
 * 工具类异常
 *
 * @author jyusun
 * @since 1.0.0
 */
public class UtilException extends BaseException {

    /**
     * 业务异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public UtilException(String code, String message) {
        super(code, message);
    }

    /**
     * 业务异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum} 消息枚举
     */
    public UtilException(BaseResultEnum baseResultEnum) {
        super(baseResultEnum.code(), baseResultEnum.message());
    }

    /**
     * 业务异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public UtilException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum}
     * @param throwable      {@link Throwable} 异常信息
     */
    public UtilException(BaseResultEnum baseResultEnum, Throwable throwable) {
        this(baseResultEnum.code(), baseResultEnum.message(), throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public UtilException(Throwable throwable) {
        this(SystemResultEnum.INTERNAL_SERVER_ERROR, throwable);
    }


}
