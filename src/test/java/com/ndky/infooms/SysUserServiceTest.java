package com.ndky.infooms;

import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chenqingsheng
 * @date 2021/1/20 16:07
 */
@SpringBootTest
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    void saveSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setSysName("admin3");
        sysUser.setSysPassword("123456");
        sysUser.setSysMobile("135xxxx5555");
        sysUser.setSysOfficeAddress("xx楼201");
        sysUser.setSysEmail("135xxxx5555@163.com");
        sysUser.setSysSex("男");
        // 在存入一个用户的时候，也必须加入这个用户的权限
        sysUserService.insert(sysUser);
        System.out.println(sysUser.getSysId());
    }

}
