package com.wu.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
//@EnableDiscoveryClient
@EnableFeignClients
//@EntityScan("com.xuecheng.framework.domain.ucenter")//扫描实体类
//@ComponentScan(basePackages={"com.xuecheng.api"})//扫描接口
//@ComponentScan(basePackages={"com.xuecheng.framework"})//扫描common下的所有类
@SpringBootApplication
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
