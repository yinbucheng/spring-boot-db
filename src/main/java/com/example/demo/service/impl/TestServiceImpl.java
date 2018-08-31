package com.example.demo.service.impl;

import com.example.demo.annotation.Transactional2;
import com.example.demo.dao.TestDao;
import com.example.demo.entity.Test;
import com.example.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestDao testDao;

    @Transactional2
    @Override
    public void test() throws Exception {
        Test test = new Test();
        test.setName("test");
        testDao.save(test);
        Test test2 = new Test();
        test2.setName("test2");
        testDao.save(test2);
    }
}
