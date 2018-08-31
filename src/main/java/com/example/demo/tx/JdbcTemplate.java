package com.example.demo.tx;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcTemplate {
    /**
     * 插入或更新,删除操作
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql,Object ...params){
        QueryRunner queryRunner = new QueryRunner(DataSourceManager.getDataSource());
        try {
           return  queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T findOne(String sql,Class<T> clazz,Long id){
        QueryRunner queryRunner = new QueryRunner(DataSourceManager.getDataSource());
        try {
          return   queryRunner.query(sql,new BeanHandler<T>(clazz),id);
        } catch (SQLException e) {
           throw  new RuntimeException(e);
        }
    }

    public <T> List<T> findList(String sql,Class<T> clazz,Object ...params){
        QueryRunner queryRunner = new QueryRunner(DataSourceManager.getDataSource());
        try {
            return queryRunner.query(sql,new BeanListHandler<T>(clazz),params);
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }


}
