package com.ysb.config.spring.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yinshuaibin
 * @date 2021/3/16 16:42
 * @description 登录失败处理逻辑
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        //返回json数据
        String result;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = "账号过期";
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = "密码错误";
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = "密码过期";
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = "账号不可用";
        } else if (e instanceof LockedException) {
            //账号锁定
            result = "账号锁定";
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = e.getMessage();
        }else{
            //其他错误
            result = e.getMessage();
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(result);
    }
}
