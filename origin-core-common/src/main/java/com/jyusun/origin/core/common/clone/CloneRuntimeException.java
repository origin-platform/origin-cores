package com.jyusun.origin.core.common.clone;

import com.jyusun.origin.core.common.base.BaseResultEnum;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.ServiceException;

/**
 * 克隆异常
 *
 * @author jyusun
 * @since 1.0.0
 */
public class CloneRuntimeException extends ServiceException {

    /**
     * 异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public CloneRuntimeException(String code, String message) {
        super(code, message);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum} 消息枚举
     */
    public CloneRuntimeException(BaseResultEnum baseResultEnum) {
        super(baseResultEnum.code(), baseResultEnum.message());
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public CloneRuntimeException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum}
     * @param throwable      {@link Throwable} 异常信息
     */
    public CloneRuntimeException(BaseResultEnum baseResultEnum, Throwable throwable) {
        this(baseResultEnum.code(), baseResultEnum.message(), throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public CloneRuntimeException(Throwable throwable) {
        this(SystemResultEnum.INTERNAL_SERVER_ERROR, throwable);
    }


}
