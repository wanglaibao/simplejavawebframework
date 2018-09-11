package com.laibao.smart.framework.helper;

import com.laibao.smart.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 辅助类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public final class BeanHelper {

    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        beanClassSet.forEach(clazz -> {
            Object obj = ReflectionUtil.newInstance(clazz);
            BEAN_MAP.put(clazz,obj);
        });
    }

    /**
     * 获取 Bean Map 映射
     * @return Map<Class<?>,Object>
     */
    public static Map<Class<?>,Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     *
     * @param clazz
     * @param <T>
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz)) {
            throw new RuntimeException("can not get bean by class : " + clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }
}
