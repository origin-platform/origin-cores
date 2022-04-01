package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.LocalFileContext;
import com.jyusun.origin.base.oss.factory.handle.LocalClient;
import com.jyusun.origin.base.oss.factory.handle.OssFactory;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 本地配置
 *
 * @author jyusun at 2021-10-8 13:58:33
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({OssProperties.class})
@ConditionalOnProperty(value = "origin-system.oss.type", havingValue = "LOCAL")
public class LocalConfiguration {

    private final OssProperties ossProperties;

    /**
     * 存储规则注册
     *
     * @return {@link OssRule}
     */
    @Bean
    @ConditionalOnMissingBean(OssRule.class)
    public OssRule ossRule() {
        return new LocalOssRule();
    }

    /**
     * @param ossRule
     * @return
     */
    @Bean
    @ConditionalOnBean(OssRule.class)
    public OssFactory propsFactory(OssRule ossRule) {
        LocalFileContext ossContext = LocalFileContext.builder().ossProperties(ossProperties)
                .ossRule(ossRule)
                .build();
        return new LocalClient(ossContext);
    }

    /**
     * 本地处理
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate ossTemplate(OssFactory ossFactory) {
        log.info("================ Local OSS Template ================");
        return new OssTemplate(ossFactory);
    }
}
