package com.jyusun.origin.core.tools.translate.aspect;

import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.model.page.PageObject;
import com.jyusun.origin.core.tools.translate.annotation.TranslateParam;
import com.jyusun.origin.core.tools.translate.annotation.TranslateResult;
import com.jyusun.origin.core.tools.translate.handle.TranslateHandle;
import com.jyusun.origin.core.tools.translate.handle.TranslateUtils;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数据字典切面
 *
 * @author sun
 */
@Aspect
@Component
@AllArgsConstructor
public class TranslateAspect {

    private final TranslateHandle translateHandle;

    /**
     * 定义切点
     *
     * @param translateResult {@link TranslateHandle} 翻译注解
     */
    @Pointcut("@annotation(translateResult)")
    public void doTranslate(TranslateResult translateResult) {
    }

    @Around("@annotation(translateResult)")
    public Object translation(ProceedingJoinPoint proceedingJoinPoint, TranslateResult translateResult)
            throws Throwable {

        Object result = proceedingJoinPoint.proceed();
        // 获取方法返回值
        if (Objects.isNull(result)) {
            return null;
        }
        Class<?> clazz = TranslateUtils.getTranslateClass(result);

        Assert.notNull(clazz, "数据值翻译：目标类不存在");
        // 获取翻译字段配置信息
        Map<String, TranslateParam> translateMap = TranslateUtils.getTranslateAnnotation(clazz);
        // 无注解标记 直接返回结果集
        if (translateMap.isEmpty()) {
            return result;
        }
        this.translateData(result, translateMap);
        return result;
    }

    /**
     * 数据翻译
     *
     * @param result       结果集
     * @param translateMap {@link Map<String,TranslateParam>} 参数
     */
    private void translateData(Object result, Map<String, TranslateParam> translateMap) {
        if (result instanceof List<?>) {
            ((List<?>) result).forEach(o -> sign(o, translateMap));
        } else if (result instanceof PageObject<?>) {
            ((PageObject<?>) result).getRows().forEach(o -> sign(o, translateMap));
        } else {
            sign(result, translateMap);
        }
    }

    /**
     * 单个设置值
     *
     * @param obj          {@code Object} 单个对象
     * @param translateMap {@link Map<String, TranslateParam>} 注解字段集合
     */
    private void sign(Object obj, Map<String, TranslateParam> translateMap) {

        for (Map.Entry<String, TranslateParam> entry : translateMap.entrySet()) {
            String fieldName = entry.getKey();
            TranslateParam param = entry.getValue();

            String valueField = param.valueField();

            PropertyDescriptor propertyDescriptor = BeanUtil.getPropertyDescriptor(obj.getClass(), valueField);
            Assert.notNull(propertyDescriptor, "翻译属性不存在");
            // 获取翻译属性值
            Object propertyValue = BeanUtil.readPropertyValue(obj, propertyDescriptor);

            String label = StringUtil.EMPTY;
            if (Objects.nonNull(propertyValue)) {
                if (param.customLabel().length > 0) {
                    if (propertyDescriptor.getPropertyType() == Boolean.class
                            || propertyDescriptor.getPropertyType() == boolean.class) {
                        label = TranslateUtils.findBooleanLabel(param, propertyValue);
                    } else if (propertyDescriptor.getPropertyType() == String.class
                            || propertyDescriptor.getPropertyType() == char.class) {
                        label = TranslateUtils.findCustomLabel(param, propertyValue);
                    } else if (propertyDescriptor.getPropertyType() == Integer.class
                            || propertyDescriptor.getPropertyType() == int.class) {
                        label = TranslateUtils.findCustomLabel(param, propertyValue);
                    }
                } else {
                    label = translateHandle.queryTranslate(param, propertyValue);
                }
            }
            if (StringUtil.isEmpty(label)) {
                label = String.valueOf(propertyValue);
            }
            BeanUtils.getWriteMethodParameter(propertyDescriptor);
            BeanUtil.writePropertyValue(obj, fieldName, label);
        }
    }

}