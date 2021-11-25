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
public abstract class BaseViewObject implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键编号")
    protected Serializable sid;

    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
    protected Long creator;


    @ApiModelProperty("创建人：标签")
    protected String creatorLabel;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    protected LocalDateTime createTime;

}
