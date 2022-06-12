package com.jyusun.origin.core.logger.common.util;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.common.util.SpringUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LoggerPublisher {

    public static void publishEvent(CoreEvent<?> coreEvent) {
        SpringUtil.publishEvent(coreEvent);
    }
}
