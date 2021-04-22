package com.jyusun.origin.core.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 基础视图对象
 *
 * @author jyusun at 2019/12/23 16:40
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseView extends BaseViewSimplify {

    @ApiModelProperty("创建人")
    protected Long creator;

    @ApiModelProperty("创建人：标签")
    protected String creatorLabel;

    @ApiModelProperty("最后更新人")
    protected Long updateMan;

    @ApiModelProperty("最后更新人：标签")
    protected String updateManLabel;

    @ApiModelProperty("乐观锁")
    protected Integer revision;

}
