package com.ndky.infooms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.vo.menu.MenuNameVO;
import com.ndky.infooms.vo.menu.SysMenuVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("SELECT *  FROM sys_menu as m LEFT JOIN sys_menu_role as r ON m.menu_id = r.menu_id WHERE m.is_show = '1' and r.role_id = #{roleId} ORDER BY m.menu_weight")
    List<SysMenu> findByRoleId(@Param("roleId") Long roleId);

    @Select("SELECT r.* FROM sys_role AS r LEFT JOIN sys_user_role as u ON r.id = u.role_id " +
            "WHERE u.user_id = (SELECT menu_id FROM sys_user WHERE name = #{name})")
    SysRole findByName(@Param("name") String name);



    @Select({"<script>select * from sys_menu where menu_name = #{menuName} or menu_code = #{menuCode} " +
            "<when test=\"menuHref != null and menuHref != ''\"> or menu_href = #{menuHref}</when></script>"})
    SysMenu getByName(@Param("menuName")String menuName, @Param("menuCode")String menuCode, @Param("menuHref")String menuHref);


    @Select("SELECT menu_id FROM sys_menu sm WHERE sm.menu_level = 1 and id in " +
            "(select mr.menu_id from sys_menu_role mr left join sys_menu m on mr.menu_id = m.id where mr.role_id = #{roleId})" +
            " ORDER BY menu_weight")
    List<String> getRoleMenu(@Param("roleId")Long roleId);




}
