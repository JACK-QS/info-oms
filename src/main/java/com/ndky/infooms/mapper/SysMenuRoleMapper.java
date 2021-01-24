package com.ndky.infooms.mapper;

import com.ndky.infooms.entity.SysMenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysMenuRoleMapper extends BaseMapper<SysMenuRole> {
    @Select({
            "<script>",
            "select menu_id from sys_menu_role",
            "where role_id = #{roleId} and menu_id not in",
            "<foreach collection='parentIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<String> getAllMenuId(@Param("roleId")Long roleId, @Param("parentIds")List<String> parentIds);
}
