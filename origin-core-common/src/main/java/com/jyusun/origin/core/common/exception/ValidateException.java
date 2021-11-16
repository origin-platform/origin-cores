package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.enums.SystemResultEnum;

/**
 * 验证异常
 *
 * @author jyusun at 2021-10-8 09:48:55
 * @since 1.0.0
 */
public class ValidateException extends BusinessException {

    /**
     * 异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public ValidateException(String code, String message) {
        super(code, message);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public ValidateException(BaseResultCode baseResultCode) {
        super(baseResultCode.code(), baseResultCode.message());
    }

    /**
     * 业务异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public ValidateException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode}
     * @param throwable      {@link Throwable} 异常信息
     */
    public ValidateException(BaseResultCode baseResultCode, Throwable throwable) {
        this(baseResultCode.code(), baseResultCode.message(), throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public ValidateException(Throwable throwable) {
        this(SystemResultEnum.INTERNAL_SERVER_ERROR, throwable);
    }

}
