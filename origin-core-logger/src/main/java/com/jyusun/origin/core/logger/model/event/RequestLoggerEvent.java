package com.jyusun.origin.core.logger.model.event;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;

public class RequestLoggerEvent extends CoreEvent<RequestLoggerDTO> {

    public RequestLoggerEvent(RequestLoggerDTO source) {
        super(source);
    }
}
