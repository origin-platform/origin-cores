//package com.jyusun.origin.base.oss.config;//package com.jyusun.origin.base.oss.config;
//
//import com.jyusun.origin.base.oss.OssTemplate;
//import com.jyusun.origin.base.oss.config.props.OssProperties;
//import com.jyusun.origin.base.oss.factory.props.AbstractPropsFactory;
//import com.jyusun.origin.base.oss.factory.props.QiniuPropsFactory;
//import com.jyusun.origin.base.oss.factory.rule.DefaultOssRule;
//import com.jyusun.origin.base.oss.factory.rule.OssRule;
//import com.qiniu.common.Zone;
//import com.qiniu.storage.BucketManager;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.util.Auth;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//
///**
// * Oss配置类
// *
// * @author jyusun
// */
//@RequiredArgsConstructor
//@EnableConfigurationProperties(OssProperties.class)
//@ConditionalOnProperty(value = "origin-system.oss.type", havingValue = "QINIU")
//public class QiniuConfiguration {
//
//    private final OssProperties ossProperties;
//
//    /**
//     * 存储规则注册
//     *
//     * @return {@link OssRule}
//     */
//    @Bean
//    @ConditionalOnMissingBean(OssRule.class)
//    public OssRule ossRule() {
//        return new DefaultOssRule();
//    }
//
//    @Bean
//    public com.qiniu.storage.Configuration qiniuConfiguration() {
//        return new com.qiniu.storage.Configuration(Zone.autoZone());
//    }
//
//    @Bean
//    public Auth auth() {
//        return Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
//    }
//
//    @Bean
//    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
//    public UploadManager uploadManager(com.qiniu.storage.Configuration cfg) {
//        return new UploadManager(cfg);
//    }
//
//    @Bean
//    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
//    public BucketManager bucketManager(com.qiniu.storage.Configuration cfg) {
//        return new BucketManager(Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret()),
//        cfg);
//    }
//
//    @Bean
//    @ConditionalOnBean({Auth.class, UploadManager.class, BucketManager.class, OssRule.class})
//    public AbstractPropsFactory ossFactory(Auth auth, UploadManager uploadManager, BucketManager bucketManager,
//                                           OssRule ossRule) {
//        return new QiniuPropsFactory().setAuth(auth).setUploadManager(uploadManager).setBucketManager(bucketManager)
//                .setOssProperties(ossProperties).setOssRule(ossRule);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(OssTemplate.class)
//    public OssTemplate qiniuTemplate(AbstractPropsFactory ossFactory) {
//        return new OssTemplate(ossFactory);
//    }
//
//
//}
