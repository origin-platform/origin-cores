package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 集合工具类
 *
 * @author jyusun at 2022-04-14 11:09:14
 */
@UtilityClass
public class CollectionUtil extends CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static String[] toArray(@Nullable Collection<String> collection) {
        return Objects.requireNonNull(collection).toArray(new String[0]);

    }
}
