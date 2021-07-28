package com.jyusun.origin.base.oss.factory;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.rule.OssRule;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Getter;
import lombok.Setter;

/**
 * 对象存储工厂
 *
 * @author jyusun
 */
@Getter
@Setter
public class OssFactory {

    /**
     * 对象存储属性
     */
    private OssProperties ossProperties;

    /**
     * 对象存储规则
     */
    private OssRule ossRule;

    /**
     * 阿里云对象存储
     */
    private OSS ossClient;

    /**
     * 七牛云鉴权
     */
    private Auth auth;
    /**
     * 七牛云上传管理
     */
    private UploadManager uploadManager;
    /**
     * 七牛云管理
     */
    private BucketManager bucketManager;
}
