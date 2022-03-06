package com.jyusun.origin.core.logger.model.dto;


import com.jyusun.origin.core.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 作用描述：日志基础数据传输对象
 *
 * @author jyusun
 * @date 2020/5/18 12:01
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AbstractLogger extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("服务名称")
    private String serviceCode;
    /**
     * 操作类型：9-未知类型|0-用户登录|1-分页查询|2-数据新增|3-数据更新|4-数据删除|5-数据查询|6-数据列表
     */
    @ApiModelProperty("操作类型")
    private String operationType;

    /**
     * 方法类
     */
    @ApiModelProperty("方法类")
    private String className;
    /**
     * 方法名
     */
    @ApiModelProperty("方法名称")
    private String methodName;
}
