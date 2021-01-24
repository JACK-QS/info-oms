package com.ndky.infooms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.infooms.entity.SysUserRole;
import com.ndky.infooms.mapper.SysUserRoleMapper;
import com.ndky.infooms.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 添加用户和角色的联系
     *
     * @param sysUserRole 用户角色
     * @return 返回值
     */
    @Override
    public int insert(SysUserRole sysUserRole) {
        return baseMapper.insert(sysUserRole);
    }

    /**
     * 根据用户id删除用户和角色的联系
     *
     * @param userId 用户id
     * @return 返回值
     */
    @Override
    public int deleteByUserId(Long userId) {
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id",userId);
        return baseMapper.delete(sysUserRoleQueryWrapper);
    }

    @Override
    public Long selectRoleId(Long userId) {
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id",userId);
        return baseMapper.selectOne(sysUserRoleQueryWrapper).getRoleId();
    }
}
