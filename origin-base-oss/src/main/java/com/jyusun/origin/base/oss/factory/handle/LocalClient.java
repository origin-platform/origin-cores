package com.jyusun.origin.base.oss.factory.handle;


import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.LocalFileContext;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;

import java.io.InputStream;

/**
 * 对象存储处理
 * <p>
 * 作用描述：上传到本地文件夹
 *
 * @author jyusun at 2021-7-31 10:48:21
 * @since 1.0.0
 */
public class LocalClient implements OssFactory {

    private final LocalFileContext ossContext;

    public LocalClient(LocalFileContext ossContext) {
        this.ossContext = ossContext;
    }

    @Override
    public LocalFileContext getOssContext() {
        return ossContext;
    }

    /**
     * 获取配置属性
     *
     * @return
     */
    public OssProperties ossProperties() {
        return this.getOssContext().getOssProperties();
    }

    /**
     * 规则获取
     *
     * @return
     */
    public OssRule getRule() {
        return this.getOssContext().getOssRule();
    }

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName) {
        return false;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName
     */
    public void makeBucket(String bucketName) {

    }

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
    @Override
    public UploadInfo put(InputStream inputStream, String bucketName, String basedir, String originalName,
                          boolean cover) {
        return null;
    }

    /**
     * 文件上传
     *
     * @param inputStream
     * @param originalName
     * @param cover
     * @return {@link UploadInfo}
     */
    @Override
    public UploadInfo put(InputStream inputStream, String originalName, boolean cover) {
        return null;
    }
}
