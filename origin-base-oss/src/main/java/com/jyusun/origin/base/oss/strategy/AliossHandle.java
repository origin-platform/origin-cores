package com.jyusun.origin.base.oss.strategy;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssAccessor;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.model.UploadInfo;
import com.jyusun.origin.base.oss.rule.OssRule;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 阿里云对象存储
 *
 * @author sun
 */
public class AliossHandle extends OssAccessor implements OssTemplate {

    public AliossHandle(OssFactory ossFactory) {
        super();
        this.setOssFactory(ossFactory);
    }

    /**
     * 获取阿里云客户端
     *
     * @return {@link OSSClient}
     */
    private OSS getOssClient() {
        return this.getOssFactory().getOssClient();
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     * @return {@link Bucket}
     */
    @SneakyThrows
    private Bucket createBucket(String bucketName) {
        return this.getOssClient().createBucket(getBucketName(bucketName));
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
        return this.getOssClient().doesBucketExist(getRule().bucketName(bucketName));
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
     * @param stream       {@link InputStream} 输入流
     * @param originalName {@code String} 文件名称
     * @param cover        {@code Boolean} true-覆盖，false-不覆盖
     * @return {@link UploadInfo} 上传信息
     */
    @SneakyThrows
    public UploadInfo put(String bucketName, InputStream stream, String originalName, boolean cover) {
        this.makeBucket(bucketName);
        String key = this.getRule().defaultFilePath(originalName);
        if (cover) {
            this.getOssClient().putObject(getBucketName(bucketName), key, stream);
        } else {
            PutObjectResult response = this.getOssClient().putObject(this.getBucketName(bucketName), key,
                    stream);
            int retry = 0;
            int retryCount = 5;
            while (StringUtils.hasText(response.getETag()) && retry < retryCount) {
                response = this.getOssClient().putObject(getBucketName(bucketName), key, stream);
                retry++;
            }
        }
        UploadInfo file = new UploadInfo();
        file.setOriginalName(originalName);
        file.setName(key);
//        file.setLink(fileLink(bucketName, key));
        return file;
    }


    @Override
    @SneakyThrows
    public void removeFile(String fileName) {
        this.removeFile(this.getOssProperties().getBucketName(), fileName);
    }

    @Override
    @SneakyThrows
    public void removeFile(String bucketName, String fileName) {
        getOssClient().deleteObject(getRule().bucketName(bucketName), fileName);
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

//    /**
//     * 获取上传凭证，普通上传
//     */
//    public String getUploadToken() {
//        return getUploadToken(this.getOssProperties().getBucketName());
//    }
//    /**
//     * <p>
//     * 获取上传凭证，普通上传
//     */
//    public String getUploadToken(String bucketName) {
//        // 默认过期时间2小时
//        return getUploadToken(bucketName, ossProperties.getArgs().get("expireTime", 3600L));
//    }


}
