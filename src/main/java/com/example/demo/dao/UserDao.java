package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {
    int save(User user);
    int update(User user);
    User findOne(Long id);
    List<User> listAll();
    int deleteOne(Long id);
}
