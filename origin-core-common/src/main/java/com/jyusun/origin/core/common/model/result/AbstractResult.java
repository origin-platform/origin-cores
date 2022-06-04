package com.jyusun.origin.core.common.model.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应对象
 * <p>
 * 作用描述： - 基础响应对象
 *
 * @author jyusun at 2019/12/21 19:32
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public abstract class AbstractResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Boolean sign;

    protected AbstractResult(String code, String message, boolean sign) {
        this.init(code, message, sign);
    }

    /**
     * 数据初始化
     *
     * @param code    {@code String } 响应编码
     * @param message {@code String } 响应消息
     * @param sign    {@code Boolean } 操作标记
     */
    private void init(String code, String message, boolean sign) {
        this.code = code;
        this.message = message;
        this.sign = sign;
    }

}
