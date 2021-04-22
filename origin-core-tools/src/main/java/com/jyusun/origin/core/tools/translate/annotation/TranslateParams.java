package com.jyusun.origin.core.tools.translate.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 值翻译
 * <p>
 * 作用描述：方法数据值翻译
 * </p>
 *
 * @author jyusun at 2020/6/1 14:52
 * @since 1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslateParams {

    /** 翻译参数 */
    TranslateParam value();

}
