//package com.jyusun.origin.base.oss.factory.handle;
//
//import com.jyusun.origin.base.oss.config.props.OssProperties;
//import com.jyusun.origin.base.oss.factory.props.AbstractPropsFactory;
//import com.jyusun.origin.base.oss.factory.rule.OssRule;
//import com.jyusun.origin.base.oss.model.UploadInfo;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * 阿里云对象存储
// *
// * @author sun
// */
//public class QiniuHandle  {
//
//
//    public QiniuHandle(AbstractPropsFactory abstractPropsFactory) {
//        super();
//        this.setOssFactory(abstractPropsFactory);
//    }
//
//
//    /**
//     * 获取oss配置属性
//     *
//     * @return {@link OssProperties}
//     */
//    @Override
//    public OssProperties getOssProperties() {
//        return null;
//    }
//
//    /**
//     * 获取存取规则
//     *
//     * @return {@link OssRule} 存取规则
//     */
//    @Override
//    public OssRule getRule() {
//        return null;
//    }
//
//    /**
//     * 获取存储空间名称
//     *
//     * @param bucketName {@code String } 存储空间名称
//     * @return {@code String} 存储空间名称
//     */
//    @Override
//    public String getBucketName(String bucketName) {
//        return null;
//    }
//
//    /**
//     * 存储空间是否存在
//     *
//     * @param bucketName 存储空间名称
//     * @return {@code Boolean} true-存在，false-不存在
//     */
//    @Override
//    public boolean bucketExists(String bucketName) {
//        return false;
//    }
//
//    /**
//     * 创建存储空间
//     *
//     * @param bucketName 存储空间名称
//     */
//    @Override
//    public void makeBucket(String bucketName) {
//
//    }
//
//    /**
//     * @param bucketName   存储空间名称
//     * @param stream       {@link InputStream} 输入流
//     * @param originalName {@code String} 文件名称
//     * @param cover        {@code Boolean} true-覆盖，false-不覆盖
//     * @return {@link UploadInfo} 上传信息
//     */
//    @Override
//    public UploadInfo put(String bucketName, InputStream stream, String originalName, boolean cover) {
//        return null;
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param fileName {@code String} 文件名称
//     */
//    @Override
//    public void removeFile(String fileName) {
//
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param bucketName {@code String} 存储空间名称
//     * @param fileName   {@code String} 文件名称
//     */
//    @Override
//    public void removeFile(String bucketName, String fileName) {
//
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param fileNames {@link  List<String>} 文件名称集合
//     */
//    @Override
//    public void removeFiles(List<String> fileNames) {
//
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param bucketName {@code String} 存储空间名称
//     * @param fileNames  {@link List<String>} 文件名称集合
//     */
//    @Override
//    public void removeFiles(String bucketName, List<String> fileNames) {
//
//    }
//
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
//}
