package com.li.handler.auth;



import com.alibaba.fastjson.JSON;
import com.li.Result;
import com.li.ResultInfo;
import com.li.service.impl.auth.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登陆成功
 *
 **/
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //验证成功来到这个处理器
        //然后获取用户信息

        response.setContentType("application/json;charset=utf-8");
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        response.getWriter().write(JSON.toJSONString(Result.success()
                .code(ResultInfo.LOGIN_SUCCESS.getCode())
                .message(ResultInfo.LOGIN_SUCCESS.getMessage()).data("user",user)));
    }
}
