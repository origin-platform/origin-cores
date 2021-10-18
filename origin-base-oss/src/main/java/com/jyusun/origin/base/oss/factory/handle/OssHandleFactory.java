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
     * @return
     */
    abstract OssProperties ossProperties();

    /**
     * 规则获取
     *
     * @return
     */
    abstract OssRule getRule();

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName
     * @return
     */
    abstract boolean bucketExists(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketName
     */
    abstract void makeBucket(String bucketName);


    /**
     * 文件上传
     *
     * @param stream
     * @param bucketName
     * @param basedir
     * @param originalName
     * @param cover
     * @return {@link UploadInfo}
     */
    abstract UploadInfo put(InputStream stream, String bucketName, String basedir, String originalName, boolean cover);

}
