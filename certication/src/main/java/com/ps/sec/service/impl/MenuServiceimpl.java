package com.ps.sec.service.impl;


import com.ps.sec.mapper.MenuMapper;
import com.ps.sec.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceimpl implements MenuService {
    @Autowired
    private MenuMapper mapper;

    @Override
    public List<String> findAllMenuUrl() {
        return mapper.findAllMenuUrl();
    }
}
