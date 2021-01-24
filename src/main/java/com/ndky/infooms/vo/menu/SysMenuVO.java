package com.ndky.infooms.vo.menu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenqingsheng
 * @date 2021/1/21 11:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 父菜单主键
     */
    private String parentId;

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
    private Integer menuWeight;

    /**
     * 菜单创建人
     */
    private String menuCreateName;

    /**
     * 是否显示（0不显示，1显示）
     */
    private Boolean isShow;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;

}
