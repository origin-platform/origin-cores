package com.jyusun.origin.base.translate;

import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.base.translate.common.util.TranslateUtil;
import com.jyusun.origin.base.translate.desc.DescContext;
import com.jyusun.origin.base.translate.desc.handle.DescBooleanStrategy;
import com.jyusun.origin.base.translate.desc.handle.DescCustomStrategy;
import com.jyusun.origin.base.translate.desc.handle.DescExtStrategy;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.util.AssertUtil;
import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

/**
 * 作用描述： - 翻译缓存
 *
 * @author jyusun at 2022/6/09 18:07
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataTranslate {

    private final DescBooleanStrategy descBooleanStrategy;
    private final DescCustomStrategy descCustomStrategy;
    private final DescExtStrategy descExtStrategy;

    /**
     * 数据翻译
     *
     * @param result 结果集
     */
    public void resultHandle(Object result) {
        Class<?> clazz = TranslateUtil.getTranslateClass(result);
        AssertUtil.notNull(clazz, "数据值翻译：目标类不存在");
        // 获取翻译字段配置信息
        Map<String, Dict> dictMap = TranslateUtil.getTranslateAnnotation(clazz);
        if (result instanceof List<?>) {
            ((List<?>) result).forEach(o -> setDesc(o, dictMap));
        } else if (result instanceof PageObject<?>) {
            ((PageObject<?>) result).getRows().forEach(o -> setDesc(o, dictMap));
        } else {
            setDesc(result, dictMap);
        }
    }

    /**
     * 单个设置值
     *
     * @param obj          {@code Object} 单个对象
     * @param translateMap {@link Map<String,Dict>} 注解字段集合
     */
    public void setDesc(Object obj, Map<String, Dict> translateMap) {
        Class<?> clazz = obj.getClass();
        for (Map.Entry<String, Dict> entry : translateMap.entrySet()) {
            String fieldName = entry.getKey();
            Dict dict = entry.getValue();

            String valueField = dict.sourceField();

            PropertyDescriptor propertyDescriptor = BeanUtil.getPropertyDescriptor(clazz, valueField);
            AssertUtil.notNull(propertyDescriptor, "翻译属性不存在");
            // 获取翻译属性值
            Object propertyValue = BeanUtil.readPropertyValue(obj, propertyDescriptor);

            if (ObjectUtil.isNotEmpty(propertyValue)) {
                DescContext descContext;
                if (dict.customKv().length > 0) {
                    if (propertyDescriptor.getPropertyType() == Boolean.class
                            || propertyDescriptor.getPropertyType() == boolean.class) {
                        descContext = new DescContext(descBooleanStrategy);
                    } else {
                        descContext = new DescContext(descCustomStrategy);
                    }
                } else {
                    descContext = new DescContext(descExtStrategy);
                }
                String desc = descContext.getDesc(dict, propertyValue);
                BeanUtil.writePropertyValue(obj, fieldName, desc);
            }
        }
    }
}
