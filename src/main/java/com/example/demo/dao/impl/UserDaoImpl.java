package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.tx.DataSourceManager;
import com.example.demo.tx.JdbcTemplate;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("insert into test2.t_person(name,age,id_card)values(?,?,?)",user.getName(),user.getAge(),user.getIdCard());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update test2.t_person set name=?,age=?,id_card=? where id=?",user.getName(),user.getAge(),user.getIdCard(),user.getIdCard());
    }

    @Override
    public User findOne(Long id) {
        return jdbcTemplate.findOne("select * from test2.t_person where id =?",User.class,id);
    }

    @Override
    public List<User> listAll() {
        return jdbcTemplate.findList("select * from test2.t_person",User.class);
    }

    @Override
    public int deleteOne(Long id) {
        return jdbcTemplate.update("delete from test2.t_person where id =?",id);
    }

}
