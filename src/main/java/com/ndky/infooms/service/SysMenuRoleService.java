package com.ndky.infooms.service;

import com.ndky.infooms.entity.SysMenuRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysMenuRoleService extends IService<SysMenuRole> {

    /**
     * 添加角色和菜单的联系
     * @param sysMenuRole 角色和菜单的实例
     * @return 返回值
     */
    int addMenuRole(SysMenuRole sysMenuRole);

    /**
     * 根据角色id删除对应的角色和菜单的联系
     * @param roleId 角色id
     * @return 返回值
     */
    int deleteByRoleId(Long roleId);

    /**
     * 根据角色id查询所有菜单id
     * @param roleId 角色id
     * @param parentIds 菜单id
     * @return 所有菜单id
     */
    List<String> getAllMenuId(Long roleId, List<String> parentIds);

    /**
     * 根据角色id查询菜单id集合
     * @param roleId 角色id
     * @return
     */
    List<String> getAllMenuId(Long roleId);
}
