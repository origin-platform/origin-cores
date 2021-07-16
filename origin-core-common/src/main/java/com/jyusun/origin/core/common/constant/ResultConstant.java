package com.jyusun.origin.core.common.constant;

import lombok.experimental.UtilityClass;

/**
 * 系统通用响应状态码
 * <p>
 * 作用描述：操作结果响应信息
 *
 * @author jyusun
 * @date 2019/8/8 10:52
 * @since 1.0.0
 */
@UtilityClass
public class ResultConstant implements BaseConstants {

    /**
     * -1 未知的异常信息
     */
    public static final String MESSAGE_UNKNOWN_ANOMALY = "-1";
    /**
     * S00200-操作成功
     */
    public static final String MESSAGE_OK = "S00200";
    /**
     * S00201-操作创建成功
     */
    public static final String MESSAGE_CREATE = "S00201";
    /**
     * S00400-请求错误
     */
    public static final String MESSAGE_BAD_REQUEST = "S00400";
    /**
     * S00401-无权限
     */
    public static final String MESSAGE_UNAUTHORIZED = "S00401";
    /**
     * S00403-请求被拒绝
     */
    public static final String MESSAGE_FORBIDDEN = "S00403";
    /**
     * S00404-请求不存在
     */
    public static final String MESSAGE_NOT_FOUND = "S00404";
    /**
     * S00405-请求方式错误
     */
    public static final String MESSAGE_METHOD_NOT_ALLOWED = "S00405";
    /**
     * S00415-不支持当前请求类型
     */
    public static final String MESSAGE_UNSUPPORTED_MEDIA_TYPE = "S00415";
    /**
     * S00500-系统繁忙，请稍后再试
     */
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "S00500";


}
