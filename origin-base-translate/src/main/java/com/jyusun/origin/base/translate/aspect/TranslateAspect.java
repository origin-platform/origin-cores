package com.jyusun.origin.base.translate.aspect;

import com.jyusun.origin.base.translate.DataTranslate;
import com.jyusun.origin.core.common.util.ObjectUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据字典切面
 *
 * @author jyusun at 2022-06-09 18:33:39
 */
@Aspect
@Component
@AllArgsConstructor
public class TranslateAspect {

    private final DataTranslate dataTranslate;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.jyusun.origin.base.translate.annotation.ResultTranslate)")
    public void resultTranslate() {
    }

    @Around("resultTranslate()")
    public Object translation(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {

        Object result = proceedingJoinPoint.proceed();
        // 获取方法返回值
        if (ObjectUtil.isNotEmpty(result)) {
            this.dataTranslate.resultHandle(result);
        }
        return result;
    }


}
