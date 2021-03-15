package com.ysb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author yinshuaibin
 * @date 2021/3/15 16:09
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 身份验证管理生成器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    /**
     * HTTP请求安全处理
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    /**
     * HTTP请求安全处理
     * @param web WebSecurity
     * @throws Exception exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
