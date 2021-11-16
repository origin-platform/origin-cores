package com.jyusun.origin.core.tools.lock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 作用描述：Redis工具的封装
 *
 * @author jyusun
 * @date 2021/2/23 14:26
 * @since 1.0.0
 */
@Component
@AllArgsConstructor
public class RedisUtil {

    @Qualifier("redisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存
     *
     * @param key  {@code String} 键
     * @param data {@code Object} 值
     * @param time {@code Long} 超时时间
     */
    public void set(String key, Object value, Long time) {
        redisTemplate.opsForValue().set(key, value, time);
    }

    /**
     * 删除缓存
     *
     * @param key {@code String} 键
     * @return {@code Boolean} true-删除成功，false-删除失败
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
