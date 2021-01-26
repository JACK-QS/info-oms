package com.ndky.infooms.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.entity.SysMenuRole;
import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.service.SysMenuRoleService;
import com.ndky.infooms.service.SysMenuService;
import com.ndky.infooms.service.SysRoleService;
import com.ndky.infooms.vo.roleController.MenuListVO;
import com.ndky.infooms.vo.roleController.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenqingsheng
 * @date 2021/1/22 14:10
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleRestController {

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final SysMenuRoleService sysMenuRoleService;

    /**
     * 获取角色列表
     * @param page 页数
     * @param pageSize 每页条数
     * @return 数据
     */
    @GetMapping("/getRoleInfo")
    public ApiResponse getRoleInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysRole> sysRoleList = sysRoleService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysRoleList.getTotal());
        jsonObject.put("page",sysRoleList.getCurrent());
        jsonObject.put("page_size",sysRoleList.getSize());
        jsonObject.put("sysRoleList",sysRoleList.getRecords());
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @GetMapping("/deleteRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteRole(@RequestParam("roleId")Long roleId){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(roleId);
            sysRoleService.deleteById(roleId);
            jsonObject.put("code",200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 更新角色
     * @param roleVO
     * @return 角色
     */
    @PostMapping("/updateRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(roleVO.getRoleId());
            for (String menuId : roleVO.getIds()){
                SysMenuRole sysMenuRole = new SysMenuRole(menuId, roleVO.getRoleId());
                sysMenuRoleService.addMenuRole(sysMenuRole);
            }
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleVO.getRoleId());
            sysRole.setRoleName(roleVO.getRoleName());
            sysRole.setRoleAuthoritr(roleVO.getRoleAuthoritr());
            sysRoleService.updateById(sysRole);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }


    /**
     * 增加一种角色
     * @param roleVO
     * @return
     */
    @PostMapping("/addRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            // 查找有没有相同的角色名
            SysRole role = sysRoleService.getByName(roleVO.getRoleName());
            if (role == null){
                // 创建角色对象
                SysRole sysRole = new SysRole();
                // 获取角色名称
                sysRole.setRoleName(roleVO.getRoleName());
                // 获取角色权限
                sysRole.setRoleAuthoritr(roleVO.getRoleAuthoritr());

                sysRoleService.insert(sysRole);
                for (int i = 0; i < 10; i++) {
                    System.out.println(sysRole);
                }
                // 插入完成之后，根据角色名称查出角色id
                Long roleId = sysRoleService.getByName(sysRole.getRoleName()).getRoleId();
                for (int i = 0; i < 10; i++) {
                    System.out.println(roleId);
                }

                for (String menuId : roleVO.getIds()){
                    SysMenuRole sysMenuRole = new SysMenuRole(menuId,roleId);
                    sysMenuRoleService.addMenuRole(sysMenuRole);
                }

                jsonObject.put("code",200);
            } else {
                // 501 角色已存在
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }


    /**
     * 新增角色页面 获取角色菜单
     * @return
     */
    @GetMapping("/getData")
    public ApiResponse getData(){
        JSONObject jsonObject = new JSONObject();

        // 获取所有菜单列表
        List<MenuListVO> listVoList = getMenu();

        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }



    /**
     * 获取所有菜单
     * @return
     */
    private List<MenuListVO> getMenu(){
        List<MenuListVO> listVoList = new LinkedList<>();
        // 获取一级菜单
        List<SysMenu> firstMenuList = sysMenuService.getFirstMenu();
        // 组装数据
        for (SysMenu sysMenu : firstMenuList) {
            List<SysMenu> secondMenu = sysMenuService.findByParentId(sysMenu.getId());
            listVoList.add(MenuListVO.builder()
                    .id(sysMenu.getId())
                    .children(secondMenu)
                    .isShow(sysMenu.getIsShow())
                    .menuCode(sysMenu.getMenuCode())
                    .menuHref(sysMenu.getMenuHref())
                    .menuIcon(sysMenu.getMenuIcon())
                    .menuLevel(sysMenu.getMenuLevel())
                    .menuName(sysMenu.getMenuName())
                    .menuWeight(sysMenu.getMenuWeight()).build());
        }
        return listVoList;
    }

    /**
     * 获取角色菜单(暂时没有发现哪里看到)
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleMenu")
    public ApiResponse getRoleMenu(@RequestParam("roleId")Long roleId){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVO> listVoList = getMenu();
        List<String> parentIds = sysMenuService.getRoleMenu(roleId);
        //List<String> roleMenuIds = sysMenuRoleService.getAllMenuId(roleId, parentIds);
        List<String> roleMenuIds = sysMenuRoleService.getAllMenuId(roleId);
        jsonObject.put("ids", roleMenuIds);
        jsonObject.put("parentIds", parentIds);
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

}
