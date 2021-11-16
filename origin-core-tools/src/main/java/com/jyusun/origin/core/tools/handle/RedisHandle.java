package com.jyusun.origin.core.tools.handle;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RedisHandle {

    private final RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, timeOut);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
