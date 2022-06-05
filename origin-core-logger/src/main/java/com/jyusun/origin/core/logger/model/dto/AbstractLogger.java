package com.jyusun.origin.core.logger.model.dto;


import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 作用描述：日志基础数据传输对象
 *
 * @author jyusun
 * @date 2020/5/18 12:01
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class AbstractLogger implements BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("服务名称")
    private String serviceCode;
    /**
     * 操作类型
     */
    @ApiModelProperty("操作类型")
    private String operationType;

    /**
     * 日志主题
     */
    @ApiModelProperty("日志主题")
    private String title;

    /**
     * 方法类
     */
    @ApiModelProperty("方法类")
    private String className;
    /**
     * 方法名
     */
    @ApiModelProperty("方法名称")
    private String methodName;
}
