package com.jyusun.origin.base.oss.factory.props;

import com.aliyun.oss.OSS;
import com.jyusun.origin.base.oss.context.OssContext;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 本地对象存储属性工厂
 *
 * @author jyusun at 2021-10-8 15:22:55
 */
@Data
@Accessors(chain = true)
public class LocalPropsFactory implements OssClient {

    private final OssContext ossContext;

    /**
     * 阿里云对象存储
     */
    private OSS ossClient;

    @Override
    public OssContext getOssContext() {
        return ossContext;
    }
}
