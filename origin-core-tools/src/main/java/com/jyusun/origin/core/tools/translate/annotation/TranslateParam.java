package com.jyusun.origin.core.tools.translate.annotation;

import com.jyusun.origin.core.tools.translate.provider.DictTranslateProvider;
import com.jyusun.origin.core.tools.translate.provider.TranslateProvider;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 值翻译
 * <p>
 * 作用描述：数据值翻译
 * </p>
 *
 * @author jyusun at 2020/6/1 14:52
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslateParam {

    /**
     * 翻译指定的翻译字典类型编码
     *
     * @return String
     */
    String type() default "";

    /**
     * 翻译属性
     *
     * @return String
     */
    @AliasFor("valueField")
    String value() default "";

    /**
     * 翻译属性
     *
     * @return String
     */
    @AliasFor("value")
    String valueField() default "";

    /**
     * 自定义翻译标签
     * 如：{true-真,false-假} or {0-女,1-男,2-未知} or {zhangsan-张三,lisi-李四}
     */
    String[] customLabel() default {};

    /**
     * 分隔符
     * <p>
     * 作用描述：分割值单独翻译 test,test1
     * </p>
     *
     * @return String
     */
    String split() default "";

    /**
     * 翻译数据来源：默认来源于数据字典
     */
    Class<? extends TranslateProvider> source() default DictTranslateProvider.class;

}
