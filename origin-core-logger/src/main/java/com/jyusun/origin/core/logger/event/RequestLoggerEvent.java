package com.jyusun.origin.core.logger.event;

import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 请求日志事件
 *
 * @author jyusun at 2019-08-08
 */
public class RequestLoggerEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public RequestLoggerEvent(RequestLoggerDTO source) {
        super(source);
    }

}
