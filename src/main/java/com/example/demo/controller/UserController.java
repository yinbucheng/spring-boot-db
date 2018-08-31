package com.example.demo.controller;

import com.example.demo.service.ITestService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping("/test")
    public Object test(){
        try {
            userService.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }
}
