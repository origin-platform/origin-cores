package com.jyusun.origin.base.oss.factory.handle;


import com.jyusun.origin.base.oss.context.OssContext;
import com.jyusun.origin.base.oss.model.UploadInfo;

import java.io.InputStream;

/**
 * 抽象工厂
 *
 * @author jyusun at 2021-10-8 16:58:54
 * @since 1.0.0
 */
public interface OssFactory {

    OssContext getOssContext();

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

    /**
     * 文件上传
     *
     * @param inputStream
     * @param originalName
     * @param cover
     * @return {@link UploadInfo}
     */
    UploadInfo put(InputStream inputStream, String originalName, boolean cover);

}
