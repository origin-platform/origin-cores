package com.jyusun.origin.core.common.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 树形结构
 *
 * @param <T> 泛型标记
 * @author jyusun at 2021-12-19 14:47:36
 */
@Data
public abstract class BaseTreeDTO<T> implements BaseDTO {

    /**
     * 子节点列表
     */
    @ApiModelProperty("子节点列表")
    protected List<BaseTreeDTO<T>> childrens;

}
