package com.example.demo.dao;

import com.example.demo.entity.Test;

public interface TestDao {
    int save(Test test);
    int update(Test test);
}
