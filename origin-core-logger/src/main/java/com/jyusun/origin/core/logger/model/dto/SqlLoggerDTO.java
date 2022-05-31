package com.jyusun.origin.core.logger.model.dto;

import com.jyusun.origin.core.logger.model.value.ServerValue;
import com.jyusun.origin.core.logger.model.value.SqlValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：SQL 执行日志
 * </p>
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SqlLoggerDTO extends AbstractLogger {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主题
     */
    @ApiModelProperty("日志主题")
    private String title;

    /**
     * 操作人ID
     */
    @ApiModelProperty("操作人ID")
    private Long operator;

    /**
     * SQL值对象
     */
    private SqlValue sqlValue;

    /**
     * 服务信息
     */
    @ApiModelProperty("服务信息")
    private ServerValue serverValue;


}
