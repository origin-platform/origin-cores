package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;
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
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public UtilException(BaseResultCode baseResultCode) {
        super(baseResultCode.code(), baseResultCode.message());
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
     * @param baseResultCode {@link BaseResultCode}
     * @param throwable      {@link Throwable} 异常信息
     */
    public UtilException(BaseResultCode baseResultCode, Throwable throwable) {
        this(baseResultCode.code(), baseResultCode.message(), throwable);
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
