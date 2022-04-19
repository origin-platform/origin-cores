package com.jyusun.origin.base.mail.dto.resp;

import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 数据传输对象
 * <p>
 * 作用描述：邮件规则响应
 *
 * @author jyusun
 * @date 2021/3/29 11:29
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class RuleDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收件人地址")
    private final List<String> toaddrs;
    @ApiModelProperty("抄送人地址")
    private final List<String> ccaddrs;
    @ApiModelProperty("邮件主题")
    private final String subject;
    @ApiModelProperty("邮件内容")
    private final String content;

}
