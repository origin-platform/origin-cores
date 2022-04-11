package com.jyusun.origin.base.wecom.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({WocomConfiguration.class})
public class WocomConfiguration {

    private final WocomConfiguration wocomConfiguration;

}
