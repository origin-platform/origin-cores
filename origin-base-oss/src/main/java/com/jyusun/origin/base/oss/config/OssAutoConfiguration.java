package com.jyusun.origin.base.oss.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Oss 自动装配
 *
 * @author jyusun
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(prefix = "origin-system.oss", name = "enabled", matchIfMissing = true)
public class OssAutoConfiguration {
}


