package com.jyusun.origin.core.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 错误信息响应
 *
 * @author jyusun at 2020/2/20 17:36
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResult<E> extends AbstractResult<E> {

    @ApiModelProperty("错误标题")
    private String title;
    @ApiModelProperty("错误明细")
    private String detail;
    @ApiModelProperty("链接对象")
    private Links links;

    public ErrorResult(String code, String message, Links links, String title, String detail) {
        this.init(code, message, links, title, detail);
    }

    public ErrorResult(String code, String message) {
        this(code, message, null, null, null);
    }

    /**
     * 错误结果初始化
     *
     * @param code    响应编码
     * @param message 响应消息
     * @param title   响应标题
     * @param detail  响应明细
     */
    private void init(String code, String message, Links links, String title, String detail) {
        super.init(code, message, false);
        this.links = links;
        this.title = title;
        this.detail = detail;
    }

}
