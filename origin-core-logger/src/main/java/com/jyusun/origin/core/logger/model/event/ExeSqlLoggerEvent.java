package com.jyusun.origin.core.logger.model.event;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.logger.model.dto.ExeSqlLoggerDTO;

/**
 * 执行SQL日志
 *
 * @author jyusun at 2022-06-08 21:01:22
 */
public class ExeSqlLoggerEvent extends CoreEvent<ExeSqlLoggerDTO> {

    public ExeSqlLoggerEvent(ExeSqlLoggerDTO source) {
        super(source);
    }
}
