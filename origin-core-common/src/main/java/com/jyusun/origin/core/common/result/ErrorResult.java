package com.jyusun.origin.core.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("异常响应对象")
public class ErrorResult<E> extends AbstractResult<E> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接对象")
    private Links links;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("错误标题")
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("错误明细")
    private String detail;


    public ErrorResult(String code, String message, Links links, String title, String detail) {
        this.init(code, message, links, title, detail);
    }

    public ErrorResult(String code, String message, Links links) {
        this.init(code, message, links, null, null);
    }

    public ErrorResult(String code, String message) {
        this(code, message, null, null, null);
    }

    /**
     * 错误结果初始化
     *
     * @param code    {@code Integer} 响应编码
     * @param message {@code String} 响应消息
     * @param links   {@link Links} 请求连接
     * @param title   {@code String} 响应标题
     * @param detail  {@code String} 响应明细
     */
    private void init(String code, String message, Links links, String title, String detail) {
        this.init(code, message, false);
        this.links = links;
        this.title = title;
        this.detail = detail;
    }
}
