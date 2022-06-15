package com.jyusun.origin.core.common.constant;


import lombok.experimental.UtilityClass;

/**
 * 服务名称常量
 *
 * @author jyusun
 * @since 1.0.0
 */
@UtilityClass
public class ServiceConstant implements BaseConstants {

    /**
     * 系统服务-系统管理
     */
    public static final String ORIGIN_SYSTEM_ADMIN = "origin-service-admin";

    /**
     * 系统服务-认证服务
     */
    public static final String ORIGIN_SYSTEM_AUTH = "origin-system-auth";
    /**
     * 系统服务-用户服务
     */
    public static final String ORIGIN_SYSTEM_UPMS = "origin-system-upms";
    /**
     * 系统服务-数据字典
     */
    public static final String ORIGIN_SYSTEM_DICT = "origin-service-dict";
    /**
     * 系统服务-日志服务
     */
    public static final String ORIGIN_SYSTEM_LOGGER = "origin-service-logger";

    /**
     * 系统服务-基础信息
     */
    public static final String ORIGIN_SYSTEM_BASEINFO = "origin-system-baseinfo";


}
