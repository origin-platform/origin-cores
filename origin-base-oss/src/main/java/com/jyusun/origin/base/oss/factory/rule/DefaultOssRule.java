package com.jyusun.origin.base.oss.factory.rule;

import com.jyusun.origin.base.oss.common.constant.OssConstant;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.FileUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UuidUtil;

/**
 * 默认Oss规则
 *
 * @author sun
 */
public class DefaultOssRule implements OssRule {


    @Override
    public String bucketName(String bucketName) {
        return bucketName;
    }

    @Override
    public String defaultPath(String originalFilename) {
        return OssConstant.UPLOAD_PATH + StringUtil.SLASH + DateUtil.thisDateToStrYmd() + StringUtil.SLASH +
                UuidUtil.generateUuidStr32() + FileUtil.getExtension(originalFilename);
    }

    /**
     * 完整的文件路径
     *
     * @param baseDir  文件基础路径
     * @param fileName 文件名
     * @return {@code String } 例如：/test/abc.xml
     */
    @Override
    public String fullPath(String baseDir, String fileName) {
        return baseDir + StringUtil.SLASH + fileName;
    }


}