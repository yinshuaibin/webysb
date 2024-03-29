package com.ysb.config.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2021/3/16 16:47
 * @description  登出
 */
@Component
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String, String> result = new HashMap<>(2);
        result.put("code", "200");
        result.put("msg", "logout");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(String.valueOf(result));
    }
}
