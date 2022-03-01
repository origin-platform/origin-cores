package com.jyusun.origin.base.oss.factory.rule;

/**
 * 规则处理
 *
 * @author jyusun at 2021-10-9 09:51:05
 */
public interface OssRule {

    /**
     * 获取存储桶规则
     *
     * @param bucketName 存储桶名称
     * @return {@code String }
     */
    String bucketName(String bucketName);

    /**
     * 默认文件路径
     *
     * @param originalFilename 原始文件名
     * @return {@code String } 例如：/test/abc.xml
     */
    String defaultPath(String originalFilename);

    /**
     * 完整的文件路径
     *
     * @param baseDir          文件基础路径
     * @param originalFilename 原始文件名
     * @return {@code String } 例如：/test/abc.xml
     */
    String fullPath(String baseDir, String originalFilename);
}
