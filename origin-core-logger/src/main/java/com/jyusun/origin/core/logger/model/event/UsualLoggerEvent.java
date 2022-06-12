package com.jyusun.origin.core.logger.model.event;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.logger.model.dto.UsualLoggerDTO;

public class UsualLoggerEvent extends CoreEvent<UsualLoggerDTO> {

    public UsualLoggerEvent(UsualLoggerDTO source) {
        super(source);
    }
}
