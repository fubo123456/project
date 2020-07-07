package com.ps.sec.filter.config;

import com.alibaba.fastjson.JSON;
import com.ps.sec.filter.util.PageResult;
import com.ps.sec.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fb
 * @apiNote 自定义登录成功处理器：返回状态码200
 */
@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Value("${jwt.token-header-key}")
    private String tokenHeaderKey; //token响应头Key
    @Value("${jwt.token-prefix}")
    private String tokenPrefix; //token前缀
    @Value("${jwt.token-secret}")
    private String tokenSecret; //token秘钥
    @Value("${jwt.token-expiration}")
    private Long tokenExpiration; //token过期时间
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");

        PageResult result=new PageResult();
        result.setCode(200);
        result.setMsg("Login Success");

        String username = (String) authentication.getPrincipal(); //表单输入的用户名
        String menu = userService.findMenuInfoByUsername(username);
        List<String>list=new ArrayList<>();
        list.add(menu);
        result.setData(list);

        // 生成token并设置响应头
        Claims claims = Jwts.claims();
        claims.put("role", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username) //设置用户名
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) //设置token过期时间
                .signWith(SignatureAlgorithm.HS512, tokenSecret).compact(); //设置token签名算法及秘钥
        httpServletResponse.addHeader(tokenHeaderKey, tokenPrefix + " " + token); //设置token响应头

        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
