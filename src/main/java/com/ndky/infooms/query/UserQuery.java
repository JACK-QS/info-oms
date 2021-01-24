package com.ndky.infooms.query;

import com.ndky.infooms.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenqingsheng
 * @date 2021/1/21 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {

    private String userRole;

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
}
