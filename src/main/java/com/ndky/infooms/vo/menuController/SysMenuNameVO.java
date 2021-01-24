package com.ndky.infooms.vo.menuController;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenqingsheng
 * @date 2021/1/22 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuNameVO{

    private String menuNames;

    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 菜单父级
     */
    public String parentId;

    /**
     * 菜单名称
     */
    public String menuName;

    /**
     * 菜单别名
     */
    public String menuCode;

    /**
     * 菜单链接
     */
    public String menuHref;

    /**
     * 菜单图标
     */
    public String menuIcon;

    /**
     * 菜单级别
     */
    public String menuLevel;

    /**
     * 菜单权重
     */
    public Integer menuWeight;

    /**
     * 菜单是否显示
     */
    public String isShow;

    /**
     * 菜单创建时间
     */
    public Date ctime;

    /**
     * 菜单创建人
     */
    public String menuCreateName;

}
