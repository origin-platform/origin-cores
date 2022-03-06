package com.jyusun.origin.core.logger.config;


import com.jyusun.origin.core.logger.aspect.WebLoggerAspect;
import com.jyusun.origin.core.logger.event.ErrorLoggerListener;
import com.jyusun.origin.core.logger.event.LoginLoggerListener;
import com.jyusun.origin.core.logger.event.RequestLoggerListener;
import com.jyusun.origin.core.logger.event.UsualLoggerListener;
import com.jyusun.origin.core.logger.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志工具自动配置
 *
 * @author jyusun at 2019/1/2 20:07
 * @since 1.0.0
 */
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class LoggerAutoConfiguration {

    /**
     * 系统日志切面
     *
     * @return {@link WebLoggerAspect}
     */
    @Bean
    public WebLoggerAspect webLoggerAspect() {
        return new WebLoggerAspect();
    }

    /**
     * 全局异常拦截
     *
     * @return {@link GlobalExceptionHandler}
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public LoginLoggerListener loginLoggerListener() {
        return new LoginLoggerListener();
    }

    @Bean
    public RequestLoggerListener requestLoggerListener() {
        return new RequestLoggerListener();
    }

    @Bean
    public UsualLoggerListener usualLoggerListener() {
        return new UsualLoggerListener();
    }

    @Bean
    public ErrorLoggerListener errorLoggerListener() {
        return new ErrorLoggerListener();
    }


}
