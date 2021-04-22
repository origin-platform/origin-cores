package com.jyusun.origin.core.tools.config.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 服务器信息
 *
 * @author jyusun at 2019-08-08
 */
@Configuration(proxyBeanMethods = false)
@Import({SysServerProperties.class, SysServerListener.class})
public class SysServerAutoConfiguration {


}
