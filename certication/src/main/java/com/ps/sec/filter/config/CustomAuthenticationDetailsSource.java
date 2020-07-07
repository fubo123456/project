package com.ps.sec.filter.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fb
 * @apiNote 自定义身份验证详细信息源
 */
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest httpServletRequest) {
        //额外验证信息 可添加验证码
        return new CustomWebAuthenticationDetails(httpServletRequest);
    }

}
