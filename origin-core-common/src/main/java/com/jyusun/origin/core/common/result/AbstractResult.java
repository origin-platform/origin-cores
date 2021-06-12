package com.jyusun.origin.core.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 作用描述： - 响应对象
 *
 * @author jyusun at 2019/12/21 19:32
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("响应对象")
public class AbstractResult<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应编码", required = true)
    protected String code;

    @ApiModelProperty(value = "响应消息", required = true)
    protected String message;

    @ApiModelProperty(value = "操作标记", required = true, example = "false-操作失败|true-操作成功")
    protected Boolean sign;

    /**
     * 数据初始化
     *
     * @param code    {@code String } 响应编码
     * @param message {@code String } 响应消息
     */
    void init(String code, String message, boolean sign) {

        this.code = code;
        this.message = message;
        this.sign = sign;
    }

}
