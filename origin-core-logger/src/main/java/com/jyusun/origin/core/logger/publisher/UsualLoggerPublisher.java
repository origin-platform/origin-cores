package com.jyusun.origin.core.logger.publisher;


import lombok.experimental.UtilityClass;

import java.io.Serializable;

/**
 * evan日志信息事件发送
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@UtilityClass
public final class UsualLoggerPublisher {

    public static void publishEvent(String level, Serializable bizId, String data) {
//        SysLogUsual logUsual = new SysLogUsual();
////        logUsual.setLogLevel(level);
////        logUsual.setLogId(sid);
////        logUsual.setLogData(data);
//
//        LoggerUtil.addRequestToLog(logUsual);
//        SpringUtil.publishEvent(new UsualLoggerEvent(logUsual));
    }

}
