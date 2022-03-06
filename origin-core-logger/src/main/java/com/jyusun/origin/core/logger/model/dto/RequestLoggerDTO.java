package com.jyusun.origin.core.logger.model.dto;

import com.jyusun.origin.core.logger.model.value.RequestValue;
import com.jyusun.origin.core.logger.model.value.ServiceValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：接口请求
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RequestLoggerDTO extends AbstractLogger {

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
     * 请求时间
     */
    @ApiModelProperty("请求时间")
    private LocalDateTime requestTime;

    /**
     * 执行耗时
     */
    @ApiModelProperty("运行耗时")
    private Long timeCost;

    /**
     * 请求信息
     */
    @ApiModelProperty("请求信息")
    private RequestValue requestValue;

    /**
     * 服务信息
     */
    @ApiModelProperty("服务信息")
    private ServiceValue serviceValue;

}
