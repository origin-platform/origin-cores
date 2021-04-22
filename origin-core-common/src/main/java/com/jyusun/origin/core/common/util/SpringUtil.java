package com.jyusun.origin.core.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 实现对SpringContext管理
 *
 * @author jyusun
 */
@Slf4j
@Lazy(false)
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(String beanName) {
        return (T) getContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    public static ApplicationContext getContext() {
        if (ObjectUtil.isEmpty(applicationContext)) {
            throw new IllegalStateException("applicationContext属性为null,请检查是否注入了SpringUtil");
        }
        return applicationContext;
    }

    public static void publishEvent(ApplicationEvent event) {
        getContext().publishEvent(event);
    }

    /**
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }


}
