package com.example.demo.tx;

import java.sql.Connection;

public class ConnectionDefination {
    //是否开启事务
    private boolean transactionFlag = false;
    //真实数据库连接
    private Connection realConn;
    //代理数据库连接
    private Connection proxyConn;
    //记录最新开始的标记
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isTransactionFlag() {
        return transactionFlag;
    }

    public void setTransactionFlag(boolean transactionFlag) {
        this.transactionFlag = transactionFlag;
    }

    public Connection getRealConn() {
        return realConn;
    }

    public void setRealConn(Connection realConn) {
        this.realConn = realConn;
    }

    public Connection getProxyConn() {
        return proxyConn;
    }

    public void setProxyConn(Connection proxyConn) {
        this.proxyConn = proxyConn;
    }
}
