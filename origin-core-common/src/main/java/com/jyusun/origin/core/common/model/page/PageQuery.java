package com.jyusun.origin.core.common.model.page;

import com.jyusun.origin.core.common.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 分页查询对象
 *
 * @author jyusun at 2020/2/20 20:55
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ApiModel("查询对象：分页查询")
public class PageQuery implements BaseQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数 默认 1", example = "1")
    private Long page;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数 10", example = "10")
    private Long limit;

    /**
     * <p>
     * SQL 排序 ASC 数组
     * </p>
     */
    @ApiModelProperty("SQL 排序 ASC")
    private String[] ascs;

    /**
     * <p>
     * SQL 排序 DESC 数组
     * </p>
     */
    @ApiModelProperty("SQL 排序 DESC")
    private String[] descs;


    public PageQuery() {
        this.init();
    }

    /**
     * 参数值初始化
     */
    private void init() {
        this.page = 1L;
        this.limit = 10L;
    }

}
