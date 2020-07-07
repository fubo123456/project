package com.ps.sec.filter.config;

import com.alibaba.fastjson.JSON;
import com.ps.sec.filter.util.PageResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fb
 * @apiNote 自定义未登录时：返回状态码401
 */
@Component
public class UrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {


        PageResult result=new PageResult();
        result.setCode(401);
        result.setMsg(e.getMessage());
        result.setData(null);

        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
