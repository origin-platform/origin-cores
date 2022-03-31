package com.jyusun.origin.thread.config;

import com.jyusun.origin.thread.config.props.ThreadPoolProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务配置
 *
 * @author jyusun at 2022-1-30 11:24:43
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(value = "origin-system.thread-pool.enabled", matchIfMissing = true)
public class AsyncTaskConfiguration implements AsyncConfigurer {

    private final ThreadPoolProperties threadPoolProperties;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //设置核心线程数
        threadPool.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        //设置最大线程数
        threadPool.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        //线程池所使用的缓冲队列
        threadPool.setQueueCapacity(10);
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);
        // 线程名称前缀
        threadPool.setThreadNamePrefix(threadPoolProperties.getThreadNamePrefix());
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }
    // ThredPoolTaskExcutor的处理流程
    // 当池子大小小于corePoolSize，就新建线程，并处理请求
    // 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
    // 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
    // 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
