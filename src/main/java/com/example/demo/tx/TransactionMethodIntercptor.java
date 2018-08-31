package com.example.demo.tx;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionMethodIntercptor implements org.aopalliance.intercept.MethodInterceptor {


    private String createKey(Method method) {
        String key = method.getDeclaringClass().getName()+"-"+method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes!=null){
            for(Class clazz:parameterTypes){
                key+="-"+clazz.getName();
            }
        }
        return key;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
       Method method =  methodInvocation.getMethod();
        Object result = null;
        String key = createKey(method);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>生成唯一标示:"+key);
        try{
            DataSourceManager.doBegian(key);
            result =  methodInvocation.proceed();
            DataSourceManager.commit(key);
        }catch (Exception e){
            DataSourceManager.rollback(key);
            throw new RuntimeException(e);
        }finally {
            DataSourceManager.releaseConn(key);
        }
        return result;
    }
}
