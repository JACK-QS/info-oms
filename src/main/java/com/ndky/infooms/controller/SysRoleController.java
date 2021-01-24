package com.ndky.infooms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @GetMapping("/list")
    public String index(){
        return "module/role/role";
    }

    @GetMapping("/update")
    public String update(String roleName, String roleAuthoritr, Long roleId, Model model){
        model.addAttribute("roleName",roleName);
        model.addAttribute("roleAuthoritr",roleAuthoritr);
        model.addAttribute("roleId",roleId);
        return "module/role/updateRole";
    }

    @GetMapping("/add")
    public String add(){
        return "module/role/addRole";
    }

}

