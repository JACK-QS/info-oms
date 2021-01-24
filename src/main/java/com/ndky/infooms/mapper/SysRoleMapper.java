package com.ndky.infooms.mapper;

import com.ndky.infooms.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("SELECT * FROM sys_role WHERE id =(SELECT role_id FROM sys_user_role WHERE user_id = #{userId})")
    SysRole findByUserId(@Param("userId") String userId);
}
