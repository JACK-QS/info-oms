package com.ndky.infooms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author chenqingsheng
 * @date 2021/1/23 16:17
 */
@Controller
@Slf4j
public class SysIndexController {

    /**
     * 在securityconfigurer中有注入
     */
    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/")
    public String index(){
        // 获取当前已认证的主体或认证请求session。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取所有已知的主体session注册表
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        return "index";
    }

    /**
     * home页面
     * @return
     */
    @RequestMapping("/console")
    public String home(){
        return "home";
    }

    /**
     * 介绍
     * @return
     */
    @RequestMapping("/introduce")
    public String introduce(){
        return "introduce";
    }

//    @RequestMapping("/admin")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String admin(){
//        return "是管理员";
//    }
//
//    @RequestMapping("/user")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String user(){
//        return "是普通用户";
//    }
}
