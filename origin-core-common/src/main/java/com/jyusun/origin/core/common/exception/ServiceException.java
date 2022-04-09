package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.base.BaseResultEnum;
import com.jyusun.origin.core.common.enums.SystemResultEnum;

/**
 * 作用描述： - 服务错误 自定义异常
 *
 * @author jyusun
 * @date 2020/1/2 17:08
 * @since 1.0.0
 */
public class ServiceException extends BaseException {

    /**
     * 异常信息构造函数
     *
     * @param code    {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public ServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum} 消息枚举
     */
    public ServiceException(BaseResultEnum baseResultEnum) {
        super(baseResultEnum.code(), baseResultEnum.message());
    }

    /**
     * 异常信息构造函数
     *
     * @param code      {@code Integer} 消息编码
     * @param message   {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public ServiceException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param baseResultEnum {@link BaseResultEnum}
     * @param throwable      {@link Throwable} 异常信息
     */
    public ServiceException(BaseResultEnum baseResultEnum, Throwable throwable) {
        this(baseResultEnum.code(), baseResultEnum.message(), throwable);
    }

    /**
     * 异常信息构造函数
     *
     * @param throwable {@link Throwable} 异常信息
     */
    public ServiceException(Throwable throwable) {
        this(SystemResultEnum.INTERNAL_SERVER_ERROR, throwable);
    }

}
