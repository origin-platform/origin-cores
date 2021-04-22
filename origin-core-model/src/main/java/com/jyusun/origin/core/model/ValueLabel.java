package com.jyusun.origin.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 作用描述：Key Value 模型
 * </p>
 *
 * @author jyusun
 * @date 2020/8/25 14:50
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ValueLabel implements Serializable {

    /** 键值 */
    @ApiModelProperty("键值")
    private String value;
    /** 标签 */
    @ApiModelProperty("标签")
    private String label;

}
