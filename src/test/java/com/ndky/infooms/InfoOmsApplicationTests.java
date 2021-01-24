package com.ndky.infooms;

import com.ndky.infooms.controller.SysMenuController;
import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InfoOmsApplicationTests {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void contextLoads() {
        SysMenu byId = sysMenuService.getById("3aeac5860d2144babbb9d755af2824cf");
        System.out.println(byId);

    }

}
