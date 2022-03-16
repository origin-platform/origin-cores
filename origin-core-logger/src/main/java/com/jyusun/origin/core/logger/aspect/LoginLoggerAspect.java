package com.jyusun.origin.core.logger.aspect;


import com.jyusun.origin.core.common.result.AbstractResult;
import com.jyusun.origin.core.logger.annotation.LoginLogger;
import com.jyusun.origin.core.logger.common.util.PointUtil;
import com.jyusun.origin.core.logger.publisher.LoginLoggerPublisher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * 登录日志打印（如果需要可以通过异步处理落库）
 *
 * @author jyusun at 2019-08-07
 * @since 1.0.0
 */
@Slf4j
@Aspect
public class LoginLoggerAspect {

    /**
     * 切点前后环绕通知：输出请求信息 和 响应信息
     *
     * @param point       {@link ProceedingJoinPoint} 切点信息
     * @param loginLogger {@link LoginLogger } 切点注解
     * @return {@code Object} 请求处理结果
     */
    @Around("@annotation(loginLogger)")
    @SneakyThrows
    public AbstractResult<Object> doAround(ProceedingJoinPoint point, LoginLogger loginLogger) {
        // 获取类名
        String className = point.getTarget().getClass().getName();
        // 获取方法
        String method = point.getSignature().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        //执行方法
        AbstractResult<Object> result = (AbstractResult<Object>) point.proceed();
        //执行时长(毫秒)
        long timeCost = System.currentTimeMillis() - startTime;
        LoginLoggerPublisher.publishEvent(className, method,
                PointUtil.getParamsByPoint(point), loginLogger, startTime, timeCost);
        return result;
    }


}
