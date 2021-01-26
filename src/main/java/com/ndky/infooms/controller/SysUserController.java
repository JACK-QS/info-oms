package com.ndky.infooms.controller;


import com.ndky.infooms.common.utils.SecurityUtils;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.service.SysRoleService;
import com.ndky.infooms.service.SysUserService;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    /** 添加一名用户
     * @return module
     */
    @GetMapping("/add")
    public String add(){
        return "module/user/addUser";
    }


    /**
     * 获取所有用户列表
     * @return module
     */
    @GetMapping("/list")
    public String index(){
        return "module/user/user";
    }


    /**
     * 更新用户
     * @param sysId 用户id
     * @param model 视图
     * @return 视图
     */
    @GetMapping("/update")
    public String update(Long sysId, Model model){
        // 通过id查询出用户对象
        SysUser sysUser = sysUserService.getById(sysId);
        // 通过用户id查询出用户角色名
        String roleName = sysRoleService.getById(sysUser.getSysId());
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roleName", roleName);
        return "module/user/updateUser";
    }

    /**
     * 用户登录后个人资料页面
     *
     */
    @GetMapping("/personal")
    public String personal(Model model){
        // 获取当前登录的用户
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        // 取得当前用那个的用户名
        String username = (String)authentication.getPrincipal();
        // 通过用户名查询出用户对象
        SysUser sysUser = sysUserService.findByName(username);
        // 通过用户id查询角色名
        String roleName = sysRoleService.getById(sysUser.getSysId());
        model.addAttribute("sysUser", JSONObject.fromObject(sysUser));
        model.addAttribute("roleName", roleName);
        return "module/user/personal";
    }

    /**
     * 用户登录之后修改密码页面
     * @return
     */
    @GetMapping("/changePassword")
    public String changePassword(){
        return "module/user/changePassword";
    }
}

