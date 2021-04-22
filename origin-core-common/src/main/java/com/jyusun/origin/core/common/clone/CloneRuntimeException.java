package com.jyusun.origin.core.common.clone;

import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.exception.BaseException;

/**
 * 克隆异常
 *
 * @author jyusun
 */
public class CloneRuntimeException extends BaseException {

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
        super(baseResultCode);
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 抛出异常
     */
    public CloneRuntimeException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public CloneRuntimeException(Throwable throwable) {
        super(throwable);
    }

}
