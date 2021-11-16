package com.jyusun.origin.core.common.clone;

import com.jyusun.origin.core.common.base.BaseResultCode;
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
     * @param baseResultCode {@link BaseResultCode} 消息枚举
     */
    public CloneRuntimeException(BaseResultCode baseResultCode) {
        super(baseResultCode.code(), baseResultCode.message());
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
     * @param baseResultCode {@link BaseResultCode}
     * @param throwable      {@link Throwable} 异常信息
     */
    public CloneRuntimeException(BaseResultCode baseResultCode, Throwable throwable) {
        this(baseResultCode.code(), baseResultCode.message(), throwable);
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
