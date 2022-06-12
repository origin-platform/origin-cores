package com.jyusun.origin.core.logger.publisher;


import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.common.util.OutForUtil;
import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.core.logger.model.event.RequestLoggerEvent;
import com.jyusun.origin.core.logger.model.value.ServerValue;
import com.jyusun.origin.core.secure.common.util.SecureUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * API日志信息事件发送
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@Slf4j
@UtilityClass
public final class RequestLoggerPublisher {

    public static void publishEvent(String methodClass,
                                    String method,
                                    Map<String, Object> params,
                                    WebLogger webLogger,
                                    long startTime,
                                    long timeCost) {

        RequestLoggerDTO requestLoggerDTO = new RequestLoggerDTO();
        String title = StringUtil.hasText(webLogger.value()) ? webLogger.value() :
                webLogger.operType().desc();
        requestLoggerDTO
                .setUserId(SecureUtil.getUser().getUserId())
                .setRequestTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                        ZoneId.systemDefault()))
                .setTimeCost(timeCost)
                .setRequestInfo(OutForUtil.buildRequest(WebUtil.getRequest(), params))
                .setServerInfo(new ServerValue())
                .setServiceCode("")
                .setOperationType(webLogger.operType().code())
                .setTitle(title)
                .setClassName(methodClass)
                .setMethodName(method);

        SpringUtil.publishEvent(new RequestLoggerEvent(requestLoggerDTO));
    }
}
