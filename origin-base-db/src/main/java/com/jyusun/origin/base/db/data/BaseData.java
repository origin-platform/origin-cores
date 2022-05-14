package com.jyusun.origin.base.db.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础数据对象
 * </p>
 *
 * @author JyuSun at 2019/4/2 16:21
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseData extends BaseCreate {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField("remarks")
    protected String remarks;

    /**
     * 最后更新人ID
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected Long updateBy;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 逻辑删除标识（逻辑删除即更新操作） 0-false-未删除,1-true-已删除
     */
    @TableLogic(value = "0", delval = "1")
    @TableField("deleted")
    protected Boolean deleted;

    /**
     * 乐观锁
     */
    @Version
    @TableField("revision")
    protected Integer revision;


}
