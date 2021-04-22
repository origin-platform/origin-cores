package com.jyusun.origin.core.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * 工具
 * <p>
 * 作用描述：UUID操作
 * </p>
 *
 * @author jyusun at 2020/7/2 14:49
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UuidUtil {

    /**
     * UUID 32位 字符串 去横线
     *
     * @return {@code String }
     */
    public static String generateUuidStr32() {
        return UUID.randomUUID().toString().replaceAll(StringUtil.DASHED, StringUtil.EMPTY);
    }

    /**
     * UUID 36位 字符串
     *
     * @return {@code String }
     */
    public static String generateUuidStr36() {
        return UUID.randomUUID().toString();
    }

}
