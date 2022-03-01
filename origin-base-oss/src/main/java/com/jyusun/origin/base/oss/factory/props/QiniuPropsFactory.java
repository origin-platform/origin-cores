package com.jyusun.origin.base.oss.factory.props;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 七牛对象存储属性工厂
 *
 * @author jyusun at 2021-10-8 15:19:28
 */
@Data
@Accessors(chain = true)
public class QiniuPropsFactory extends AbstractPropsFactory {

    /**
     * 七牛云鉴权
     */
    private Auth auth;
    /**
     * 七牛云上传管理
     */
    private UploadManager uploadManager;
    /**
     * 七牛云管理
     */
    private BucketManager bucketManager;

}
