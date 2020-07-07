package com.ps.sec.mapper;

import java.util.List;

public interface RoleMapper {
    List<String> findRoleNameByUsername(String username);

    List<String> findRoleNameByMenuUrl(String url);
}
