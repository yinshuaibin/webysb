package com.ysb.config.spring.jwt;

import com.ysb.config.spring.security.MyUserDetailServiceImpl;
import com.ysb.config.spring.security.NoPermissionEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yinshuaibin
 * @date 2021/3/19 15:18
 * @description jwt过滤器, myUserDetailService用来验证用户 noPermissionEntryPoint用来手动
 *              抛出异常, myUserDetailService.loadUserByUsername调用后并不会调用LoginSuccessHandler
 */
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private MyUserDetailServiceImpl myUserDetailService;

    private NoPermissionEntryPoint noPermissionEntryPoint;

    @Autowired
    public JwtFilter(MyUserDetailServiceImpl myUserDetailService,
                     NoPermissionEntryPoint noPermissionEntryPoint){
        this.myUserDetailService = myUserDetailService;
        this.noPermissionEntryPoint = noPermissionEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(JwtUtil.getLoginSign());
        String tokenPrefix = JwtUtil.getTokenPrefix();
        if (StringUtils.isNotBlank(header) && header.startsWith(tokenPrefix)){
            String token = header.replace(tokenPrefix, "");
            UserDetails userDetails;
            try {
                String username = JwtUtil.getUsername(token);
                if (username == null){
                    throw new AuthenticationServiceException("权限校验失败");
                }
                if(JwtUtil.verify(token, username)){
                    userDetails = myUserDetailService.loadUserByUsername(username);
                    if (userDetails != null){
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,
                                userDetails.getPassword(), userDetails.getAuthorities()));
                    }else {
                        throw new AuthenticationServiceException("用户不存在");
                    }
                }
            } catch (Exception e){
                log.error("权限校验出现异常:{}", e);
                noPermissionEntryPoint.commence(httpServletRequest, httpServletResponse,
                        new AuthenticationServiceException(e.getMessage()));
                return;
            }
        }
        // 进入下一个过滤器, 由于SecurityContext,无权限并不会放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
