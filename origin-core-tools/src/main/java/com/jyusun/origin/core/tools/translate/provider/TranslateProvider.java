package com.jyusun.origin.core.tools.translate.provider;

/**
 * 数据值来源
 *
 * <p>
 * 作用描述：自定义值翻译
 * </p>
 *
 * @author jyusun at 2020/6/1 14:57
 * @since 1.0.0
 */
public interface TranslateProvider {

    /**
     * 值翻译
     *
     * @param type  类型
     * @param value 键值
     * @return 标签
     */
    String findLabel(String type, Object value);

}
