package com.ndky.infooms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实例
     */
    SysUser findByName(@Param("name") String name);

    /**
     * 查询所有用户
     * @param page 分页数据
     * @return 所有用户集合
     */
    IPage<SysUser> getAll(Page<SysUser> page);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户集合
     */
    SysUser getById(Long id);

    /**
     * 根据id删除用户
     * @param id id
     * @return 返回值
     */
    int deleteById(Long id);

    /**
     * 根据sysUser更新用户信息
     * @param sysUser 用户
     * @return 返回值
     */
    int updateUserById(SysUser sysUser);

    /**
     * 保存用户
     * @param sysUser 用户
     * @return 返回值
     */
    int insert(SysUser sysUser);

    /**
     * 根据用户id,更新密码
     * @param password 密码
     * @param id 用户id
     * @return 修改条数
     */
    int updatePasswordById(String password, Long id);
}
