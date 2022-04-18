package com.jyusun.origin.core.secure;

import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author sun
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends BaseDTO {

    /**
     * 客户端id
     */
    @ApiModelProperty(hidden = true)
    private String clientId;
    /**
     * 租户ID
     */
    @ApiModelProperty(hidden = true)
    private String tenantId;

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private Long userId;
    /**
     * 昵称
     */
    @ApiModelProperty(hidden = true)
    private String nickName;
    /**
     * 账号
     */
    @ApiModelProperty(hidden = true)
    private String account;

}
