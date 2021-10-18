package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.handle.LocalHandle;
import com.jyusun.origin.base.oss.factory.handle.OssHandleFactory;
import com.jyusun.origin.base.oss.factory.props.AbstractPropsFactory;
import com.jyusun.origin.base.oss.factory.props.LocalPropsFactory;
import com.jyusun.origin.base.oss.factory.rule.LocalOssRule;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
    public AbstractPropsFactory propsFactory(OssRule ossRule) {
        return new LocalPropsFactory().setOssProperties(ossProperties).setOssRule(ossRule);
    }

    /**
     * 本地处理
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate ossTemplate(AbstractPropsFactory propsFactory) {
        log.info("================ Local OSS Template ================");
        OssHandleFactory ossHandleFactory = new LocalHandle(propsFactory);
        return new OssTemplate(ossHandleFactory);
    }
}
