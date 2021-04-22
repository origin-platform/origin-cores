package com.jyusun.origin.core.tools.translate.handle;

import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.model.page.PageObject;
import com.jyusun.origin.core.tools.translate.annotation.TranslateParam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 翻译处理工具
 *
 * <p>
 * 作用描述：
 * </p>
 *
 * @author jyusun at 2020/6/1 16:41
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TranslateUtils {

    /**
     * 获取需要翻译的注解集合
     *
     * @param clazz 翻译类
     * @return {@link Map <String, TranslateParam>} 翻译属性集合
     */
    public static Map<String, TranslateParam> getTranslateAnnotation(Class<?> clazz) {

        Field[] fields = FieldUtils.getAllFields(clazz);
        return Arrays.stream(fields).filter(field -> field.isAnnotationPresent(TranslateParam.class))
                .collect(Collectors.toMap(Field::getName, filed -> filed.getAnnotation(TranslateParam.class)));
    }

    /**
     * 获取布尔值对应的标签
     *
     * @param param         {@link TranslateParam} 翻译参数
     * @param propertyValue {@code Object} 属性值
     * @return {@code String}
     */
    public static String findBooleanLabel(TranslateParam param, Object propertyValue) {
        Map<Boolean, String> labelMap = Arrays.stream(param.customLabel()).map(elem -> elem.split(StringUtil.DASHED))
                .filter(elem -> elem.length == 2)
                .collect(Collectors.toMap(e -> Boolean.parseBoolean(e[0]), e -> String.valueOf(e[1])));
        return labelMap.get(Boolean.parseBoolean(String.valueOf(propertyValue)));
    }

    /**
     * 获取自定义值对应的标签
     *
     * @param param         {@link TranslateParam} 翻译参数
     * @param propertyValue {@code Object} 属性值
     * @return {@code String}
     */
    public static String findCustomLabel(TranslateParam param, Object propertyValue) {
        Map<String, String> labelMap = Arrays.stream(param.customLabel()).map(elem -> elem.split(StringUtil.DASHED))
                .collect(Collectors.toMap(e -> String.valueOf(e[0]), e -> String.valueOf(e[1])));
        return labelMap.get(String.valueOf(propertyValue));
    }


    /**
     * 获取翻译目标类
     * <p>
     * 作用描述：仅支持
     * </p>
     *
     * @param result {@link List} or {@link PageObject} or {@code Object} 单个对象
     * @return {@code Class<?>}
     */
    public static Class<?> getTranslateClass(Object result) {
        Object obj;
        if (result instanceof List) {
            List<?> results = ((List<?>) result);
            if (CollectionUtils.isEmpty(results)) {
                return null;
            }
            obj = results.get(0);
        } else if (result instanceof PageObject<?>) {
            List<?> rows = ((PageObject<?>) result).getRows();
            if (CollectionUtils.isEmpty(rows)) {
                return null;
            }
            obj = rows.get(0);
        } else {
            obj = result;
        }
        return obj.getClass();
    }

}
