package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;

/**
 * 作用描述： - 业务异常 自定义异常
 *
 * @author jyusun at 2020/1/2 17:08
 * @since 1.0.0
 */
public class BusinessException extends WarnException {

    /**
     * 业务异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public BusinessException(String code, String message) {
        super(code, message);
    }

    /**
     * 业务异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public BusinessException(BaseResultCode baseResultCode) {
        super(baseResultCode);
    }

    /**
     * 业务异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public BusinessException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public BusinessException(Throwable throwable) {
        super(throwable);
    }


}
