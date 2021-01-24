package com.ndky.infooms.config.security.UserInfo;

import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.service.SysRoleService;
import com.ndky.infooms.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:04
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 查询用户
        SysUser sysUser = sysUserService.findByName(username);
        if (sysUser != null) {
            // 查询权限
            SysRole sysRole = sysRoleService.findByUserId(sysUser.getSysId());
            authorities.add(new SimpleGrantedAuthority(sysRole.getRoleAuthoritr()));
            return new User(username,sysUser.getSysPassword(),authorities);
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }
}
