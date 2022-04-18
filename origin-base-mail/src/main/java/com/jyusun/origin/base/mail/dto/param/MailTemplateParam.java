package com.jyusun.origin.base.mail.dto.param;

import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 数据传输对象
 * <p>
 * 作用描述：发送模板邮件
 *
 * @author jyusun
 * @date 2021/3/29 11:29
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MailTemplateParam extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收件人地址")
    @NotEmpty
    private List<String> toaddrs;
    @ApiModelProperty("抄送人地址")
    private List<String> ccaddrs;
    @ApiModelProperty("邮件主题")
    @NotBlank
    private String subject;
    @ApiModelProperty("模板内容")
    @NotEmpty(message = "模板填充内容不允许为空")
    private Map<String, Object> templateContent;
    @ApiModelProperty("邮件附件")
    private List<String> attachments;
}
