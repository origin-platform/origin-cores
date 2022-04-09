package com.jyusun.origin.core.logger.event;


import com.alibaba.fastjson.JSON;
import com.jyusun.origin.core.logger.common.util.OutFormatUtil;
import com.jyusun.origin.core.logger.model.dto.ErrorLoggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.EventObject;

/**
 * 异步监听错误日志事件
 *
 * @author jyusun at 2019-08-07
 */
@Slf4j
public class ErrorLoggerListener implements LoggerListener {

    @Async
    @Order
    @EventListener(ErrorLoggerEvent.class)
    @Override
    public void eventHandle(EventObject event) {
        ErrorLoggerDTO loggerDTO = (ErrorLoggerDTO) event.getSource();
        // 输出本地日志
        String loggerFormat = OutFormatUtil.buildErrorReqMessage(loggerDTO.getTitle(),
                String.valueOf(loggerDTO.getOperator()),
                loggerDTO.getRequestTime(),
                JSON.toJSONString(loggerDTO));
        log.warn(loggerFormat);
    }

}
