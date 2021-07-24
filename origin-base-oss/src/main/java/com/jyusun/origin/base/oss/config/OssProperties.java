package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.common.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS属性
 *
 * @author jyusun
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "origin-system.oss")
public class OssProperties {

    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 对象存储类型
     */
    private TypeEnum type;
    /**
     * 令牌桶
     */
    private String bucket;
    /**
     * 令牌桶名称
     */
    private String bucketName;
    /**
     * 端点
     */
    private String endpoint;
    /**
     * 访问ID
     */
    private String accessKeyId;
    /**
     * 访问密钥
     */
    private String accessKeySecret;
}
