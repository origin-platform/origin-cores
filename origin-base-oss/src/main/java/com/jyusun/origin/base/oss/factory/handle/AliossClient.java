package com.jyusun.origin.base.oss.factory.handle;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.AliOssContext;
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
public class AliossClient implements OssFactory {

    private final AliOssContext ossContext;

    public AliossClient(AliOssContext ossContext) {
        this.ossContext = ossContext;
    }

    @Override
    public AliOssContext getOssContext() {
        return ossContext;
    }

    /**
     * 获取oss存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    public OssRule getRule() {
        return this.getOssContext().getOssRule();
    }

    /**
     * 获取阿里云客户端
     *
     * @return {@link OSSClient}
     */
    private OSS ossClient() {
        return this.getOssContext().getOss();
    }

    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    public OssProperties ossProperties() {
        return this.getOssContext().getOssProperties();
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
        return this.ossClient().createBucket(this.getBucketName(bucketName));
    }

    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return this.ossClient().doesBucketExist(getRule().bucketName(bucketName));
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
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
        String thisBucketName = this.getBucketName(bucketName);

        String fullPath = StringUtils.hasText(basedir) ? this.getRule().path(basedir, originalName) :
                this.getRule().defaultPath(originalName);
        if (cover) {
            this.ossClient().putObject(thisBucketName, fullPath, inputStream);
        } else {
            PutObjectResult response = this.ossClient().putObject(thisBucketName, fullPath, inputStream);
            int retry = 0;
            int retryCount = 5;
            while (StringUtils.hasText(response.getETag()) && retry < retryCount) {
                response = this.ossClient().putObject(thisBucketName, fullPath, inputStream);
                retry++;
            }
        }
        UploadInfo file = new UploadInfo();
        file.setOriginalName(originalName);
        file.setName(fullPath);
//        file.setLink(fileLink(bucketName, fullPath));
        return file;
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
        return this.put(inputStream, this.getBucketName(ossProperties().getBucketName()), "", originalName, cover);
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
