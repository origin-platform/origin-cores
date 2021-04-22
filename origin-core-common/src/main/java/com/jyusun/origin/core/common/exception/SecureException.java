package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;

/**
 * 作用描述： - 自定义异常：认证异常
 *
 * @author jyusun at 2020/1/2 17:08
 * @since 1.0.0
 */
public class SecureException extends BusinessException {

    /**
     * 异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public SecureException(String code, String message) {
        super(code, message);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public SecureException(BaseResultCode baseResultCode) {
        super(baseResultCode);
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public SecureException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public SecureException(Throwable throwable) {
        super(throwable);
    }
	
}
