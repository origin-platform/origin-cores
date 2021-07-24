package com.jyusun.origin.core.tools.config.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 作用描述：
 * - 基础属性配置
 *
 * @author jyusun at 2020/3/16 12:44
 * @since 1.0.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "origin-system")
public class GlobalProperties {

    /** project name * */
    private String name;

    /** project version */
    private String version;

    /** Base Profile */
    private String profile;

    /**
     * 获取头像上传路径
     */
    public String getAvatarPath() {
        return this.profile + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public String getDownloadPath() {
        return this.profile + "/download";
    }

    /**
     * 获取上传路径
     */
    public String getUploadPath() {
        return this.profile + "/upload";
    }
}
