package com.ps.sec.controller;

import com.ps.sec.domain.User;
import com.ps.sec.service.UserService;
import com.ps.sec.filter.util.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户表")
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation("用戶表新增")
    @PostMapping("/insert")
    public PageResult insert(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return PageResult.result(1, bindingResult.getFieldError().getDefaultMessage());
        }
        service.addUserInfo(user);
        return PageResult.result(0, "success");
    }
}
