package com.jyusun.origin.core.logger.event;

import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.EventObject;


/**
 * 接口日志 异步监听事件
 *
 * @author jyusun at 2019-08-08
 */
@Slf4j
public class RequestLoggerListener implements LoggerListener {

    @Async
    @Order
    @EventListener(RequestLoggerEvent.class)
    @Override
    public void eventHandle(EventObject event) {
        RequestLoggerDTO loggerDTO = (RequestLoggerDTO) event.getSource();
        log.info("==== 请求日志：{}", loggerDTO);
    }
}
