package com.jyusun.origin.thread.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文档库属性配置
 *
 * @author jyusun at 2021-7-7 13:33:21
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.thread-pool")
public class ThreadPoolProperties {
    /**
     * 启用
     */
    private Boolean enabled = true;

    /**
     * 核心线程数
     */
    private String threadNamePrefix = "Thread-Async-";

    /**
     * 核心线程数
     */
    private Integer corePoolSize = 10;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize = 50;

    /**
     * 队列容量
     */
    private Integer queueCapacity = 10;




}
