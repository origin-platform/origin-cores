package com.jyusun.origin.base.logger.config;


import com.jyusun.origin.base.logger.aspect.WebLoggerAspect;
import com.jyusun.origin.base.logger.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志工具自动配置
 *
 * @author jyusun at 2019/1/2 20:07
 * @since 1.0.0
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class LoggerAutoConfiguration {
    /**
     * 系统日志切面
     *
     * @return {@link WebLoggerAspect}
     */
    @Bean
    public WebLoggerAspect sysLoggerAspect() {
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
}
