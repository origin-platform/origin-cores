package com.jyusun.origin.base.db.common.enums;

import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.constant.ResultConstant;
import lombok.AllArgsConstructor;

/**
 * 响应结果
 * <p>
 * 作用描述：数据库操作相关
 * </p>
 *
 * @author jyusun
 * @date 2020/11/24 15:47
 * @since 1.0.0
 */
@AllArgsConstructor
public enum DbResultEnum implements BaseResultCode {

    /**
     * 单页数据响应
     */
    MORE_THAN_THE_MAXIMUM(ResultConstant.MESSAGE_INTERNAL_SERVER_ERROR, "单页数据超过500，请调整");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 返回代码
     *
     * @return int
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 返回消息
     *
     * @return str
     */
    @Override
    public String message() {
        return this.message;
    }
}
