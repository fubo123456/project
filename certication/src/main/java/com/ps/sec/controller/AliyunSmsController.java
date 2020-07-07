package com.ps.sec.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ps.sec.filter.util.PageResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/sms")
@Api(tags = "短信验证")
public class AliyunSmsController {
    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsController.class);

    @PostMapping("/sendSms")
    @ResponseBody
    public PageResult sendSms(HttpServletRequest httpServletRequest, String phoneNumber) {
        List list = new ArrayList();
        try {
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient，(分别填写自己的AccessKey ID和Secret)
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G8FPwisxJUXB24Rxnw3", "tzXWHJAU9GWTA4a0hCxJ0J2eVUMEJV");
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumber);
            request.setSignName("Povasoft");
            request.setTemplateCode("SMS_194700044");
            request.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("verifyCode", verifyCode);
            session.setAttribute("verifyCodeCreateTime", System.currentTimeMillis());
            list.add(sendSmsResponse);
            return PageResult.suc(1, list, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.suc(1, list, "error");
        }
    }

    @PostMapping("/dologin")
    @ResponseBody
    public PageResult doLogin(@RequestParam("code") String code, @RequestParam("phoneNumber") String phoneNumber, HttpSession session) {
        PageResult pageResult = new PageResult();
        try {
            if (session.getAttribute("verifyCodeCreateTime") != null &&
                    (System.currentTimeMillis() - Long.valueOf(String.valueOf(session.getAttribute("verifyCodeCreateTime")))) > 1000 * 60) {
                session.removeAttribute("verifyCode");
                session.removeAttribute("phoneNumber");
                session.removeAttribute("verifyCodeCreateTime");
                pageResult.setMsg("验证码过期！");
            }else if(!phoneNumber.equals(session.getAttribute("phoneNumber"))){
                session.removeAttribute("verifyCode");
                session.removeAttribute("phoneNumber");
                session.removeAttribute("verifyCodeCreateTime");
                pageResult.setMsg("请先获取验证码！");
            } else if (session.getAttribute("verifyCode") != null
                    && code.equals(session.getAttribute("verifyCode"))
            ) {
                session.removeAttribute("verifyCode");
                session.removeAttribute("verifyCodeCreateTime");
                session.removeAttribute("phoneNumber");
                pageResult.setMsg("登录成功！");
            } else {
                pageResult.setMsg("验证码错误！");
            }
        } catch (Exception e) {
            pageResult.setMsg("登陆失败！");
            e.printStackTrace();
        }
        return pageResult;
    }
}
