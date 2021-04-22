package com.jyusun.origin.core.tools.idempotent;

import com.jyusun.origin.core.common.base.ResultConstant;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.tools.handle.RedisHandle;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@AllArgsConstructor
public class AspectIdempotent {

    private final RedisHandle redisService;

    /**
     * 定义切点
     *
     * @param apiIdempotent {@link ApiIdempotent} 幂等注解
     */
    @Pointcut("@annotation(apiIdempotent)")
    public void doIdempotent(ApiIdempotent apiIdempotent) {
    }

    /**
     * 环绕通知
     */
    @Around("doIdempotent(apiIdempotent)")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint, ApiIdempotent apiIdempotent) throws Throwable {
        String token = Objects.requireNonNull(WebUtil.getRequest()).getHeader("token");
        boolean del = redisService.delete(token);
        // 如果成功删除，证明token存在，否则证明没有获取到token或已过期 不要先查在删会影响性能
        if (!del) {
            throw new BusinessException(ResultConstant.MESSAGE_INTERNAL_SERVER_ERROR, apiIdempotent.errorMsg());
        }
        //继续执行
        return proceedingJoinPoint.proceed();
    }
}
