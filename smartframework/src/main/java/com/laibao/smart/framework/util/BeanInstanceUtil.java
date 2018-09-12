package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Bean实例化工具类
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public interface BeanInstanceUtil {
    Logger logger = LoggerFactory.getLogger(IOStreamUtil.class);

    /**
     * 对Class进行实例化
     * @param clazz
     * @return Object
     */
    static Object newInstance(Class<?> clazz) {
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            logger.error("class instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 通过反射方式调用对象的方法
     * @param ojb
     * @param method
     * @param params
     * @return Object
     */
    static Object invokeMethod(Object ojb, Method method, Object... params) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(ojb,params);
        } catch (Exception e) {
            logger.error("object method invoke failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 设置成员变量的值
     * @param obj
     * @param field
     * @param value
     */
    static void setFieldValue(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e) {
            logger.error("field set value failure",e);
            throw new RuntimeException(e);
        }
    }
}
