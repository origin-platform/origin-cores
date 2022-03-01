package com.jyusun.origin.base.oss;


import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssAccessor;
import com.jyusun.origin.base.oss.factory.handle.OssHandleFactory;
import com.jyusun.origin.base.oss.model.UploadInfo;

import java.io.InputStream;

/**
 * OSS模板
 *
 * @author jyusun at 2021-10-9 13:49:09
 */
public class OssTemplate extends OssAccessor {

    public OssTemplate(OssHandleFactory ossHandleFactory) {
        super(ossHandleFactory);
    }

    /**
     * 处理工厂
     *
     * @return {@link OssHandleFactory}
     */
    private OssHandleFactory ossFactory() {
        return this.getOssHandleFactory();
    }

    /**
     * Osss 属性配置
     *
     * @return {@link OssProperties}
     */
    private OssProperties ossProperties() {
        return ossFactory().ossProperties();
    }


    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储桶
     * @param basedir      基础路径
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    public UploadInfo upload(InputStream inputStream, String bucketName, String basedir, String originalName,
                             boolean cover) {
        return this.ossFactory().put(inputStream, bucketName, basedir, originalName, cover);
    }

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param basedir      基础路径
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    public UploadInfo upload(InputStream inputStream, String basedir, String originalName) {
        return this.ossFactory().put(inputStream, ossProperties().getBucketName(), basedir, originalName, true);
    }


}
