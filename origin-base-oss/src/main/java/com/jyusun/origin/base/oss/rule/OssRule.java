package com.jyusun.origin.base.oss.rule;

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
     * @param originalFilename 文件名
     * @return {@code String } 例如：/test/abc.xml
     */
    String defaultFilePath(String originalFilename);

    /**
     * 完整的文件路径
     *
     * @param baseDir          文件基础路径
     * @param originalFilename 文件名
     * @return {@code String } 例如：/test/abc.xml
     */
    String fullFilePath(String baseDir, String originalFilename);
}
