package com.aaa.lee.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/27 9:09
 * @Description
 *      如果使用的自定义ribbon算法类，当把这个类标识成配置类的时候，不能再使用@SpringBootApplication
 *          需要使用@Configuration注解
 **/
@Configuration
public class RibbonRuleMine {

    @Bean
    public IRule randoms() {
        return new RandomRule();
    }

}
