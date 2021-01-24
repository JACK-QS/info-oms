package com.ndky.infooms.controller;


import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("/list")
    public String index(){
        return "module/menu/menu";
    }

    @GetMapping("/update")
    public String update(String menuId, Model model){
        SysMenu sysMenu = sysMenuService.getById(menuId);
        model.addAttribute("sysMenu",sysMenu);
        return "module/menu/updateMenu";
    }

    @GetMapping("/add")
    public String add(){
        return "module/menu/addMenu";
    }

}

