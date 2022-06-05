package com.jyusun.origin.core.logger.publisher;


import com.jyusun.origin.core.common.model.event.CoreEvent;
import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.LoginLogger;
import com.jyusun.origin.core.logger.common.enums.OperTypeEnum;
import com.jyusun.origin.core.logger.common.util.OutForUtil;
import com.jyusun.origin.core.logger.model.dto.LoginLoggerDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
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
public final class LoginLoggerPublisher {

    public static void publishEvent(String methodClass,
                                    String method,
                                    Map<String, Object> params,
                                    LoginLogger loginLogger,
                                    long startTime,
                                    long timeCost) {
        String title = StringUtil.hasText(loginLogger.title()) ? loginLogger.title() : loginLogger.operType().desc();

        HttpServletRequest request = WebUtil.getRequest();
        LoginLoggerDTO loginLoggerDTO = new LoginLoggerDTO();
        loginLoggerDTO
                .setUserAgent(OutForUtil.buildUserAgent(request))
                .setRemoteAddress(WebUtil.getIpAddr(request))
                .setLoginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                        ZoneId.systemDefault()))
                .setTimeCost(timeCost)
                .setServiceCode("")
                .setClassName(methodClass)
                .setMethodName(method)
                .setOperationType(OperTypeEnum.LOGIN.code())
                .setTitle(title);
        SpringUtil.publishEvent(new CoreEvent<>(loginLoggerDTO));
    }
}
