package com.jyusun.origin.base.oss.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Oss配置类
 *
 * @author jyusun
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(value = "origin-system.oss.type", havingValue = "qiniu")
public class QiniuConfiguration {

    private final OssProperties ossProperties;


}
