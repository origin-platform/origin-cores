package com.jyusun.origin.core.logger.publisher;

import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.logger.model.dto.ExeSqlLoggerDTO;
import com.jyusun.origin.core.logger.model.event.ExeSqlLoggerEvent;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


/**
 * SQL 事件发布
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@Slf4j
@UtilityClass
public final class ExeSqlLoggerPublisher {

    public static void publishEvent() {
        SpringUtil.publishEvent(new ExeSqlLoggerEvent(new ExeSqlLoggerDTO()));
    }

}
