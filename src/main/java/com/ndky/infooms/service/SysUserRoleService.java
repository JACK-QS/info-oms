package com.ndky.infooms.service;

import com.ndky.infooms.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 添加用户和角色的联系
     * @param sysUserRole 用户角色
     * @return 返回值
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 根据用户id删除用户和角色的联系
     * @param userId 用户id
     * @return 返回值
     */
    int deleteByUserId(Long userId);

    /**
     * 通过用户id查询角色id
     *
     */
    Long selectRoleId(Long userId);




}
