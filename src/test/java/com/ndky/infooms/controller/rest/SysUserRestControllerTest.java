package com.ndky.infooms.controller.rest;

import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chenqingsheng
 * @date 2021/1/21 15:35
 */
@SpringBootTest
public class SysUserRestControllerTest {
    @Autowired
    private SysUserRestController sysUserRestController;

    @Test
    void addUser(){
        UserQuery sysUser = new UserQuery();
        sysUser.setUserRole("管理员");
        sysUser.setSysName("admin15915");
        sysUser.setSysPassword("123456");
        sysUser.setSysMobile("135xxxx5555");
        sysUser.setSysOfficeAddress("xx楼201");
        sysUser.setSysEmail("135xxxx5555@163.com");
        sysUser.setSysSex("男");

        sysUserRestController.addRole(sysUser);

    }

    @Test
    void getUserVO(){
        ApiResponse userInfo = sysUserRestController.getUserInfo(1, 5);
        System.out.println(userInfo);
    }
}
