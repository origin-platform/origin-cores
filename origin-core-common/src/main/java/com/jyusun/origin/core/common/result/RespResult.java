package com.jyusun.origin.core.common.result;

import com.jyusun.origin.core.common.base.BaseResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 响应数据信息
 *
 * @author jyusun at 2020/2/20 17:35
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RespResult<E> extends AbstractResult<E> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("承载数据")
    private E data;

    /**
     * 构造函数
     *
     * @param data           响应数据
     * @param baseResultCode 响应编码
     */
    public RespResult(BaseResultCode baseResultCode, Boolean sign, E data) {
        this(baseResultCode.code(), baseResultCode.message(), sign, data);
    }

    /**
     * 构造函数
     *
     * @param sign           响应数据
     * @param baseResultCode 响应编码
     */
    public RespResult(BaseResultCode baseResultCode, Boolean sign) {
        this(baseResultCode.code(), baseResultCode.message(), sign);
    }

    /**
     * 构造函数
     *
     * @param sign    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RespResult(String code, String message, Boolean sign) {
        this.init(code, message, sign, null);
    }

    /**
     * 构造函数
     *
     * @param data    响应数据
     * @param code    响应编码
     * @param message 响应消息
     */
    public RespResult(String code, String message, Boolean sign, E data) {
        this.init(code, message, sign, data);
    }

    /**
     * 初始化数据响应结果
     *
     * @param code    {@code String } 响应编码
     * @param message {@code String } 响应消息
     * @param sign    {@code Boolean} 操作标记
     * @param data    {@code Boolean} 响应数据
     */
    private void init(String code, String message, boolean sign, E data) {
        super.init(code, message, sign);
        this.data = data;
    }


}
