package com.laibao.smart.framework;

import com.laibao.smart.framework.helper.*;
import com.laibao.smart.framework.util.ClassUtil;

import java.util.stream.Stream;

/**
 * 加载相应的Helper类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface HelperLoader {
    static void init() {
        Stream.of( ClassHelper.class,
                    BeanHelper.class,
                    AopHelper.class,
                    IocHelper.class,
                    ControllerHelper.class)
                .forEach(clazz ->  ClassUtil.loadClass(clazz.getName(),true));
    }
}
