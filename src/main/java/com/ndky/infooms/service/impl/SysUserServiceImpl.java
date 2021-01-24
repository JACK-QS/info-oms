package com.ndky.infooms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.mapper.SysUserMapper;
import com.ndky.infooms.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByName(String name) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("sys_name",name);
        return baseMapper.selectOne(sysUserQueryWrapper);
    }

    @Override
    public IPage<SysUser> getAll(Page<SysUser> page) {
        return baseMapper.selectPage(page,null);
    }

    @Override
    public SysUser getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateUserById(SysUser sysUser) {
        SysUser sysUser1 = baseMapper.selectById(sysUser.getSysId());
        sysUser1.setSysName(sysUser.getSysName());
        sysUser1.setSysPassword(sysUser.getSysPassword());
        sysUser1.setSysSex(sysUser.getSysSex());
        sysUser1.setSysMobile(sysUser.getSysMobile());
        sysUser1.setSysEmail(sysUser.getSysEmail());
        sysUser1.setSysOfficeAddress(sysUser.getSysOfficeAddress());
        return baseMapper.update(sysUser1,null);
    }

    @Override
    public int insert(SysUser sysUser) {
        return baseMapper.insert(sysUser);
    }

    @Override
    public int updatePasswordById(String password, Long id) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setSysPassword(password);
        return baseMapper.updateById(sysUser);
    }
}
