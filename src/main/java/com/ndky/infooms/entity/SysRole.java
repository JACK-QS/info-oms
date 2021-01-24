package com.ndky.infooms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色权限
     */
    private String roleAuthoritr;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;

    /**
     * 角色修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;


}
