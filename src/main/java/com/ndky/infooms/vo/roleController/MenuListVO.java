package com.ndky.infooms.vo.roleController;

import com.ndky.infooms.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chenqingsheng
 * @date 2021/1/22 14:29
 */
@Data
@Builder
public class MenuListVO {

    /**
     * 菜单主键
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单别名
     */
    private String menuCode;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单权重
     */
    private int menuWeight;

    /**
     * 菜单是否显示
     */
    private Boolean isShow;

    /**
     * 子菜单
     */
    private List<SysMenu> children;

}
