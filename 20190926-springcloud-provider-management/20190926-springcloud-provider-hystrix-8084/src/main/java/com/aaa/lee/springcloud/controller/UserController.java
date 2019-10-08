package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.User;
import com.aaa.lee.springcloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/27 16:30
 * @Description
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author Seven Lee
     * @description
     *      模拟controller调用service的时候抛出异常
     *      selectAllUsers():一定会抛出异常
     *          ---->consumer调用provider的selectAllUsers方法抛出异常
     *          ---->调用selectAllUsers方法的备用方法
     * @param []
     * @date 2019/9/27
     * @return java.util.List<com.aaa.lee.springcloud.model.User>
     * @throws 
    **/
    @RequestMapping("/userAll")
    //@HystrixCommand(fallbackMethod = "selectAllUsersFallBack")
    public List<User> selectAllUsers() throws Exception {
        List<User> userList = userService.selectAllUsers();
        if(userList.size() > 0) {
            // 说明从数据库中查询到了数据
            throw new Exception("模拟异常，使用熔断处理！");
        }
        return userList;
    }

    /**
     * @author Seven Lee
     * @description
     *      当selectAllUsers方法进入异常阶段，hystrix会让consumer就会不再调用selectAllUsers，
     *      直接会调用selectAllUsersFallBack方法
     *      后备方法(备胎):
     *          !!!就算获取不到数据，也一定必须保证正确率能达到100%!!!
     *          后备方法的返回值一定要和常用方法的返回值一模一样
     *          consumer--->常用方法
     *          后备方法的参数一定要和常用方法的参数一模一样(无论是参数类型，还是顺序，还是个数)
     * @param []
     * @date 2019/9/27
     * @return java.util.List<com.aaa.lee.springcloud.model.User>
     * @throws 
    **/
    public List<User> selectAllUsersFallBack() {
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setId(10000L);
        user.setUsername("测试熔断-->用户名");
        user.setPassword("测试熔断-->密码");
        user.setSalt("测试熔断-->盐值");
        userList.add(user);
        return userList;
    }

}
