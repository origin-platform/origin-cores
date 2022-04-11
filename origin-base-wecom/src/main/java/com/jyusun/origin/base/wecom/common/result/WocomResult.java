package com.jyusun.origin.base.wecom.common.result;

import lombok.Data;

/**
 * 企业微信响应结果
 *
 * @param <T>
 */
@Data
public class WocomResult<T> {

    private String errcode;

    private String errmsg;

    private T data;


}
