package com.jyusun.origin.core.logger.event;

import com.jyusun.origin.core.logger.model.dto.LoginLoggerDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 登录日志事件
 *
 * @author jyusun at 2019-08-08
 */
public class LoginLoggerEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public LoginLoggerEvent(LoginLoggerDTO source) {
        super(source);
    }

}
