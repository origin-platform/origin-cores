package com.jyusun.origin.core.logger.event;

import java.util.EventObject;

/**
 * 作用描述：
 * - 基础监听函数
 *
 * @author jyusun at 2019/8/12 11:04
 * @since 1.0.0
 */
public interface LoggerListener {

    /**
     * 事件处理
     *
     * @param event 事件
     */
    void eventHandle(EventObject event);

}
