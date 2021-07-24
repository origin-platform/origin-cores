package com.jyusun.origin.base.oss.common.enums;


import com.jyusun.origin.core.common.base.BaseEnum;
import lombok.AllArgsConstructor;

/**
 * OSS存储类型
 *
 * @author jyusun
 */
@AllArgsConstructor
public enum TypeEnum implements BaseEnum {

    /**
     * 对象存储类型:本地
     */
    LOCAL("local", "本地存储"),
    /**
     * 对象存储类型:阿里云
     */
    ALI("ali", "阿里云"),
    /**
     * 对象存储类型:七牛云
     */
    QINIU("qiniu", "七牛云"),
    /**
     * 对象存储类型:FastDFS
     */
    FASTDFS("fastdfs", "FastDFS");

    private final String code;
    private final String label;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }
}
