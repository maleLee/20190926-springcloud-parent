package com.aaa.lee.springcloud.fallback;

import com.aaa.lee.springcloud.model.User;
import com.aaa.lee.springcloud.service.IUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/29 10:36
 * @Description
 *      @Component和@Service的区别
 *      @Autowired和@Resource的区别
 **/
@Component
public class IUserServiceFallbackFactory implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable throwable) {
        // create返回的是一个接口
        return new IUserService() {
            @Override
            public List<User> selectAllUsers() {
                System.out.println("测试服务熔断，IUserService已经抛出异常！");
                return null;
            }
        };
    }

}
