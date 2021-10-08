package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;

/**
 * 验证异常
 *
 * @author jyusun at 2021-10-8 09:48:55
 */
public class ValidateException extends BusinessException {

    /**
     * 异常信息构造函数
     *
     * @param code    {@code String}  消息编码
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
        super(baseResultCode);
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code String}  消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public ValidateException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public ValidateException(Throwable throwable) {
        super(throwable);
    }
}
