package com.jyusun.origin.core.common.util;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 作用描述： Web 工具
 *
 * @author jyusun at 2020/4/16 15:18
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUtil extends WebUtils {

    public static final String USER_AGENT_HEADER = "user-agent";

    private static final String UN_KNOWN = "unknown";

    /**
     * 获取 HttpServletRequest
     *
     * @return {@link HttpServletRequest} 请求
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ObjectUtil.isEmpty(requestAttributes) ? null :
                ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获取http请求 参数
     *
     * @param request {@link HttpServletRequest} 请求对象
     * @return {@link Map}
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取http请求 头信息
     *
     * @param request {@link HttpServletRequest}
     * @return {@link Map}
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }


    /**
     * 获取ip
     *
     * @return {String}
     */
    public static String getIpAddr() {
        return getIpAddr(WebUtil.getRequest());
    }

    /**
     * 获取ip
     *
     * @param request HttpServletRequest
     * @return {String}
     */
    @Nullable
    public static String getIpAddr(HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest is null");
        String ip = request.getRemoteAddr();
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Requested-For");
        }
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        return StringUtil.isBlank(ip) ? null : ip.split(",")[0];
    }
}
