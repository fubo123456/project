package com.ps.sec.mapper;

import com.ps.sec.domain.User;

import java.util.List;

public interface UserMapper {
    String findPasswordByUsername(String username);

    String findMenuInfoByUsername(String username);

    Integer addUserInfo(List<User> user);


}
