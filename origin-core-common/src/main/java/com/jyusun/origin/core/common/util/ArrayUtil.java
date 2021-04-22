package com.jyusun.origin.core.common.util;

import com.google.common.collect.Lists;
import com.jyusun.origin.core.common.lang.Editor;
import com.jyusun.origin.core.common.lang.Filter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 作用描述： - 数组操作工具类
 *
 * @author jyusun at 2020/1/5 16:39
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ArrayUtil extends ArrayUtils {

    /**
     * 新建一个空数组
     *
     * @param <T>           数组元素类型
     * @param componentType 元素类型
     * @param newSize       大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    /**
     * 过滤<br>
     * 过滤过程通过传入的Editor实现来返回需要的元素内容，这个Editor实现可以实现以下功能：
     *
     * <pre>
     * 1、过滤出需要的对象，如果返回null表示这个元素对象抛弃
     * 2、修改元素对象，返回集合中为修改后的对象
     * </pre>
     *
     * @param <T>    数组元素类型
     * @param array  数组
     * @param editor 编辑器接口
     * @return 过滤后的数组
     */
    public static <T> T[] filter(T[] array, Editor<T> editor) {
        ArrayList<T> list = Lists.newArrayListWithCapacity(array.length);
        T modified;
        for (T t : array) {
            modified = editor.edit(t);
            if (ObjectUtil.isNotEmpty(modified)) {
                list.add(modified);
            }
        }
        return list.toArray(Arrays.copyOf(array, list.size()));
    }

    /**
     * 过滤<br>
     * 过滤过程通过传入的Filter实现来过滤返回需要的元素内容，这个Filter实现可以实现以下功能：
     *
     * <pre>
     * 1、过滤出需要的对象，{@link Filter#accept(Object)}方法返回true的对象将被加入结果集合中
     * </pre>
     *
     * @param <T>    数组元素类型
     * @param array  数组
     * @param filter 过滤器接口，用于定义过滤规则，null表示不过滤，返回原数组
     * @return 过滤后的数组
     */
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        if (null == filter) {
            return array;
        }

        final ArrayList<T> list = Lists.newArrayListWithCapacity(array.length);
        for (T t : array) {
            if (filter.accept(t)) {
                list.add(t);
            }
        }
        final T[] result = newArray(array.getClass().getComponentType(), list.size());
        return list.toArray(result);
    }

}
