package com.jyusun.origin.base.feign.config;

import com.jyusun.origin.core.common.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 作用描述：
 * - Feign 配置
 *
 * @author jyusun at 2020/1/29 12:54
 * @since 1.0.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(basePackages = SystemConstant.BASE_PACKAGE)
public class FeignConfiguration {

}
