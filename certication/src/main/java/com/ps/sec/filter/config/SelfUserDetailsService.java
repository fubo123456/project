package com.ps.sec.filter.config;


import com.ps.sec.service.RoleService;
import com.ps.sec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fb
 * @apiNote 自定义用户登录
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username); //任意登录用户名

        String password = userService.findPasswordByUsername(username);
        if (ObjectUtils.isEmpty(password)) {
            throw new UsernameNotFoundException("User name" + username + "not find!!");
        }
        userInfo.setPassword(password); //从数据库获取密码

        Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<>();
        List<String> roles = roleService.findRoleNameByUsername(username);
        for (String roleName : roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName); //用户拥有的角色
            authoritiesSet.add(simpleGrantedAuthority);
        }
        userInfo.setAuthorities(authoritiesSet);

        return userInfo;
    }

}
