package com.ndky.infooms;

import com.ndky.infooms.entity.SysUserRole;
import com.ndky.infooms.service.SysUserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chenqingsheng
 * @date 2021/1/21 11:06
 */
@SpringBootTest
public class SysUserRoleServiceTest {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    void insert(){
        SysUserRole sysUserRole = new SysUserRole();

        sysUserRole.setUserId(5L);
        sysUserRole.setRoleId(2L);
        System.out.println(sysUserRoleService.insert(sysUserRole));;
    }

}
