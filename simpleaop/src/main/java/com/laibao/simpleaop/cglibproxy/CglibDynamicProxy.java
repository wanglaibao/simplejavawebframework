package com.laibao.simpleaop.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理
 * @author laibao wang
 * @date 2018-09-14
 * @version 1.0
 */
public class CglibDynamicProxy implements MethodInterceptor{

    public <T> T getProxyObj(Class<T> clazz) {
        return (T) Enhancer.create(clazz,this);
    }

    @Override
    public Object intercept(Object targetObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(targetObj,args);
    }
}
