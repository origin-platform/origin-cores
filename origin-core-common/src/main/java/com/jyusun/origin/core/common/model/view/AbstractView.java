package com.jyusun.origin.core.common.model.view;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 作用描述： - 基础视图对象
 *
 * @author jyusun at 2019/12/23 16:40
 * @since 1.0.0
 */
public abstract class AbstractView {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键编号")
    protected Serializable sid;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    protected String remarks;

    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
    protected Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty("最后更新人")
    protected Long updateBy;
    /**
     * 最后更新时间
     */
    @ApiModelProperty("更新时间")
    protected LocalDateTime updateTime;

    @ApiModelProperty("乐观锁")
    protected Integer revision;

}
