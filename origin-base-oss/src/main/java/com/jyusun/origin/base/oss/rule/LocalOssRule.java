package com.jyusun.origin.base.oss.rule;

import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.FileUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UuidUtil;

/**
 * 本地Oss规则
 *
 * @author sun
 */
public class LocalOssRule implements OssRule {


    @Override
    public String bucketName(String bucketName) {
        return bucketName;
    }

    @Override
    public String defaultFilePath(String originalFilename) {
        return StringUtil.SLASH + DateUtil.thisDateToStrYmd() + StringUtil.SLASH +
                UuidUtil.generateUuidStr32() + FileUtil.getExtension(originalFilename);
    }

    @Override
    public String fullFilePath(String baseDir, String originalFilename) {
        return baseDir +
                StringUtil.SLASH +
                UuidUtil.generateUuidStr32() +
                FileUtil.getExtension(originalFilename);
    }


}
