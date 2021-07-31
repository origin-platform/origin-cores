package com.jyusun.origin.base.oss.common.enums;


import lombok.AllArgsConstructor;

/**
 * OSS存储类型
 *
 * @author jyusun
 */
@AllArgsConstructor
public enum TypeEnum {

    /**
     * 对象存储类型:本地
     */
    LOCAL,
    /**
     * 对象存储类型:阿里云
     */
    ALI,
    /**
     * 对象存储类型:七牛云
     */
    QINIU,
    /**
     * 对象存储类型:FastDFS
     */
    FASTDFS;

}
