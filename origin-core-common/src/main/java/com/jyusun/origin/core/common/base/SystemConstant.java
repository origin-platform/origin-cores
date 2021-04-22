package com.jyusun.origin.core.common.base;

/**
 * <p>
 * 作用描述：系统常量类
 *
 * @author jyusun
 * @date 2020/11/23 17:29
 * @since 1.0.0
 */
public interface SystemConstant extends BaseConstants {

    /**
     * 扫描基础包路径
     */
    String BASE_PACKAGE = "com.jyusun.origin";

    /**
     * 扫描Mapper路径
     */
    String BASE_MAPPER = "com.jyusun.origin.**.mapper";

    /**
     * 扫描web路径
     */
    String BASE_WEB = "com.jyusun.origin";

    /**
     * 扫描FeignClient包路径
     */
    String BASE_FEIGN = "com.jyusun.origin.**.client.feign";
}
