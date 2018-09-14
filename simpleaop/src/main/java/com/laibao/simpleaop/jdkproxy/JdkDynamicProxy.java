package com.laibao.simpleaop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 * @author laibao wang
 * @date 2018-09-14
 * @version 1.0
 */
public class JdkDynamicProxy implements InvocationHandler{

    /**
     * 实现接口的目标对象
     */
    private Object targetObj;

    public JdkDynamicProxy(Object targetObj) {
        this.targetObj = targetObj;
    }

    /**
     * 获取JDK动态代理对象
     * @param <T>
     * @return T
     */
    public <T> T getProxyObj() {
        return (T) Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
                                targetObj.getClass().getInterfaces(),
                                this);
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args
     * @return Object
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(targetObj,args);
        return result;
    }
}
