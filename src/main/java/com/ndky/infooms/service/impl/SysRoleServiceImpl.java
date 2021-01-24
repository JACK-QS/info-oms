package com.ndky.infooms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.mapper.SysRoleMapper;
import com.ndky.infooms.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ndky.infooms.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 通过用户id查角色
     *
     * @param userId 用户id
     * @return 角色
     */
    @Override
    public SysRole findByUserId(Long userId) {
        // 通过userId查询角色id
        Long aLong = sysUserRoleService.selectRoleId(userId);
        SysRole sysRole = baseMapper.selectById(aLong);
        return sysRole;
    }

    /**
     * 查询所有角色
     *
     * @param page 分页数据
     * @return 所有角色集合
     */
    @Override
    public IPage<SysRole> getAll(Page page) {
        return baseMapper.selectPage(page,null);
    }

    /**
     * 根据角色名称查角色
     *
     * @param name 名称
     * @return 角色
     */
    @Override
    public SysRole getByName(String rolename) {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name",rolename);
        return baseMapper.selectOne(sysRoleQueryWrapper);
    }

    /**
     * 根据id查角色名称
     *
     * @param id 用户id
     * @return 角色名称
     */
    @Override
    public String getById(Long id) {
        //传过来的是用户id
        //查询角色id
        Long aLong = sysUserRoleService.selectRoleId(id);
        //通过角色id来查询角色名称
        return baseMapper.selectById(aLong).getRoleName();
    }

    /**
     * 根据id删除角色
     *
     * @param id id
     * @return 返回值
     */
    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 根据id修改角色
     *
     * @param sysRole 角色
     * @return 返回值
     */
    @Override
    public int updateRoleById(SysRole sysRole) {
        SysRole sysRole1 = baseMapper.selectById(sysRole.getRoleId());
        sysRole1.setRoleName(sysRole.getRoleName());
        sysRole1.setRoleAuthoritr(sysRole.getRoleAuthoritr());
        int update = baseMapper.update(sysRole1, null);
        return update;
    }

    /**
     * 保存角色
     *
     * @param sysRole 角色
     * @return 返回值
     */
    @Override
    public int insert(SysRole sysRole) {
        return baseMapper.insert(sysRole);
    }

    /**
     * 获取所有的角色名称
     *
     * @return 所有角色名称
     */
    @Override
    public List<String> getAllRoleName() {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.select("role_name");
        List<SysRole> sysRoles = baseMapper.selectList(sysRoleQueryWrapper);
        Iterator<SysRole> iterator = sysRoles.iterator();
        ArrayList<String> strings = new ArrayList<>();
        while(iterator.hasNext()){
            strings.add(iterator.next().getRoleName().toString());
        }
        return strings;
    }

    /**
     * 根据角色名称查询角色id
     *
     * @param name 角色名称
     * @return 角色id
     */
    @Override
    public Long getIdByName(String name) {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name",name);
        return baseMapper.selectOne(sysRoleQueryWrapper).getRoleId();
    }
}
