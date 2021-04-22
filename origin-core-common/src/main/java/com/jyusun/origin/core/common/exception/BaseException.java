package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.util.ExceptionUtil;
import lombok.Getter;

/**
 * 作用描述： - 基础异常 自定义异常
 *
 * @author jyusun at 2020/1/2 17:08
 * @since 1.0.0
 */
public abstract class BaseException extends RuntimeException {

    @Getter
    protected String code;

    /**
     * 异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public BaseException(BaseResultCode baseResultCode) {
        this(baseResultCode.code(), baseResultCode.message());
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     * @param throwable  {@link Throwable} 异常信息
     */
    public BaseException(BaseResultCode baseResultCode, Throwable throwable) {
        this(baseResultCode.code(), baseResultCode.message(), throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public BaseException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public BaseException(Throwable throwable) {
        super(ExceptionUtil.getMessage(throwable), throwable);
    }

}
