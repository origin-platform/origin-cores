package com.jyusun.origin.base.oss.factory.props;

import com.aliyun.oss.OSS;
import com.jyusun.origin.base.oss.context.OssContext;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 阿里对象存储属性工厂
 *
 * @author jyusun at 2021-10-8 13:41:11
 */
@Data
@Accessors(chain = true)
public class AliClientImpl implements OssClient {



    /**
     * 阿里云对象存储
     */
    private OSS ossClient;

    @Override
    public OssContext getOssContext() {
        return ossContext;
    }
}
