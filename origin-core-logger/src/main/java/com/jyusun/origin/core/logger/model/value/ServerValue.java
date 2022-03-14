package com.jyusun.origin.core.logger.model.value;

import com.jyusun.origin.core.model.domain.value.BaseValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 服务信息
 *
 * @author jyusun at 10:32:39
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ServerValue extends BaseValue {

    /**
     * 应用名称
     */
    @ApiModelProperty("服务名称")
    private String serviceCode;

    /**
     * 服务器 ip
     */
    @ApiModelProperty("服务IP")
    private String serverIp;

    /**
     * 服务器端口
     */
    @ApiModelProperty("服务端口")
    private Integer serverPort;

    /**
     * 服务器名
     */
    @ApiModelProperty("服务器名")
    private String serverHost;

    /**
     * 环境
     */
    @ApiModelProperty("运行环境")
    private String env;
}
