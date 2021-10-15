package com.jyusun.origin.base.oss.factory.handle;

import com.aliyun.oss.model.Bucket;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.props.AbstractPropsFactory;
import com.jyusun.origin.base.oss.factory.props.AliPropsFactory;
import com.jyusun.origin.base.oss.factory.props.LocalPropsFactory;
import com.jyusun.origin.base.oss.model.UploadInfo;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
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
public class LocalHandle implements OssHandleFactory {

    private final LocalPropsFactory propsFactory;

    public LocalHandle(LocalPropsFactory propsFactory) {
        this.propsFactory = propsFactory;
    }


    /**
     * 获取配置属性
     *
     * @return
     */
    @Override
    public OssProperties ossProperties() {
        return this.propsFactory.getOssProperties();
    }

    /**
     * 规则获取
     *
     * @return
     */
    @Override
    public OssRule getRule() {
        return this.propsFactory.getOssRule();
    }

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName
     * @return
     */
    @Override
    public boolean bucketExists(String bucketName) {
        return false;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName
     */
    @Override
    public void makeBucket(String bucketName) {

    }

    /**
     * 文件上传
     *
     * @param stream
     * @param bucketName
     * @param basedir
     * @param originalName
     * @param cover
     * @return {@link UploadInfo}
     */
    @Override
    public UploadInfo put(InputStream stream, String bucketName, String basedir, String originalName, boolean cover) {
        return null;
    }
}
