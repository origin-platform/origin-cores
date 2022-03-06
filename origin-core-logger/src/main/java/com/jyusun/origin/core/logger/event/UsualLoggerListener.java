package com.jyusun.origin.core.logger.event;


import com.jyusun.origin.core.logger.model.dto.UsualLoggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.EventObject;

/**
 * 异步监听日志事件
 *
 * @author jyusun at 2019-08-07
 */
@Slf4j
@Component
public class UsualLoggerListener implements LoggerListener {


    @Async
    @Order
    @EventListener(UsualLoggerEvent.class)
    @Override
    public void eventHandle(EventObject event) {
        UsualLoggerDTO logUsual = (UsualLoggerDTO) event.getSource();
        log.info("==== 常规日志：{}", logUsual);
    }
}
