/**
 * @program: kefubbs
 * @description: 配置类
 * @author: Wu
 * @create: 2019-12-11 23:43
 **/
package com.wu.bbs.config;

import com.wu.bbs.interceptor.AccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
    }
}
