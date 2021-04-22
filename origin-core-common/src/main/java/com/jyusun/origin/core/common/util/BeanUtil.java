package com.jyusun.origin.core.common.util;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 作用描述： JavaBean 操作工具
 *
 * @author jyusun at 2020/5/18 13:52
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil extends BeanUtils {

    /**
     * 实例化对象
     *
     * @param clazz {@code Class<T>} 类
     * @param <T>   {@link <T>} 泛型标记
     * @return 对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        return BeanUtils.instantiateClass(clazz);
    }

    /**
     * 复制对象中的值
     *
     * @param source      源
     * @param targetClass 目标类
     * @param <T>         目标类泛型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(targetClass, "Target must not be null");
        T bean = BeanUtil.newInstance(targetClass);
        BeanUtils.copyProperties(source, bean);
        return bean;
    }

    /**
     * 复制对象中的值
     *
     * @param targetClass 目标类
     * @return 目标对象
     */
    public static <T, R> List<R> copyList(Collection<T> sources, Class<R> targetClass) {
        Assert.notNull(targetClass, "Target must not be null");
        List<R> list = Lists.newArrayListWithCapacity(sources.size());
        sources.forEach(data -> {
            R bean = BeanUtil.newInstance(targetClass);
            BeanUtils.copyProperties(data, bean);
            list.add(bean);
        });
        return list;
    }

    /**
     * 读取属性值
     *
     * @param object             对象
     * @param propertyDescriptor 属性信息
     * @return 属性值
     */
    public static Object readPropertyValue(Object object, PropertyDescriptor propertyDescriptor) {

        Method sourceReadMethod = propertyDescriptor.getReadMethod();
        Object translateValue = null;
        try {
            translateValue = sourceReadMethod.invoke(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return translateValue;
    }

    /**
     * 根据对象和属性名称写入属性值
     *
     * @param object 对象
     * @param value  属性值
     */
    public static void writePropertyValue(Object object, String propertyName, String value) {
        PropertyDescriptor targetPropertyDescriptor = BeanUtils.getPropertyDescriptor(object.getClass(), propertyName);
        Method writeMethod = Objects.requireNonNull(targetPropertyDescriptor).getWriteMethod();
        try {
            writeMethod.invoke(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
