package com.jyusun.origin.core.logger.event;

import com.alibaba.fastjson.JSON;
import com.jyusun.origin.core.logger.common.util.OutFormatUtil;
import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.EventObject;


/**
 * 登录日志 异步监听事件
 *
 * @author jyusun at 2019-08-08
 */
@Slf4j
public class LoginLoggerListener implements LoggerListener {


    @Async
    @Order
    @EventListener(LoginLoggerEvent.class)
    @Override
    public void eventHandle(EventObject event) {
        RequestLoggerDTO loggerDTO = (RequestLoggerDTO) event.getSource();
        // 输出本地日志
        String loggerFormat = OutFormatUtil.buildReqMessage(loggerDTO.getTitle(),
                String.valueOf(loggerDTO.getOperator()),
                loggerDTO.getRequestTime(),
                loggerDTO.getTimeCost(),
                JSON.toJSONString(loggerDTO));
        log.info(loggerFormat);
    }
}
