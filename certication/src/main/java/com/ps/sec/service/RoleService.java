package com.ps.sec.service;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户名获得角色名称
     */
    List<String> findRoleNameByUsername(String username);

    /**
     * 根据菜单url获得需要拥有的角色
     */
    List<String> findRoleNameByMenuUrl(String url);
}
