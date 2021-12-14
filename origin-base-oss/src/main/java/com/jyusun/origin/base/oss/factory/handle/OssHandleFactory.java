package com.jyusun.origin.base.oss.factory.handle;


import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;

import java.io.InputStream;

/**
 * 抽象工厂
 *
 * @author jyusun at 2021-10-8 16:58:54
 * @since 1.0.0
 */
public interface OssHandleFactory {


    /**
     * 获取配置属性
     *
     * @return {@link OssProperties} OSS属性
     */
    OssProperties ossProperties();

    /**
     * 规则获取
     *
     * @return {@link OssRule} 存储规则
     */
    OssRule getRule();

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName
     * @return
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketName
     */
    void makeBucket(String bucketName);


    /**
     * 文件上传
     *
     * @param inputStream
     * @param bucketName
     * @param basedir
     * @param originalName
     * @param cover
     * @return {@link UploadInfo}
     */
    UploadInfo put(InputStream inputStream, String bucketName, String basedir, String originalName, boolean cover);

}
