package com.jyusun.origin.core.logger.publisher;

import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.logger.annotation.WebLogger;
import com.jyusun.origin.core.logger.common.util.OutForUtil;
import com.jyusun.origin.core.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.core.logger.model.event.ErrorLoggerEvent;
import com.jyusun.origin.core.logger.model.value.ServerValue;
import com.jyusun.origin.core.secure.common.util.SecureUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;


/**
 * 异常信息事件发送
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@Slf4j
@UtilityClass
public final class ErrorLoggerPublisher {

    public static void publishEvent(Throwable throwable, String className, String methodName,
                                    Map<String, Object> params, WebLogger webLogger, long startTime) {

        HttpServletRequest request = WebUtil.getRequest();
        String title = StringUtil.hasText(webLogger.value()) ? webLogger.value() :
                webLogger.operType().desc();

        ErrorLoggerDTO errDto = new ErrorLoggerDTO();
        errDto.setTitle(title)
                .setOperator(SecureUtil.getUser().getUserId())
                .setRequestTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime),
                        ZoneId.systemDefault()))
                .setErrorInfo(OutForUtil.buildError(throwable))
                .setRequestInfo(OutForUtil.buildRequest(request, params))
                .setServerInfo(new ServerValue())
                .setOperationType(webLogger.operType().code())
                .setServiceCode("")
                .setClassName(className)
                .setMethodName(methodName);
        SpringUtil.publishEvent(new ErrorLoggerEvent(errDto));
    }

}
