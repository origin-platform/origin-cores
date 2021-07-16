package com.jyusun.origin.base.db.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础租户数据对象
 * </p>
 *
 * @author JyuSun at 2019/4/2 16:21
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseTenant extends BaseData {

    private static final long serialVersionUID = 1L;

    /**
     * 租户主键编号: 8位 10000001
     */
    @TableField(value = "tenantId", fill = FieldFill.INSERT)
    protected String tenantId;

}
