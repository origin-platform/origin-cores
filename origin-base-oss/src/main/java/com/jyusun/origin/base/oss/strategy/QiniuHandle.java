package com.jyusun.origin.base.oss.strategy;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssAccessor;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.rule.OssRule;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.List;

/**
 * 阿里云对象存储
 *
 * @author sun
 */
public class QiniuHandle extends OssAccessor implements OssTemplate {


    public QiniuHandle(OssFactory ossFactory) {
        super();
        this.setOssFactory(ossFactory);
    }


    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    @Override
    public OssProperties getOssProperties() {
        return null;
    }

    /**
     * 获取存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    @Override
    public OssRule getRule() {
        return null;
    }

    /**
     * 获取存储空间名称
     *
     * @param bucketName {@code String } 存储空间名称
     * @return {@code String} 存储空间名称
     */
    @Override
    public String getBucketName(String bucketName) {
        return null;
    }

    /**
     * 存储空间是否存在
     *
     * @param bucketName 存储空间名称
     * @return {@code Boolean} true-存在，false-不存在
     */
    @Override
    public boolean bucketExists(String bucketName) {
        return false;
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    @Override
    public void makeBucket(String bucketName) {

    }

    /**
     * 删除文件
     *
     * @param fileName {@code String} 文件名称
     */
    @Override
    public void removeFile(String fileName) {

    }

    /**
     * 删除文件
     *
     * @param bucketName {@code String} 存储空间名称
     * @param fileName   {@code String} 文件名称
     */
    @Override
    public void removeFile(String bucketName, String fileName) {

    }

    /**
     * 删除文件
     *
     * @param fileNames {@link  List<String>} 文件名称集合
     */
    @Override
    public void removeFiles(List<String> fileNames) {

    }

    /**
     * 删除文件
     *
     * @param bucketName {@code String} 存储空间名称
     * @param fileNames  {@link List<String>} 文件名称集合
     */
    @Override
    public void removeFiles(String bucketName, List<String> fileNames) {

    }
}
