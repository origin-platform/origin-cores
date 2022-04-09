package com.jyusun.origin.core.logger.publisher;


import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.LoginLogger;
import com.jyusun.origin.core.logger.event.LoginLoggerEvent;
import com.jyusun.origin.core.logger.model.value.RequestValue;
import com.jyusun.origin.core.logger.common.util.OutFormatUtil;
import com.jyusun.origin.core.logger.model.dto.LoginLoggerDTO;
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
public final class LoginLoggerPublisher {

    public static void publishEvent(String methodClass,
                                    String method,
                                    Map<String, Object> params,
                                    LoginLogger loginLogger,
                                    long startTime,
                                    long timeCost) {
        // 请求信息
        RequestValue requestValue = Optional.ofNullable(WebUtil.getRequest())
                .map(request -> OutFormatUtil.buildRequest(request, params))
                .orElse(new RequestValue());

        LoginLoggerDTO loginLoggerDTO = new LoginLoggerDTO();
        loginLoggerDTO.setRemoteAddress(WebUtil.getIpAddr())
                .setLoginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                        ZoneId.systemDefault()))
                .setTimeCost(timeCost)
                .setOperationType(loginLogger.operType().code())
                .setServiceCode("");

        SpringUtil.publishEvent(new LoginLoggerEvent(loginLoggerDTO));

    }
}
