package com.example.demo.service.impl;

import com.example.demo.annotation.Transactional2;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.ITestService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ITestService testService;

    @Transactional2
    @Override
    public void test() throws Exception {
        testService.test();
        User user = new User();
        user.setAge(22);
        user.setName("kiss");
        user.setIdCard("123456");
        userDao.save(user);
        int i =9/0;
        User user2 = new User();
        user2.setAge(22);
        user2.setName("kiss2");
        user2.setIdCard("123456");
        userDao.save(user);
    }

    @Override
    public void test2() throws Exception {
        User user = new User();
        user.setAge(22);
        user.setName("kiss3");
        user.setIdCard("123456");
        userDao.save(user);
        int i =9/0;
        User user2 = new User();
        user2.setAge(22);
        user2.setName("kiss4");
        user2.setIdCard("123456");
        userDao.save(user);
    }
}
