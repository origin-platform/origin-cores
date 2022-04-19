package com.jyusun.origin.base.mail.dto.param;

import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 数据传输对象
 * <p>
 * 作用描述：邮件发送
 *
 * @author jyusun
 * @date 2021/3/29 11:29
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class MailSimpleParam implements BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收件人地址")
    @NotEmpty(message = "收件人不允许为空")
    private List<String> toaddrs;
    @ApiModelProperty("抄送人地址")
    private List<String> ccaddrs;
    @ApiModelProperty("邮件主题")
    @NotBlank
    private String subject;
    @ApiModelProperty("邮件内容")
    private String content;
    @ApiModelProperty(value = "是否为HTML", example = "true-是|false-否")
    @NotEmpty
    private Boolean whetherHtml;
    @ApiModelProperty("邮件附件")
    private List<String> attachments;
}
