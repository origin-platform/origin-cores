package com.jyusun.origin.core.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 作用描述： - 基础视图对象
 *
 * @author jyusun at 2019/12/23 16:40
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class BaseViewSimplify implements Serializable {

    @ApiModelProperty("主键编号")
    protected Serializable sid;

    @ApiModelProperty("备注")
    protected String remarks;

    @ApiModelProperty("创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    protected LocalDateTime updateTime;

}
