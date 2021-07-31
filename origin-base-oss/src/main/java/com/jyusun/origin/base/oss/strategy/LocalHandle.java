package com.jyusun.origin.base.oss.strategy;

import com.aliyun.oss.model.Bucket;
import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssAccessor;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.model.UploadInfo;
import com.jyusun.origin.base.oss.rule.OssRule;
import com.jyusun.origin.core.common.util.StringUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 对象存储处理
 * <p>
 * 作用描述：上传到本地文件夹
 *
 * @author jyusun at 2021-7-31 10:48:21
 * @since 1.0.0
 */
public class LocalHandle extends OssAccessor implements OssTemplate {

    public LocalHandle(OssFactory ossFactory) {
        super();
        this.setOssFactory(ossFactory);
    }


    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     * @return {@link Bucket}
     */
    @SneakyThrows
    private boolean createBucket(String bucketName) {
        File file = new File(getBucketName(bucketName));
        return file.mkdirs();
    }

    /**
     * 获取oss存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    @Override
    public OssRule getRule() {
        return this.getOssFactory().getOssRule();
    }

    @Override
    public String getBucketName(String bucketName) {
        return this.getRule().bucketName(bucketName);
    }

    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    @Override
    public OssProperties getOssProperties() {
        return this.getOssFactory().getOssProperties();
    }

    @Override
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return new File(this.getRule().bucketName(bucketName)).exists();
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    @Override
    @SneakyThrows
    public void makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            this.createBucket(bucketName);
        }
    }


    /**
     * @param bucketName   存储空间名称
     * @param inputStream  {@link InputStream} 输入流
     * @param originalName {@code String} 文件名称
     * @param cover        {@code Boolean} true-覆盖，false-不覆盖
     * @return {@link UploadInfo} 上传信息
     */
    @SneakyThrows
    public UploadInfo put(String bucketName, InputStream inputStream, String originalName, boolean cover) {
        this.makeBucket(bucketName);
        String key = this.getRule().defaultFilePath(originalName);

        File thisFile = new File(this.getBucketName(bucketName) + StringUtil.SLASH + key);
        try {
            if (cover) {
                try (FileOutputStream fos = new FileOutputStream(thisFile)) {
                    IOUtils.copy(inputStream, fos);
                }
            } else {
                if (!thisFile.exists()) {
                    try (FileOutputStream fos = new FileOutputStream(thisFile)) {
                        IOUtils.copy(inputStream, fos);
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setOriginalName(originalName);
        uploadInfo.setName(key);
//        file.setLink(fileLink(bucketName, key));
        return uploadInfo;
    }


    @Override
    @SneakyThrows
    public void removeFile(String fileName) {
        this.removeFile(this.getOssProperties().getBucketName(), fileName);
    }

    @Override
    @SneakyThrows
    public void removeFile(String bucketName, String fileName) {
    }

    @Override
    @SneakyThrows
    public void removeFiles(List<String> fileNames) {
        this.removeFiles(this.getOssProperties().getBucketName(), fileNames);
    }

    @Override
    @SneakyThrows
    public void removeFiles(String bucketName, List<String> fileNames) {
        fileNames.forEach(fileName -> removeFile(bucketName, fileName));
    }

}
