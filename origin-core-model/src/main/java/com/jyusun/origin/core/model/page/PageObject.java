package com.jyusun.origin.core.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 响应对象：分页数据对象
 *
 * @param <T> 泛型标记
 * @author jyusun at 2019/4/14 12:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel
public final class PageObject<T extends Serializable> implements Serializable {

	/** 响应数据行结果 */
	@ApiModelProperty("响应数据")
	private List<T> rows;

	/** 响应数据总条数 */
	@ApiModelProperty("总条数")
	private Long total;

	/** 响应数据总页数 */
	@ApiModelProperty("总页数")
	private Long pages;

	/** 当前页 */
	@ApiModelProperty("当前页")
	private Long page;

	/** 每页条数 */
	@ApiModelProperty("每页条数")
	private Long limit;

}