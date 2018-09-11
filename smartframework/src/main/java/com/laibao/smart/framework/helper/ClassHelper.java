package com.laibao.smart.framework.helper;

import com.laibao.smart.framework.util.ClassUtil;

import java.util.Set;

/**
 * 类操作辅助类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface ClassHelper {
   Set<Class<?>> CLASS_SET = ClassUtil.getClassSet(ConfigHelper.getAppBasePackage());



}
