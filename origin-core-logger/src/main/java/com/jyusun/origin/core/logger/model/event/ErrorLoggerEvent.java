package com.jyusun.origin.core.logger.model.event;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.logger.model.dto.ErrorLoggerDTO;

public class ErrorLoggerEvent extends CoreEvent<ErrorLoggerDTO> {

    public ErrorLoggerEvent(ErrorLoggerDTO source) {
        super(source);
    }
}
