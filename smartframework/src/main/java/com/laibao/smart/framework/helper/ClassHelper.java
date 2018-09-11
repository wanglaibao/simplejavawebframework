package com.laibao.smart.framework.helper;

import com.laibao.smart.framework.annotation.Controller;
import com.laibao.smart.framework.annotation.Service;
import com.laibao.smart.framework.util.ClassUtil;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 类操作辅助类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface ClassHelper {

    /**
     * 定义类集合 (用于存放所加载的类)
     */
    Set<Class<?>> CLASS_SET = ClassUtil.getClassSet(ConfigHelper.getAppBasePackage());

    /**
     * 获取应用包名下的所有类
     * @return Set<Class<?>>
     */
    static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有的@Service注解类
     * @return Set<Class<?>>
     */
    static Set<Class<?>> getServiceClassSet(){
//        Set<Class<?>> classSet=new HashSet<Class<?>>();
//        for(Class<?> cls:CLASS_SET) {
//            if(cls.isAnnotationPresent(Service.class)) {
//                classSet.add(cls);
//            }
//        }
//        return classSet;
        return CLASS_SET.stream()
                          .filter(clazz -> clazz.isAnnotationPresent(Service.class))
                          .collect(Collectors.toSet());
    }

    /**
     * 获取应用包名下所有的@Controller注解类
     * @return Set<Class<?>>
     */
     static Set<Class<?>> getControllerClassSet() {
         return CLASS_SET.stream()
                          .filter(clazz -> clazz.isAnnotationPresent(Controller.class))
                          .collect(Collectors.toSet());
     }

    /**
     * 获取应用包名下所有的bean类(@Service,@Controller等)
     * @return Set<Class<?>>
     */
     static Set<Class<?>> getBeanClassSet(){
         return CLASS_SET.stream()
                 .filter(clazz -> clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Controller.class))
                 .collect(Collectors.toSet());
     }
}
