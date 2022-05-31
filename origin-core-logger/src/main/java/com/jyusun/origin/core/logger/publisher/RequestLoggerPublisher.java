package com.jyusun.origin.core.logger.publisher;


import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.common.util.OutForUtil;
import com.jyusun.origin.core.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.core.logger.model.value.RequestValue;
import com.jyusun.origin.core.logger.model.value.ServerValue;
import com.jyusun.origin.core.secure.common.util.SecureUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

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
        // 请求信息
        RequestValue requestValue = Optional.ofNullable(WebUtil.getRequest())
                .map(request -> OutForUtil.buildRequest(request, params))
                .orElse(new RequestValue());

        RequestLoggerDTO requestLoggerDTO = new RequestLoggerDTO();
        String title = StringUtil.hasText(webLogger.value()) ? webLogger.value() :
                webLogger.operType().desc();
        requestLoggerDTO.setTitle(title)
                .setOperator(SecureUtil.getUser().getUserId())
                .setRequestTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                        ZoneId.systemDefault()))
                .setTimeCost(timeCost)
                .setRequestValue(requestValue)
                .setServerValue(new ServerValue())
                .setServiceCode("")
                .setOperationType(webLogger.operType().code())
                .setClassName(methodClass)
                .setMethodName(method);

        SpringUtil.publishEvent(new CoreEvent<>(requestLoggerDTO));
    }
}
