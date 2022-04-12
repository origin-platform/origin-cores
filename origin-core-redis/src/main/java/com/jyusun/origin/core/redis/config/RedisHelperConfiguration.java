package com.jyusun.origin.core.redis.config;

import com.jyusun.origin.core.redis.helper.RedisHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;

/**
 * Redis 通用配置
 *
 * @author jyusun
 */
@Configuration
@RequiredArgsConstructor
public class RedisHelperConfiguration {

    private final RedisTemplate<String, Serializable> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    @Bean
    public RedisHelper redisHelper() {
        return new RedisHelper(redisTemplate, stringRedisTemplate);
    }
}