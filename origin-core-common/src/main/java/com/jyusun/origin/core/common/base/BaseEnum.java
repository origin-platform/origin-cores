package com.jyusun.origin.core.common.base;

/**
 * 枚举Key Value 接口
 * <p>
 * 作用描述：基础枚举 KV 接口
 *
 * @author jyusun
 * @date 2021/3/24 11:25
 * @since 1.0.0
 */
public interface BaseEnum {

    /**
     * 值
     *
     * @return str
     */
    String code();

    /**
     * 标签
     *
     * @return str
     */
    String label();
}
