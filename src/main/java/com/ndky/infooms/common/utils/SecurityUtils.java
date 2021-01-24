package com.ndky.infooms.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author chenqingsheng
 * @date 2021/1/23 17:27
 */
public class SecurityUtils {

    /**
     * 获取当前用户
     */
    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}