package com.jyusun.origin.core.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应对象
 * <p>
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
public class WarnResult<E extends Serializable> extends AbstractResult<E> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接对象")
    private Links links;

    /**
     * 构造函数
     *
     * @param code    响应编码
     * @param message 响应消息
     */
    public WarnResult(String code, String message, Links links) {
        this.init(code, message, links);
    }

    /**
     * 初始化数据响应结果
     *
     * @param code    {@code Integer } 响应编码
     * @param message {@code String } 响应消息
     */
    private void init(String code, String message, Links links) {
        this.init(code, message, false);
        this.links = links;
    }


}
