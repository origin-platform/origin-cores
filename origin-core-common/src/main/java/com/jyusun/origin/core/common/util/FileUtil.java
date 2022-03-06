package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * 文件操作工具类
 *
 * @author jyusun at 2021-7-26 17:54:06
 * @since 1.0.0
 */
@UtilityClass
public class FileUtil {

    /**
     * 获取文件扩展名
     *
     * @param fileName {@code String } 文件名称
     * @return {@code String} 文件扩展名.txt,如果不存在扩展名返回 “”
     */
    public static String getExtension(String fileName) {
        String extension;
        if (fileName.contains(StringUtil.DOT)) {
            //从最后一个点之后截取字符串
            extension = fileName.substring(fileName.lastIndexOf(StringUtil.DOT));
        } else {
            extension = StringUtil.EMPTY;
        }
        return extension;
    }

}
