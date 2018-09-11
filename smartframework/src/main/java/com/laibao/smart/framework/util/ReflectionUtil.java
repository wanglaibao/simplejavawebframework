package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 发射工具类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface ReflectionUtil {
    Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建对象实例
     * @param clazz
     * @return Object
     */
    static Object newInstance(Class<?> clazz) {
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param params
     * @return Object
     */
    static Object invokeMethod(Object obj, Method method, Object... params) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,params);
        } catch (Exception e) {
            logger.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置实例变量的值
     * @param obj
     * @param field
     * @param value
     */
    static void setFieldValue(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e) {
            logger.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
