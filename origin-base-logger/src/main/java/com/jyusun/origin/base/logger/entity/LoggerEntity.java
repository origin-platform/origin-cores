package com.jyusun.origin.base.logger.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jyusun.origin.base.logger.entity.value.RequestInfo;
import com.jyusun.origin.base.logger.entity.value.UserAgentInfo;
import com.jyusun.origin.core.common.result.AbstractResult;
import com.jyusun.origin.core.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体对象
 * <p>
 * 作用描述：日志实体对象
 *
 * @author jyusun
 * @date 2021/7/5 21:09
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoggerEntity extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 请求信息
     */
    @ApiModelProperty("请求信息")
    private RequestInfo requestInfo;

    /**
     * 响应结果
     */
    @ApiModelProperty("响应结果")
    private AbstractResult<Serializable> result;

    /**
     * 用户代理信息
     */
    @ApiModelProperty("用户代理信息")
    private UserAgentInfo userAgent;

    /**
     * 日志主题
     */
    @ApiModelProperty("日志主题")
    private String title;

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Long beginTime;

    /**
     * 执行耗时
     */
    @ApiModelProperty("运行耗时")
    private Long timeConsuming;

}
