package com.example.demo.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DataSourceManager {

    private static DataSource dataSource = new ComboPooledDataSource("mysql");

    private static ThreadLocal<ConnectionDefination> definationThreadLocal = new ThreadLocal<>();

    public static void doBegian(String key)throws Exception{
        ConnectionDefination connectionDefination = definationThreadLocal.get();
        if(connectionDefination==null){
            connectionDefination = new ConnectionDefination();
            definationThreadLocal.set(connectionDefination);
            connectionDefination.setTransactionFlag(true);
            Connection realConn = dataSource.getConnection();
            realConn.setAutoCommit(false);
            connectionDefination.setRealConn(realConn);
            connectionDefination.setKey(key);
            Connection proxyConn = (Connection) Proxy.newProxyInstance(DataSourceManager.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(method.getName().equals("close")){
                        return null;
                    }
                    return method.invoke(realConn,args);
                }
            });
            connectionDefination.setProxyConn(proxyConn);
        }
    }


    public static DataSource getDataSource(){
        ConnectionDefination  connectionDefination = definationThreadLocal.get();
        if(connectionDefination==null)
            return dataSource;
        return (DataSource) Proxy.newProxyInstance(DataSourceManager.class.getClassLoader(), new Class[]{DataSource.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               if(method.getName().equals("getConnection")){
                  ConnectionDefination  connectionDefination = definationThreadLocal.get();
                  return connectionDefination.getProxyConn();
               }
                return method.invoke(dataSource,args);
            }
        });
    }


    public static void commit(String key){
        ConnectionDefination  connectionDefination = definationThreadLocal.get();
        if(connectionDefination!=null&&connectionDefination.getKey().equals(key)){
            try {
                connectionDefination.getRealConn().commit();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>key:"+key+" 提交事务");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(String key){
        ConnectionDefination  connectionDefination = definationThreadLocal.get();
        if(connectionDefination!=null&&connectionDefination.getKey().equals(key)){
            try {
                connectionDefination.getRealConn().rollback();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>key:" + key + " 回滚资源");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void releaseConn(String key){
        ConnectionDefination  connectionDefination = definationThreadLocal.get();
        if(connectionDefination!=null&&connectionDefination.getKey().equals(key)){
            try {
                connectionDefination.getRealConn().close();
                System.out.println(">>>>>>>>>>>>>>>>>>>key:"+key+" 释放资源");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            definationThreadLocal.remove();
        }
    }


}
