package com.jyusun.origin.core.logger.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：常规日志
 * </p>
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UsualLoggerDTO extends AbstractLogger {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库表
     */
    @ApiModelProperty("数据库表")
    private String tableName;

    /**
     * 业务编号
     */
    @ApiModelProperty("业务编号")
    private String bizId;

    /**
     * 详情描述
     */
    @ApiModelProperty("详情描述")
    private String description;


}
