package com.jyusun.origin.core.logger.model.value;

import com.jyusun.origin.core.model.domain.value.BaseValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 错误日志值对象
 *
 * @author jyusun at 2021-12-4 14:08:42
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ErrorValue extends BaseValue {

    /**
     * 堆栈信息
     */
    @ApiModelProperty("堆栈信息")
    private String stackTrace;
    /**
     * 异常类型
     */
    @ApiModelProperty("异常类型")
    private String exceptionName;
    /**
     * 异常消息
     */
    @ApiModelProperty("异常消息")
    private String message;
    /**
     * 错误文件类
     */
    @ApiModelProperty("错误文件")
    private String fileName;
    /**
     * 错误文件 行数
     */
    @ApiModelProperty("错误行数")
    private Integer lineNumber;

}
