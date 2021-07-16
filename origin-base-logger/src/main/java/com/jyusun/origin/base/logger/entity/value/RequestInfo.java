package com.jyusun.origin.base.logger.entity.value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jyusun.origin.core.model.domain.value.BaseValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 值对象
 * <p>
 * 作用描述：请求信息
 *
 * @author jyusun at 2021-7-8 18:39:52
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("值对象：请求信息")
public class RequestInfo extends BaseValue {

    private static final long serialVersionUID = 1L;
    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;
    /**
     * 操作IP地址
     */
    @ApiModelProperty("远程IP")
    private String remoteAddr;
    /**
     * 请求URI
     */
    @ApiModelProperty("请求URI")
    private String requestUri;
    /**
     * 请求方式：GET、POST、DELETE、PUT 等
     */
    @ApiModelProperty("请求方式")
    private String httpMethod;
    /**
     * 方法类
     */
    @ApiModelProperty("请求类名")
    private String className;
    /**
     * 方法名
     */
    @ApiModelProperty("请求方法")
    private String methodName;
    /**
     * HTTP中请求的参数
     */
    @ApiModelProperty("HTTP中参数")
    private Map<String, String> requestParams;
    /**
     * Poin中 请求的参数
     */
    @ApiModelProperty("请求参数")
    private Map<String, Object> params;
}
