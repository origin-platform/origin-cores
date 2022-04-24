package com.jyusun.origin.core.common.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("响应对象")
public abstract class AbstractResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应编码", required = true)
    private String code;

    @ApiModelProperty(value = "响应消息", required = true)
    private String message;

    @ApiModelProperty(value = "操作标记", required = true, example = "0-操作失败|1-操作成功")
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