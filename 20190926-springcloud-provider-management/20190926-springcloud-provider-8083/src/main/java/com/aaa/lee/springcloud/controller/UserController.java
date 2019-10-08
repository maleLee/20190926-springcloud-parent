package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.User;
import com.aaa.lee.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/26 15:56
 * @Description
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userAll")
    public List<User> selectAllUsers() {
        System.out.println(8083);
        return userService.selectAllUsers();
    }

}
