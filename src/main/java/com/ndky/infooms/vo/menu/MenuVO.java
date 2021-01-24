package com.ndky.infooms.vo.menu;

import com.ndky.infooms.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chenqingsheng
 * @date 2021/1/21 11:22
 */
@Data
@Builder
public class MenuVO {

    private String name;

    private String icon;

    private String code;

    private List<SysMenu> sysMenus;

}
