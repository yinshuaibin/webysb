package com.ysb.config.spring.security;

import com.ysb.config.spring.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author yinshuaibin
 * @date 2021/3/15 16:09
 * @description spring security 核心处理器
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailServiceImpl myUserDetailService;

    /**
     * 无权限
     */
    private NoPermissionEntryPoint customizeAuthenticationEntryPoint;

    /**
     * 正常登出
     */
    private LogoutHandler logoutHandler;

    /**
     * 登录成功
     */
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * 登录失败
     */
    private LoginFailureHandler loginFailureHandler;

    /**
     * 回话过期
     */
    private SessionExpiredExpiredStrategy sessionExpiredExpiredStrategy;

    private JwtFilter jwtFilter;

    @Autowired
    public WebSecurityConfig(MyUserDetailServiceImpl myUserDetailService,
                             NoPermissionEntryPoint customizeAuthenticationEntryPoint,
                             LoginFailureHandler loginFailureHandler,
                             LoginSuccessHandler loginSuccessHandler,
                             LogoutHandler logoutHandler,
                             SessionExpiredExpiredStrategy sessionExpiredExpiredStrategy,
                             JwtFilter jwtFilter){
        this.myUserDetailService = myUserDetailService;
        this.customizeAuthenticationEntryPoint = customizeAuthenticationEntryPoint;
        this.loginFailureHandler = loginFailureHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.logoutHandler = logoutHandler;
        this.sessionExpiredExpiredStrategy = sessionExpiredExpiredStrategy;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份验证管理生成器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //1配置自定义的用户服务  2启用内存用户存储(相当于写死)  3基于数据库表进行验证
        auth.userDetailsService(myUserDetailService);
    }

    /**
     * HTTP请求安全处理
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/ysb/**").hasAnyAuthority("hasRole")
                .antMatchers("/open/**").permitAll()
                .antMatchers("/**").authenticated()
                // 无权限返回信息
                .and().exceptionHandling().authenticationEntryPoint(customizeAuthenticationEntryPoint)
                //登入
                .and().formLogin()
                        //允许所有用户
                        .permitAll()
                        //登录成功处理逻辑
                        .successHandler(loginSuccessHandler)
                        //登录失败处理逻辑
                        .failureHandler(loginFailureHandler)
                .and().logout()
                .permitAll()//允许所有用户
                //登出成功处理逻辑
                .logoutSuccessHandler(logoutHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")
                // 限制同一个用户只允许一个登录
                .and().sessionManagement()
                // session 生成策略用无状态策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .maximumSessions(1)
                //会话信息过期策略会话信息过期策略(账号被挤下线)
                .expiredSessionStrategy(sessionExpiredExpiredStrategy);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * HTTP请求安全处理
     * @param web WebSecurity
     * @throws Exception exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
