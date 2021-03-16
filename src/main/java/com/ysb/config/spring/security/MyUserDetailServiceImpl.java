package com.ysb.config.spring.security;

import com.ysb.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinshuaibin
 * @date 2021/3/16 10:47
 * @description 登录逻辑
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        if(StringUtils.isBlank(name)){
            throw new InternalAuthenticationServiceException("用户名不能为空");
        }
        if ("A".equals(name)){
            throw new InternalAuthenticationServiceException("authError");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("hasRole"));
        // 下边这个密码是BBB
        return new org.springframework.security.core.userdetails.User("AAA", "$2a$10$8z2xvJc3HRikpGWkGXiJVeZBw95dzGDHuLDZKLDw7kxHzKwNO3Zs2", authorities);
    }
}
