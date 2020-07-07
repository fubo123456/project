package com.ps.sec.filter.config;

import com.alibaba.fastjson.JSON;
import com.ps.sec.filter.util.PageResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fb
 * @apiNote 自定义权限不足处理器：返回状态码403
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {


        PageResult result=new PageResult();
        result.setCode(403);
        result.setMsg(e.getMessage());
        result.setData(null);

        httpServletResponse.setStatus(403);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //httpServletResponse.getWriter().write(GsonUtil.GSON.toJson(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
