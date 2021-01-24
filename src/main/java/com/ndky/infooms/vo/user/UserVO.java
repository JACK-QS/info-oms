package com.ndky.infooms.vo.user;

import com.ndky.infooms.entity.SysUser;
import lombok.Data;

/**
 * @author chenqingsheng
 * @date 2021/1/22 10:22
 */
@Data
public class UserVO extends SysUser{

    private String userRole;

    public UserVO() {

    }

    public UserVO(String userRole){
        this.userRole = userRole;
    }
}
