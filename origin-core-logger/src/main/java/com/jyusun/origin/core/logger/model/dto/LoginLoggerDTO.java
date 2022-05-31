package com.jyusun.origin.core.logger.model.dto;

import com.jyusun.origin.core.logger.model.value.RequestValue;
import com.jyusun.origin.core.logger.model.value.UserAgentValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录日志数据传输对象
 * <p>
 * 作用描述：接口请求
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
public class LoginLoggerDTO extends AbstractLogger implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主题
     */
    @ApiModelProperty("日志主题")
    private String title;

    /**
     * 操作人ID
     */
    @ApiModelProperty("登录人ID")
    private Long userId;

    /**
     * 操作IP地址
     */
    @ApiModelProperty("远程地址")
    private String remoteAddress;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    private LocalDateTime loginTime;

    /**
     * 执行耗时
     */
    @ApiModelProperty("运行耗时")
    private Long timeCost;

    /**
     * 用户代理信息
     */
    @ApiModelProperty("请求信息")
    private RequestValue requestValue;
    /**
     * 用户代理信息
     */
    @ApiModelProperty("用户代理信息")
    private UserAgentValue userAgentValue;

}
