package com.jyusun.origin.core.logger.publisher;


import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.common.util.LoggerUtil;
import com.jyusun.origin.core.logger.event.LoginLoggerEvent;
import com.jyusun.origin.core.logger.event.RequestLoggerEvent;
import com.jyusun.origin.core.logger.model.dto.LoginLoggerDTO;
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
                .map(request -> LoggerUtil.buildRequest(request, params))
                .orElse(new RequestValue());

        switch (webLogger.operType()) {
            case LOGIN:
                LoginLoggerDTO loginLoggerDTO = new LoginLoggerDTO();
                loginLoggerDTO.setRemoteAddress(WebUtil.getIpAddr())
                        .setLoginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                                ZoneId.systemDefault()))
                        .setTimeCost(timeCost)
                        .setOperationType(webLogger.operType().code())
                        .setServiceCode("");

                SpringUtil.publishEvent(new LoginLoggerEvent(loginLoggerDTO));
                break;

            default:
                RequestLoggerDTO requestLoggerDTO = new RequestLoggerDTO();
                String title = StringUtil.hasText(webLogger.value()) ? webLogger.value() :
                        webLogger.operType().label();
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

                SpringUtil.publishEvent(new RequestLoggerEvent(requestLoggerDTO));
        }
    }
}
