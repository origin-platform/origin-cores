package com.jyusun.origin.core.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 连接地址
 * <p>
 * 作用描述：请求URL信息
 * </p>
 *
 * @author jyusun
 * @date 2020/12/3 17:48
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("链接对象")
@NoArgsConstructor
public class Links implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("请求地址")
    private String self;


}
