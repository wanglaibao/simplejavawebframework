package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 类操作工具类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface ClassUtil {
    Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * @Description: 获取类加载器
     * @return ClassLoader
     */
    static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    static Class<?> loadClass(String className,boolean isInitialized) {
        Class<?> clazz = null;
        try {
            clazz =Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    static Set<Class<?>> getClassSet(String packageName){
        //TODO
        return null;
    }
}
