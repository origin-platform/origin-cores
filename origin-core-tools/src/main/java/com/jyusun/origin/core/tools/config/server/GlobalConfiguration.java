package com.jyusun.origin.core.tools.config.server;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类
 *
 * @author jyusun
 */
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
public class GlobalConfiguration {


}
