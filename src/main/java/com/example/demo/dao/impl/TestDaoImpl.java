package com.example.demo.dao.impl;

import com.example.demo.dao.TestDao;
import com.example.demo.entity.Test;
import com.example.demo.tx.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Test test) {
        return jdbcTemplate.update("insert into test2.t_test(name) values(?)",test.getName());
    }

    @Override
    public int update(Test test) {
        return jdbcTemplate.update("update test2.t_test set name=? where id = ?",test.getName(),test.getId());
    }
}
