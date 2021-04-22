package com.jyusun.origin.core.common.base;

import java.io.Serializable;

/**
 * 作用描述： - 基础响应结果
 *
 * @author jyusun at 2019/12/22 16:28
 * @since 1.0.0
 */
public interface BaseResultCode extends Serializable {

    /**
     * 返回代码
     *
     * @return int
     */
    String code();

    /**
     * 返回消息
     *
     * @return str
     */
    String message();

}
