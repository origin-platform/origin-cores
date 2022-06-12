package com.jyusun.origin.core.common.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 作用描述： - RPC响应对象
 *
 * @author jyusun at 2020/2/20 17:35
 * @since 1.0.0
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("RPC响应对象")
public class RpcResult<T> extends AbstractResult<T> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("承载数据")
    private T body;

    /**
     * 构造函数
     *
     * @param sign    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RpcResult(String code, String message, boolean sign) {
        super(code, message, sign);
    }

    /**
     * 构造函数
     *
     * @param body    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RpcResult(String code, String message, boolean sign, T body) {
        super(code, message, sign);
        this.init(body);
    }

    /**
     * 初始化数据响应结果
     *
     * @param body {@code Boolean} 响应数据
     */
    private void init(T body) {
        this.body = body;
    }


}
