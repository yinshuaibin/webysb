package com.ysb.config.spring.security;

import com.ysb.config.spring.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author yinshuaibin
 * @date 2021/3/16 16:39
 * @description 登录成功逻辑处理
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功");
        // 更新用户表上次登录时间、更新人、更新时间等字段
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getPassword());
        // 一样的效果 User principal = (User)authentication.getPrincipal()
        System.out.println(principal);
        // 生成token返回前台
        String sign = JwtUtil.sign(principal.getUsername());
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(new HashMap<String, String>(2){{put("code","200");put("msg",sign);}}.toString());
    }
}
