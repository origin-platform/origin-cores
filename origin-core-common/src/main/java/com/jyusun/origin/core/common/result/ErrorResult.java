package com.jyusun.origin.core.common.result;

import com.jyusun.origin.core.common.base.BaseResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class ErrorResult<E extends Serializable> extends AbstractResult<E> {

    @ApiModelProperty("错误标题")
    private String title;
    @ApiModelProperty("错误明细")
    private String detail;

    public ErrorResult(String code, String message, Links links, String title, String detail) {
        this.init(code, message, links, title, detail);
    }

    public ErrorResult(BaseResultCode baseResultCode, Links links, String title, String detail) {
        this(baseResultCode.code(), baseResultCode.message(), links, title, detail);
    }

    public ErrorResult(String code, String message, Links links) {
        this(code, message, links, null, null);
    }

    public ErrorResult(BaseResultCode baseResultCode) {
        this(baseResultCode.code(), baseResultCode.message(), null);
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
        this.init(code, message, false);
        this.links = links;
        this.title = title;
        this.detail = detail;
    }

}
