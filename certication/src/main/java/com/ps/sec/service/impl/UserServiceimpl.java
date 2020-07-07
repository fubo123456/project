package com.ps.sec.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.sec.domain.User;
import com.ps.sec.mapper.UserMapper;
import com.ps.sec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public String findPasswordByUsername(String username) {
        return mapper.findPasswordByUsername(username);
    }

    @Override
    public Integer addUserInfo(User user) {
        List<User> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setRegistrationTime(sdf.format(new Date()));
        list.add(user);
        return mapper.addUserInfo(list);
    }

    @Override
    public String findMenuInfoByUsername(String username) {
        return mapper.findMenuInfoByUsername(username);
    }

}
