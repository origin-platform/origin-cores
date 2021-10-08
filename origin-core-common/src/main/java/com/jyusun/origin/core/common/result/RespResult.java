package com.jyusun.origin.core.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 作用描述： - 响应数据信息
 *
 * @author jyusun at 2020/2/20 17:35
 * @since 1.0.0
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("成功响应对象")
public class RespResult<E extends Serializable> extends AbstractResult<E> {

    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("承载数据")
    private E body;

    /**
     * 构造函数
     *
     * @param sign    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RespResult(String code, String message, boolean sign) {
        this.init(code, message, sign, null);
    }

    /**
     * 构造函数
     *
     * @param body    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RespResult(String code, String message, boolean sign, E body) {
        this.init(code, message, sign, body);
    }

    /**
     * 初始化数据响应结果
     *
     * @param code    {@code String } 响应编码
     * @param message {@code String } 响应消息
     * @param sign    {@code Boolean} 操作标记
     * @param body    {@code Boolean} 响应数据
     */
    private void init(String code, String message, boolean sign, E body) {
        this.init(code, message, sign);
        this.body = body;
    }


}
