package com.jyusun.origin.core.tools.config.server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 作用描述：
 * - 服务配置信息
 *
 * @author jyusun at 2020/3/16 12:44
 * @since 1.0.0
 */
@Slf4j
@Getter
public class SysServerProperties {

    private String appName;
    private String activeEnv;

    private String hostName;
    private String serverIp;
    private Integer serverPort;
    private String address;

}
