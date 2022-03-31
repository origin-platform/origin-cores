package com.jyusun.origin.base.oss.factory.handle;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.props.OssClient;
import com.jyusun.origin.base.oss.factory.props.AliClientImpl;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * 对象存储处理
 * <p>
 * 作用描述：阿里云
 *
 * @author jyusun at 2021-7-31 10:48:21
 * @since 1.0.0
 */
public class AliossHandle implements OssHandleFactory {

    private final AliClientImpl propsFactory;

    public AliossHandle(OssClient propsFactory) {
        this.propsFactory = (AliClientImpl) propsFactory;
    }


    /**
     * 获取oss存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    @Override
    public OssRule getRule() {
        return this.propsFactory.getOssRule();
    }

    /**
     * 获取阿里云客户端
     *
     * @return {@link OSSClient}
     */
    private OSS getOssClient() {
        return this.propsFactory.getOssClient();
    }

    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    @Override
    public OssProperties ossProperties() {
        return this.propsFactory.getOssProperties();
    }


    /**
     * 创建桶名称
     *
     * @param bucketName
     * @return
     */
    public String getBucketName(String bucketName) {
        return this.getRule().bucketName(bucketName);
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     * @return {@link Bucket}
     */
    @SneakyThrows
    private Bucket createBucket(String bucketName) {
        return this.getOssClient().createBucket(this.getBucketName(bucketName));
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
     * 文件上传
     *
     * @param bucketName   存储空间名称
     * @param inputStream  {@link InputStream} 输入流
     * @param originalName {@code String} 文件名称
     * @param cover        {@code Boolean} true-覆盖，false-不覆盖
     * @return {@link UploadInfo} 上传信息
     */
    @Override
    @SneakyThrows
    public UploadInfo put(InputStream inputStream, String bucketName, String basedir, String originalName,
                          boolean cover) {
        this.makeBucket(bucketName);
        String key;
        if (StringUtils.hasText(basedir)) {
            key = this.getRule().fullPath(basedir, originalName);
        } else {
            key = this.getRule().defaultPath(originalName);
        }
        if (cover) {
            this.getOssClient().putObject(getBucketName(bucketName), key, inputStream);
        } else {
            PutObjectResult response = this.getOssClient().putObject(this.getBucketName(bucketName), key,
                    inputStream);
            int retry = 0;
            int retryCount = 5;
            while (StringUtils.hasText(response.getETag()) && retry < retryCount) {
                response = this.getOssClient().putObject(getBucketName(bucketName), key, inputStream);
                retry++;
            }
        }
        UploadInfo file = new UploadInfo();
        file.setOriginalName(originalName);
        file.setName(key);
//        file.setLink(fileLink(bucketName, key));
        return file;
    }


//    @Override
//    @SneakyThrows
//    public void removeFile(String fileName) {
//        this.removeFile(this.getOssProperties().getBucketName(), fileName);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFile(String bucketName, String fileName) {
//        getOssClient().deleteObject(getRule().bucketName(bucketName), fileName);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFiles(List<String> fileNames) {
//        this.removeFiles(this.getOssProperties().getBucketName(), fileNames);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFiles(String bucketName, List<String> fileNames) {
//        fileNames.forEach(fileName -> removeFile(bucketName, fileName));
//    }

//    /**
//     * 下载响应
//     *
//     * @param bucketName       存储空间名称
//     * @param responseFileName 响应文件名称
//     * @param remoteFilePath   远程文件路径
//     */
//    @Override
//    public void downloadWeb(String bucketName, String responseFileName, String remoteFilePath) {
//
//    }
//
//    /**
//     * 下载响应
//     *
//     * @param responseFileName 响应文件名称
//     * @param remoteFilePath   远程文件路径
//     */
//    @Override
//    public void downloadWeb(String responseFileName, String remoteFilePath) {
//
//    }


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
