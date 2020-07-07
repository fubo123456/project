package com.ps.sec.filter.config;

import com.alibaba.fastjson.JSON;
import com.ps.sec.filter.util.PageResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fb
 * @apiNote 自定义注销成功处理器：返回状态码200
 */
@Component
public class UrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


        PageResult result=new PageResult();
        result.setCode(200);
        result.setMsg("Logout Success!!");
        result.setData(null);

        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
