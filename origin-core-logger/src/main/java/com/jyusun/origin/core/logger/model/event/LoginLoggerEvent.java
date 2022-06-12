package com.jyusun.origin.core.logger.model.event;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.logger.model.dto.LoginLoggerDTO;

public class LoginLoggerEvent extends CoreEvent<LoginLoggerDTO> {

    public LoginLoggerEvent(LoginLoggerDTO source) {
        super(source);
    }
}
