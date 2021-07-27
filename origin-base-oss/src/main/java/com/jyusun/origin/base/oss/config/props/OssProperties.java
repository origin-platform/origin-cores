package com.jyusun.origin.base.oss.config.props;

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
    private Boolean enabled = true;
    /**
     * 对象存储类型
     */
    private TypeEnum type;
    /**
     * 存储空间
     */
    private String bucket;
    /**
     * 默认存储空间名称
     */
    private String bucketName = "origin-platform";
    /**
     * 端点（访问域名）
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
