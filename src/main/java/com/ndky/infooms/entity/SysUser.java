package com.ndky.infooms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "sys_id", type = IdType.AUTO)
    private Long sysId;

    /**
     * 用户姓名
     */
    private String sysName;

    /**
     * 用户密码
     */
    private String sysPassword;

    /**
     * 用户性别
     */
    private String sysSex;

    /**
     * 用户手机号
     */
    private String sysMobile;

    /**
     * 用户邮箱
     */
    private String sysEmail;

    /**
     * 用户办公地址
     */
    private String sysOfficeAddress;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;

    /**
     * 逻辑删除：1-删除、0-未删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;


}
