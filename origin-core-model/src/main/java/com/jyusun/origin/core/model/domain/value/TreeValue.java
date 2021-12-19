package com.jyusun.origin.core.model.domain.value;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 树形结构
 *
 * @param <T> 泛型标记
 * @author jyusun at 2021-12-19 14:47:36
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TreeValue<T> extends BaseValue {

    /**
     * 当前节点
     */
    @ApiModelProperty("当前节点")
    private T value;

    /**
     * 子节点列表
     */
    @ApiModelProperty("子节点列表")
    private List<TreeValue<T>> childrens;

}
