package com.jyusun.origin.core.logger.aspect;


import com.jyusun.origin.core.common.result.AbstractResult;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.publisher.RequestLoggerPublisher;
import com.jyusun.origin.core.logger.common.util.PointUtil;
import com.jyusun.origin.core.logger.publisher.ErrorLoggerPublisher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;


/**
 * 操作日志打印（如果需要可以通过异步处理落库）
 *
 * @author jyusun at 2019-08-07
 * @since 1.0.0
 */
@Slf4j
@Aspect
public class WebLoggerAspect {

    /**
     * 切点前后环绕通知：输出请求信息 和 响应信息
     *
     * @param point     {@link ProceedingJoinPoint} 切点信息
     * @param webLogger {@link WebLogger } 切点注解
     * @return {@code Object} 请求处理结果
     */
    @Around("@annotation(webLogger)")
    @SneakyThrows
    public AbstractResult<Object> doAround(ProceedingJoinPoint point, WebLogger webLogger) {
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
        RequestLoggerPublisher.publishEvent(className, method,
                PointUtil.getParamsByPoint(point), webLogger, startTime, timeCost);
        return result;
    }

    /**
     * 切点抛出异常后：输出请求信息
     *
     * @param joinPoint
     * @param webLogger {@link WebLogger } 切点注解
     * @param throwable {@link Throwable } 异常信息 通过统一异常处理拦截 不在此处处理
     */
    @AfterThrowing(pointcut = "@annotation(webLogger)", throwing = "throwable")
    public void doAfterThrow(JoinPoint joinPoint, WebLogger webLogger, Throwable throwable) {
        // 获取类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法
        String method = joinPoint.getSignature().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        //记录日志
        Map<String, Object> params = PointUtil.getParamsByJoinPoint(joinPoint);
        ErrorLoggerPublisher.publishEvent(throwable,className, method, params, webLogger, startTime);
    }


}
