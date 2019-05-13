package com.laibao.spring.diy.beans;

import java.util.List;

/**
 * 新增 ConstructorArgument getConstructorArgument()方法
 *
 * 主要用于构造函数方式注入的时候
 *
 * 获对象的构造函数参数列表所包含的参数值
 */
public interface BeanDefinitionV4 {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    String SCOPE_DEFAULT = "";

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();

    /**
     * 获取一个Bean对象所包含的属性值列表
     * @return List<PropertyValue>
     */
    List<PropertyValue> getPropertyValues();

    /**
     *
     * @return ConstructorArgument
     */
    ConstructorArgument getConstructorArgument();
}
