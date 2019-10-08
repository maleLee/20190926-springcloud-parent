package com.aaa.lee.springcloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/26 9:25
 * @Description
 *      因为ConfigRest是配置类，所以必须要加上@Configuration/@SpringBootApplication
 *
 *      @SpringBootApplication+@Bean-->相当于application.xml配置文件
 *
 *      RestTemplate:就是模拟了Http协议，使两个controller之间实现调用
 *          post
 *          get
 *          ....
 **/
@SpringBootApplication
public class ConfigRest {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
