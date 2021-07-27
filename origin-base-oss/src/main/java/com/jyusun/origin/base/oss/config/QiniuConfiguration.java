package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.rule.OssRule;
import com.jyusun.origin.base.oss.strategy.QiniuHandle;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public com.qiniu.storage.Configuration qiniuConfiguration() {
        return new com.qiniu.storage.Configuration(Zone.autoZone());
    }

    @Bean
    public Auth auth() {
        return Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

    @Bean
    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
    public UploadManager uploadManager(com.qiniu.storage.Configuration cfg) {
        return new UploadManager(cfg);
    }

    @Bean
    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
    public BucketManager bucketManager(com.qiniu.storage.Configuration cfg) {
        return new BucketManager(Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret()), cfg);
    }

    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    @ConditionalOnBean({Auth.class, UploadManager.class, BucketManager.class, OssRule.class})
    public OssTemplate qiniuTemplate(Auth auth, UploadManager uploadManager, BucketManager bucketManager,
                                     OssRule ossRule) {
        return new QiniuHandle(auth, uploadManager, bucketManager, ossRule);
    }


}
