package com.jyusun.origin.core.logger.event;

import com.jyusun.origin.core.logger.model.dto.UsualLoggerDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author jyusun at 2019-08-07
 */
public class UsualLoggerEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public UsualLoggerEvent(UsualLoggerDTO source) {
        super(source);
    }

}
