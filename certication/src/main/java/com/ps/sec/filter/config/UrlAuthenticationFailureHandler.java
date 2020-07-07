package com.ps.sec.filter.config;

import com.alibaba.fastjson.JSON;
import com.ps.sec.filter.util.PageResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @apiNote 自定义登录失败处理器：返回状态码402
 * @author fb
 */
@Component
public class UrlAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        PageResult result=new PageResult();
        result.setCode(402);
        result.setMsg(e.getMessage());
        result.setData(null);

        httpServletResponse.setStatus(402);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
       // httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
