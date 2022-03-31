package com.jyusun.origin.thread.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务配置
 *
 * @author jyusun at 2022-1-30 11:24:43
 */
@Slf4j
@Import(ThreadPoolConfiguration.class)
@EnableAsync
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "origin-system.thread-pool.enabled", matchIfMissing = true)
public class AsyncTaskConfiguration implements AsyncConfigurer {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("#### Error Occurs in async method:{}", ex.getMessage());
        };
    }
}
