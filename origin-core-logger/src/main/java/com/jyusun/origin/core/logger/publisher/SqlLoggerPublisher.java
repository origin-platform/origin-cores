package com.jyusun.origin.core.logger.publisher;

import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.common.util.OutForUtil;
import com.jyusun.origin.core.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.core.logger.model.dto.SqlLoggerDTO;
import com.jyusun.origin.core.logger.model.value.RequestValue;
import com.jyusun.origin.core.logger.model.value.ServerValue;
import com.jyusun.origin.core.secure.common.util.SecureUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;


/**
 * SQL 事件发布
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@Slf4j
@UtilityClass
public final class SqlLoggerPublisher {

    public static void publishEvent() {
        SpringUtil.publishEvent(new CoreEvent<>(new SqlLoggerDTO()));
    }

}
