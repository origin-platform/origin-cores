package com.jyusun.origin.core.common.exception;

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
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public UtilException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

}
