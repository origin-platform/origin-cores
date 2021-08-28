package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.rule.LocalOssRule;
import com.jyusun.origin.base.oss.rule.OssRule;
import com.jyusun.origin.base.oss.strategy.LocalHandle;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@EnableConfigurationProperties({OssProperties.class, OssRule.class})
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

    @Bean
    @ConditionalOnBean(OssRule.class)
    public OssFactory ossFactory(OssRule ossRule) {
        OssFactory ossFactory = new OssFactory();
        ossFactory.setOssProperties(ossProperties);
        ossFactory.setOssRule(ossRule);
        return ossFactory;
    }

    /**
     * 本地处理
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate localTemplate(OssFactory ossFactory) {
        return new LocalHandle(ossFactory);
    }
}
