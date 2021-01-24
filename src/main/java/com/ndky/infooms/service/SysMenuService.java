package com.ndky.infooms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ndky.infooms.vo.menu.MenuNameVO;
import com.ndky.infooms.vo.menu.MenuVO;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户名获取菜单vo集合
     * @param username 用户名
     * @return 菜单vo集合
     */
    List<MenuVO> getMenuByUser(String username);

    /**
     * 根据用户名获取菜单集合
     * @param username 用户名
     * @return 菜单集合
     */
    List<SysMenu> findMenuListByUser(String username);

    /**
     * 根据页数条数找到一级菜单集合
     * @param page 页数
     * @return 一级菜单
     */
    IPage<SysMenu> findFirstMenu(Page page);

    /**
     * 根据父菜单id找到父菜单集合
     * @param parentId  父菜单
     * @return 父菜单集合
     */
    List<SysMenu> findByParentId(String parentId);

    /**
     * 修改菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    int updateMenu(SysMenu sysMenu);

    /**
     * 添加菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    int addMenu(SysMenu sysMenu);

    /**
     * 查询菜单
     * @param menuName 菜单名称
     * @param menuCode 菜单别名
     * @param menuHref 菜单链接
     * @return 返回值
     */
    SysMenu getByName(String menuName, String menuCode, String menuHref);

    /**
     * 根据id查询菜单
     * @param id id
     * @return 菜单
     */
    SysMenu getById(String id);

    /**
     * 获取一级菜单
     * @return 一级菜单
     */
    List<SysMenu> getFirstMenu();

    /**
     * 获取二级菜单
     */
    List<SysMenu> getSecondMenu();

    /**
     * 根据角色id查询所有菜单
     * @param roleId 角色id
     * @return 菜单
     */
    List<String> getRoleMenu(Long roleId);

    /**
     * 获取菜单层级
     * @return 菜单登记
     */
    List<String> getMenuLevel();

    /**
     * 查询当前菜单的上级菜单
     * @param menuLevel 上级菜单层级
     * @return 上级菜单名称
     */
    List<MenuNameVO> getPreviousMenu(String menuLevel);

    /**
     * 根据菜单名称查询菜单id
     * @param menuNames 菜单名称
     * @return 菜单id
     */
    String getByMenuName(String menuNames);

    /**
     * 根据id删除菜单
     * @param id id
     * @return 返回值
     */
    int deleteMenuById(String id);
}
