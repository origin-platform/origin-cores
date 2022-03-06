package com.jyusun.origin.core.logger.model.value;

import com.jyusun.origin.core.model.domain.value.BaseValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户代理信息
 *
 * @author jyusun
 * @date 2021-6-8 13:47:28
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserAgentValue extends BaseValue {

    @ApiModelProperty("浏览器厂商")
    private String browserManufacturer;
    @ApiModelProperty("浏览器组")
    private String browserGroup;
    @ApiModelProperty("浏览器名称")
    private String browserName;
    @ApiModelProperty("浏览器类型")
    private String browserType;
    @ApiModelProperty("浏览器版本")
    private String browserVersion;

    @ApiModelProperty("设备厂商")
    private String deviceManufacturer;
    @ApiModelProperty("设备类型")
    private String deviceType;
    @ApiModelProperty("设备操作系统")
    private String os;

    @ApiModelProperty("操作系统名称")
    private String osName;
    @ApiModelProperty(value = "操作系统版本号", example = "32或64")
    private String osVersion;
    @ApiModelProperty("操作系统浏览器渲染引擎")
    private String browserRenderingEngine;

}
