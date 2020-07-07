package com.ps.sec.service.impl;

import com.ps.sec.mapper.RoleMapper;
import com.ps.sec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceimpl implements RoleService {
    @Autowired
    private RoleMapper mapper;

    @Override
    public List<String> findRoleNameByUsername(String username) {
        return mapper.findRoleNameByUsername(username);
    }

    @Override
    public List<String> findRoleNameByMenuUrl(String url) {
        return mapper.findRoleNameByMenuUrl(url);
    }
}
