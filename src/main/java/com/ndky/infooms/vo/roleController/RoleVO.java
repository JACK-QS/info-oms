package com.ndky.infooms.vo.roleController;

import com.ndky.infooms.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenqingsheng
 * @date 2021/1/23 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleVO extends SysRole {

    private String[] ids;
}
