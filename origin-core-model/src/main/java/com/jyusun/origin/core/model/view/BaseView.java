package com.jyusun.origin.core.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 作用描述： - 基础视图对象
 *
 * @author jyusun at 2019/12/23 16:40
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseView extends BaseViewObject {

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    protected String remarks;
    /**
     * 最后更新时间
     */
    @ApiModelProperty("更新时间")
    protected LocalDateTime updateTime;

    @ApiModelProperty("最后更新人")
    protected Long updateBy;

    @ApiModelProperty("最后更新人：标签")
    protected String updateByDesc;

    @ApiModelProperty("乐观锁")
    protected Integer revision;








}
