package com.jyusun.origin.core.common.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 错误信息响应
 *
 * @author jyusun at 2020/2/20 17:36
 * @since 1.0.0
 */
@Getter
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("异常响应对象")
public class ErrorResult<E> extends AbstractResult<E> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接对象")
    private Links links;
    @ApiModelProperty("错误标题")
    private String title;
    @ApiModelProperty("错误明细")
    private String detail;


    public ErrorResult(String code, String message, String title, String detail, Links links) {
        super(code, message, false);
        this.init(title, detail, links);
    }

    public ErrorResult(String code, String message, Links links) {
        this(code, message, null, null, links);
    }

    public ErrorResult(String code, String message) {
        this(code, message, null, null, null);
    }

    /**
     * 错误结果初始化
     *
     * @param title  {@code String} 响应标题
     * @param detail {@code String} 响应明细
     * @param links  {@link Links} 请求连接
     */
    private void init(String title, String detail, Links links) {
        this.title = title;
        this.detail = detail;
        this.links = links;
    }
}
