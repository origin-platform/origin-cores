package com.jyusun.origin.core.logger.event;


import com.jyusun.origin.core.logger.model.dto.ErrorLoggerDTO;
import org.springframework.context.ApplicationEvent;


/**
 * 错误日志事件
 *
 * @author jyusun at 2019-08-07
 */
public class ErrorLoggerEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public ErrorLoggerEvent(ErrorLoggerDTO source) {
        super(source);
    }

}
