package com.ndky.infooms;

import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chenqingsheng
 * @date 2021/1/21 10:50
 */
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void inserRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleAuthoritr("ROLE_GENERAL");
        sysRole.setRoleName("普通用户");
        sysRoleService.insert(sysRole);

    }


}
