package com.jyusun.origin.base.oss;

import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.rule.OssRule;

import java.util.List;

/**
 * OSS模板
 *
 * @author jyusun
 */
public interface OssTemplate {

    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    OssProperties getOssProperties();

    /**
     * 获取存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    OssRule getRule();


    /**
     * 获取存储空间名称
     *
     * @param bucketName {@code String } 存储空间名称
     * @return {@code String} 存储空间名称
     */
    String getBucketName(String bucketName);

    /**
     * 存储空间是否存在
     *
     * @param bucketName 存储空间名称
     * @return {@code Boolean} true-存在，false-不存在
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    void makeBucket(String bucketName);

    /**
     * 删除文件
     *
     * @param fileName {@code String} 文件名称
     */
    void removeFile(String fileName);

    /**
     * 删除文件
     *
     * @param bucketName {@code String} 存储空间名称
     * @param fileName   {@code String} 文件名称
     */
    void removeFile(String bucketName, String fileName);

    /**
     * 删除文件
     *
     * @param fileNames {@link List<String>} 文件名称集合
     */
    void removeFiles(List<String> fileNames);

    /**
     * 删除文件
     *
     * @param bucketName {@code String} 存储空间名称
     * @param fileNames  {@link List<String>} 文件名称集合
     */
    void removeFiles(String bucketName, List<String> fileNames);
}
