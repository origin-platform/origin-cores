package com.jyusun.origin.core.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 键值模型
 * <p>
 * 作用描述：Key Value 模型
 * </p>
 *
 * @author jyusun at 2020/8/25 14:50
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class DictDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String dictCode;

    /**
     * 键值
     */
    @ApiModelProperty("键值")
    private String dictValue;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String dictDesc;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 默认值
     */
    @ApiModelProperty("默认值")
    private Boolean defValue;

}
