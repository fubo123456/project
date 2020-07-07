package com.ps.sec.service;

import com.ps.sec.domain.User;


public interface UserService {
    //根据用户名查询密码
    String findPasswordByUsername(String username);

    Integer addUserInfo(User user);

    String findMenuInfoByUsername(String username);
}
