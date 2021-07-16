package com.jyusun.origin.base.logger.aspect;


import com.jyusun.origin.base.logger.annotation.WebLogger;
import com.jyusun.origin.base.logger.common.util.LoggerUtil;
import com.jyusun.origin.base.logger.common.util.PointUtil;
import com.jyusun.origin.base.logger.entity.LoggerEntity;
import com.jyusun.origin.core.common.util.WebUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


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
     * @param point
     * @param webLogger {@link WebLogger } 切点注解
     * @return
     */
    @Around("@annotation(webLogger)")
    @SneakyThrows
    public Object doAround(ProceedingJoinPoint point, WebLogger webLogger) {
        // 获取类名
        String className = point.getTarget().getClass().getName();
        // 获取方法
        String method = point.getSignature().getName();
        // 开始时间
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long timeConsuming = System.currentTimeMillis() - beginTime;

        //记录日志
        LoggerEntity logger = new LoggerEntity();
        logger.setTitle(webLogger.value())
                .setBeginTime(beginTime)
                .setTimeConsuming(timeConsuming);
        log.info(LoggerUtil.logMessageRequest(WebUtil.getRequest(), logger,
                PointUtil.getRequestParamsByProceedingJoinPoint(point), className, method,
                webLogger.isOutResult() ? result : null));
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
        //记录日志
        LoggerEntity logger = new LoggerEntity();
        logger.setTitle(webLogger.value())
                .setBeginTime(System.currentTimeMillis());
        log.info(LoggerUtil.logMessageRequestError(WebUtil.getRequest(), logger,
                PointUtil.getRequestParamsByJoinPoint(joinPoint), className, method));
    }


}
