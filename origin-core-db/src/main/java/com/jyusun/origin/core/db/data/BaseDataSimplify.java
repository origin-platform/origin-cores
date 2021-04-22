package com.jyusun.origin.core.db.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础精简数据对象
 * </p>
 *
 * @author jyusun at 2019/12/21 17:57
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseDataSimplify extends BaseDataId {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField("remarks")
    protected String remarks;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;


}
