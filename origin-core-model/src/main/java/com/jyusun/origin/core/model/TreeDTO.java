package com.jyusun.origin.core.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构
 *
 * @param <T> 泛型标记
 * @author jyusun at 2021-12-19 14:47:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"handler"})
public class TreeDTO<T extends Serializable> extends BaseDTO {

    /**
     * 当前节点
     */
    @ApiModelProperty("当前节点")
    private T thisNode;

    /**
     * 子节点列表
     */
    @ApiModelProperty("子节点列表")
    private List<TreeDTO<T>> childrens;

}
