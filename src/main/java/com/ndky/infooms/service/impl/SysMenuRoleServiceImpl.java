package com.ndky.infooms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.infooms.entity.SysMenuRole;
import com.ndky.infooms.mapper.SysMenuRoleMapper;
import com.ndky.infooms.service.SysMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {

    private SysMenuRoleMapper sysMenuRoleMapper;
    /**
     * 添加角色和菜单的联系
     *
     * @param sysMenuRole 角色和菜单的实例
     * @return 返回值
     */
    @Override
    public int addMenuRole(SysMenuRole sysMenuRole) {
        return baseMapper.insert(sysMenuRole);
    }

    /**
     * 根据角色id删除对应的角色和菜单的联系
     *
     * @param roleId 角色id
     * @return 返回值
     */
    @Override
    public int deleteByRoleId(Long roleId) {
        QueryWrapper<SysMenuRole> sysMenuRoleQueryWrapper = new QueryWrapper<>();
        sysMenuRoleQueryWrapper.eq("role_id",roleId);
        return baseMapper.delete(sysMenuRoleQueryWrapper);
    }

    /**
     * 根据角色id查询所有菜单id
     *
     * @param roleId    角色id
     * @param parentIds 菜单id
     * @return 所有菜单id
     */
    @Override
    public List<String> getAllMenuId(Long roleId, List<String> parentIds) {
//        @Select({
//                "<script>",
//                "select menu_id from sys_menu_role",
//                "where role_id = #{roleId} and menu_id not in",
//                "<foreach collection='parentIds' item='id' open='(' separator=',' close=')'>",
//                "#{id}",
//                "</foreach>",
//                "</script>"
//        })
//        List<String> getAllMenuId(@Param("roleId")Long roleId, @Param("parentIds")List<String> parentIds);
//        return sysMenuRoleMapper.getAllMenuId(roleId,parentIds);
        List<String> strings = new ArrayList<>();
        return strings;
    }

    /**
     * 根据角色id查询菜单id集合
     *
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<String> getAllMenuId(Long roleId) {
        QueryWrapper<SysMenuRole> sysMenuRoleQueryWrapper = new QueryWrapper<>();
        sysMenuRoleQueryWrapper.eq("role_id",roleId);
        List<SysMenuRole> sysMenuRoles = baseMapper.selectList(sysMenuRoleQueryWrapper);
        Iterator<SysMenuRole> iterator = sysMenuRoles.iterator();
        List<String> strings = new ArrayList<>();
        while(iterator.hasNext()){
            strings.add(iterator.next().getMenuId());
        }
        return strings;
    }
}
