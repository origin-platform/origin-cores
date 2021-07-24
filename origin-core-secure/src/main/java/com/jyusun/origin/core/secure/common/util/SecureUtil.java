package com.jyusun.origin.core.secure.common.util;

import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.core.secure.UserInfo;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限工具
 *
 * @author jyusun
 */
@UtilityClass
public class SecureUtil {

    /**
     * 获取用户信息
     *
     * @param request {@link HttpServletRequest}
     * @return {@link UserInfo}
     */
    public UserInfo getUser(HttpServletRequest request) {
        String tenantId = "100001";
        long userId = 1000000000000000001L;
        String account = "admin";
        String nickName = "管理员阿呆";
        return new UserInfo()
                .setTenantId(tenantId)
                .setUserId(userId)
                .setAccount(account)
                .setNickName(nickName);
    }

    /**
     * 获取用户信息
     *
     * @return {@link UserInfo}
     */
    public UserInfo getUser() {
        return getUser(WebUtil.getRequest());
    }


    /**
     * 获取用户信息ID信息
     *
     * @return {@link UserInfo}
     */
    public Long getUserId() {
        return getUser().getUserId();
    }

}
