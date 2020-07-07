package com.ps.sec.filter.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author fb
 * @apiNote 自定义web身份验证详细信息
 */

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails implements Serializable {
    private String macAddress;

    CustomWebAuthenticationDetails(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
//        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String s = headerNames.nextElement();
//            String header = httpServletRequest.getHeader(s);
//            System.out.println(s + ": " + header);
//        }
        macAddress = httpServletRequest.getParameter("macAddress");
    }

    String getMacAddress() {
        return macAddress;
    }

}
