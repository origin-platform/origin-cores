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
     * TODO 需要改为实际的值
     *
     * @param request {@link HttpServletRequest}
     * @return {@link UserInfo}
     */
    public UserInfo getUser(HttpServletRequest request) {
        return new UserInfo()
                .setTenantId("100001")
                .setUserId(1000000000000000001L)
                .setAccount("admin")
                .setNickName("管理员阿呆");
    }

    /**
     * 获取用户信息
     *
     * @return {@link UserInfo}
     */
    public UserInfo getUser() {
        return getUser(WebUtil.getRequest());
    }

}
