package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/26 15:09
 * @Description
 **/
@RestController
public class UserController {

    private static final String URL = "http://USER-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/userAll")
    public List<User> selectAllUsers() {
        return restTemplate.getForObject(URL+"/userAll", List.class);
    }

    /**
     * @author Seven Lee
     * @description
     *      报错:java.lang.IllegalStateException: No instances available for localhost
     *      找不到localhost的实例
     *      ribbon脱离eureka的时候，是需要正式环境的支持，也就是说所有正式环境的访问路径都不可能是localhost
     *      使用花生壳(外网映射工具)，在window系统中的hosts文件配置HOSTNAME(consumer01)的和IP地址映射
     *      当使用ribbon脱离了eureka的控制后，这个时候不但消费者的localhost无法识别，连生产者的localhost也无法识别，所以必须要配置外网映射
     * @param []
     * @date 2019/9/27
     * @return java.util.List<com.aaa.lee.springcloud.model.User>
     * @throws
    **/
    @RequestMapping("/balanceAllUsers")
    public List<User> selectUsersByLoadBalance() {
        // 1.通过loadBalancerClient对象获取到所有的服务提供者的信息
            // 在application.properties配置文件中有一个配置user-provider.ribbon.listOfServers(value值)
            // 通过在8081,8082,8083中配置的spring.application.name的值进行获取
            // serviceId-->spring.application.name的值
            // serviceId-->也就是源码中的Object key
            // serviceInstance:Server对象
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
        // 2.获取server的ip地址
        String host = serviceInstance.getHost();
        System.out.println(host);
        // 3.获取server的port端口号
        int port = serviceInstance.getPort();
        System.out.println(port);
        // ip+port+requestMapping的路径
        return restTemplate.getForObject("http://"+host+":"+port+"/userAll", List.class);
    }

}
