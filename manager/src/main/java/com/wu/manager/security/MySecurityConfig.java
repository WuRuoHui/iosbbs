package com.wu.manager.security;

import com.wu.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @program: iosbbs
 * @description: springsecurity配置类
 * @author: Wu
 * @create: 2020-03-12 22:29
 **/

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)    //开权限方法权限注解支持
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder createPwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()    //对登录请求放行
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/json/**").permitAll()
                .antMatchers("/layui/**").permitAll()
                .antMatchers("/index/**").hasAnyAuthority("VIP")
                .antMatchers("/**")   //拦截根目录以及根目录下的子目录
                .fullyAuthenticated()  //对所有的资源进行请求拦截
                .and()
                .formLogin()   //以表单验证的方式对所有的拦截资源进行认证
                .loginPage("/login")   //自定义登录页面
//                .successForwardUrl("/")  //登录成功后的跳转页面
                .successHandler(new MyAuthenticationSuccessHandler())  //登录成功返回json信息
                .failureHandler(new MyAuthenticationFailureHandler())  //登录失败返回json信息
                .and()
                .rememberMe().tokenValiditySeconds(86400)
                .and()
                .logout().deleteCookies("remember-me")
                .and().headers().frameOptions().sameOrigin()
                .and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
//                .csrf().disable()   //关闭跨域访问
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}


