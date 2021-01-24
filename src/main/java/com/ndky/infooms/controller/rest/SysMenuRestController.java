package com.ndky.infooms.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.common.utils.SecurityUtils;
import com.ndky.infooms.common.utils.UUIDUtils;
import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.service.SysMenuService;
import com.ndky.infooms.vo.menu.MenuNameVO;
import com.ndky.infooms.vo.menu.MenuVO;
import com.ndky.infooms.vo.menu.SysMenuVO;
import com.ndky.infooms.vo.menuController.SysMenuNameVO;
import com.ndky.infooms.vo.roleController.MenuListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenqingsheng
 * @date 2021/1/22 14:58
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuRestController {

    private final SysMenuService sysMenuService;

    /**
     * 添加菜单
     * @param sysMenuNameVO
     * @return
     */
    @PostMapping("/addMenu")
    @ResponseBody
    public ApiResponse addMenu(@RequestBody SysMenuNameVO sysMenuNameVO){
        JSONObject jsonObject = new JSONObject();
        System.out.println(sysMenuNameVO.getMenuName());
        System.out.println(sysMenuNameVO.getMenuCode());
        System.out.println(sysMenuNameVO.getMenuHref());

        //查看有没有重复的菜单
        SysMenu menu = sysMenuService.getByName(
                sysMenuNameVO.getMenuName(),
                sysMenuNameVO.getMenuCode(),
                sysMenuNameVO.getMenuHref());

        if (menu == null) {
            //Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
            SysMenu sysMenu = new SysMenu();
            BeanUtils.copyProperties(sysMenuNameVO,sysMenu);
            sysMenu.setMenuId(UUIDUtils.getUUID());
            // 查询父类菜单的id值
            System.out.println(sysMenuNameVO.getMenuNames());

            if(!sysMenuNameVO.getMenuNames().equals("")){
                String byMenuName = sysMenuService.getByMenuName(sysMenuNameVO.getMenuNames());
                sysMenu.setParentId(byMenuName);
            }

            sysMenu.setMenuCreateName("admin");
            if("1".equals(sysMenuNameVO.getIsShow())){
                sysMenu.setIsShow(true);
            }else {
                sysMenu.setIsShow(false);
            }
                    //(String)authentication.getPrincipal());
            try{
                if (sysMenuService.addMenu(sysMenu) > 0){
                    jsonObject.put("code",200);
                }
            }catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("code",500);
            }
        } else {
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 获取菜单层级
     * @return
     */
    @GetMapping("/getMenuLevel")
    public ApiResponse getMenuLevel(){
        JSONObject jsonObject = new JSONObject();
        List<String> menuLevel = sysMenuService.getMenuLevel();
        jsonObject.put("menuLevel",menuLevel);
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 查询当前层级的上层菜单
     * @param menuLevel
     * @return
     */
    @GetMapping("/getPreviousMenu")
    public ApiResponse getPreviousMenu(@RequestParam("menuLevel")String menuLevel){
        JSONObject jsonObject = new JSONObject();
        List<MenuNameVO> menuNames = sysMenuService.getPreviousMenu(String.valueOf((Integer.parseInt(menuLevel) - 1)));
        jsonObject.put("menuNames",menuNames);
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 分页查询所有菜单列表
     * @param page 页数
     * @param pageSize 条数
     * @return
     */
    @GetMapping("/getMenuInfo")
    public ApiResponse getMenuInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();

        /*page = (page -1) * pageSize;*/
        List<MenuListVO> listVoList = new LinkedList<>();
        IPage<SysMenu> firstMenu = sysMenuService.findFirstMenu(new Page(page, pageSize));
        //组装数据
        List<SysMenu> firstMenuList = firstMenu.getRecords();
        for (SysMenu sysMenu : firstMenuList) {
            List<SysMenu> secondMenu = sysMenuService.findByParentId(sysMenu.getMenuId());
            listVoList.add(MenuListVO.builder()
                    .menuId(sysMenu.getMenuId())
                    .children(secondMenu)
                    .isShow(sysMenu.getIsShow())
                    .menuCode(sysMenu.getMenuCode())
                    .menuHref(sysMenu.getMenuHref())
                    .menuIcon(sysMenu.getMenuIcon())
                    .menuLevel(sysMenu.getMenuLevel())
                    .menuName(sysMenu.getMenuName())
                    .menuWeight(sysMenu.getMenuWeight()).build());
        }
        jsonObject.put("total",firstMenu.getTotal());
        jsonObject.put("page",firstMenu.getCurrent());
        jsonObject.put("page_size",firstMenu.getSize());
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 修改菜单
     * @param sysMenuNameVO
     * @return
     */
    @PostMapping("/updateMenu")
    @ResponseBody
    public ApiResponse updateMenu(@RequestBody SysMenuNameVO sysMenuNameVO){
        JSONObject jsonObject = new JSONObject();
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuNameVO,sysMenu);
        try{
            if (sysMenuService.updateMenu(sysMenu) > 0){
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 根据登录用户获取当前菜单列表
     * @return
     */
    @GetMapping("/getMenulist")
    public ApiResponse getMenulist(){
        //获取当前用户登录用户
        Authentication userAuthentication = SecurityUtils.getCurrentUserAuthentication();
        String name = userAuthentication.getName();
        for (int i = 0; i < 10; i++) {
            System.out.println(name);
        }
        List<MenuVO> menuVoList = sysMenuService.getMenuByUser(name);
        JSONObject data = new JSONObject(16);
        data.put("name",name);
        data.put("menuList",menuVoList);
        return ApiResponse.ofSuccess(data);
    }


    /**
     * 删除一条菜单
     * @param id
     * @return
     */
    @GetMapping("/deleteMenu")
    public ApiResponse deleteMenu(@RequestParam("id")String menuId){
        JSONObject jsonObject = new JSONObject();
        try{
            if (sysMenuService.deleteMenuById(menuId) > 0){
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }



}
