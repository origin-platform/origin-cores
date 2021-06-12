package com.jyusun.origin.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 作用描述：基础数据传输对象
 *
 * @author jyusun
 * @date 2020/5/18 12:01
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键编号")
    private Long sid;

}
