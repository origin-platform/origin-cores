package com.jyusun.origin.base.oss.rule;

import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.FileUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UuidUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;

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

    /**
     * 文件路径
     *
     * @param originalFilename 文件名
     * @return
     */
    @Override
    public String defaultFilePath(String originalFilename) {
        return new File(FileUtils.getTempDirectoryPath() + StringUtil.SLASH + DateUtil.thisDateToStrYmd() + StringUtil.SLASH +
                UuidUtil.generateUuidStr32() + FileUtil.getExtension(originalFilename)).getPath();
    }

    @Override
    public String fullFilePath(String baseDir, String originalFilename) {
        return new File(baseDir +
                StringUtil.SLASH +
                UuidUtil.generateUuidStr32() +
                FileUtil.getExtension(originalFilename)).getPath();
    }


}
