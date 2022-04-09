package com.jyusun.origin.core.logger.model.value;

import com.jyusun.origin.core.model.domain.value.ValueObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 请求信息
 *
 * @author jyusun at 2021-11-24 22:17:16
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RequestValue implements ValueObject {

    /**
     * 操作IP地址
     */
    @ApiModelProperty("远程地址")
    private String remoteAddress;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String httpMethod;

    /**
     * 请求的参数
     */
    private Map<String,Object> params;

    /**
     * 值对象通过属性比较，没有唯一ID
     *
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    @Override
    public boolean sameValueAs(Object other) {
        return false;
    }
}
