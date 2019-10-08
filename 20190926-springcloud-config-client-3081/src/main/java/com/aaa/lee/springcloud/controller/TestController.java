package com.aaa.lee.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/30 16:52
 * @Description
 **/
@RestController
public class TestController {

   @Value("${server.port}")
    private String port;

   @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

   @RequestMapping("/config")
   public String testConfig() {
        return port+"-----------------"+driverClassName;
   }

}
