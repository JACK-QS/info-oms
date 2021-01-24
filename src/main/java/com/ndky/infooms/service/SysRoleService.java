package com.ndky.infooms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户id查角色
     * @param userId 用户id
     * @return 角色
     */
    SysRole findByUserId(@Param("userId") Long userId);

    /**
     * 查询所有角色
     * @param page 分页数据
     * @return 所有角色集合
     */
    IPage<SysRole> getAll(Page page);

    /**
     * 根据用户名字查角色
     * @param rolename 名称
     * @return 角色
     */
    SysRole getByName(String rolename);

    /**
     * 根据id查角色名称
     * @param id id
     * @return 角色名称
     */
    String getById(Long id);

    /**
     * 根据id删除角色
     * @param id id
     * @return 返回值
     */
    int deleteById(Long id);

    /**
     * 根据id修改角色
     * @param sysRole 角色
     * @return 返回值
     */
    int updateRoleById(SysRole sysRole);

    /**
     * 保存角色
     * @param sysRole 角色
     * @return 返回值
     */
    int insert(SysRole sysRole);

    /**
     * 获取所有的角色名称
     * @return 所有角色名称
     */
    List<String> getAllRoleName();

    /**
     * 根据角色名称查询角色id
     * @param name 角色名称
     * @return 角色id
     */
    Long getIdByName(String name);
}
