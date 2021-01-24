package com.ndky.infooms.config.security;

import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.service.SysRoleService;
import com.ndky.infooms.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:15
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String name = token.getName();
        String password = token.getCredentials().toString();
        SysUser sysUser = sysUserService.findByName(name);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //判断密码
//        if (!new BCryptPasswordEncoder().matches(password,sysUser.getSysPassword())){
//            throw new UsernameNotFoundException("密码不正确");
//        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 查询权限
        SysRole sysRole = sysRoleService.findByUserId(sysUser.getSysId());
        authorities.add(new SimpleGrantedAuthority(sysRole.getRoleAuthoritr()));

        return new UsernamePasswordAuthenticationToken(name,password,authorities);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
