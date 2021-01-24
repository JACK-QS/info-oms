package com.ndky.infooms.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:59
 */
public class RequestUtils {
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
