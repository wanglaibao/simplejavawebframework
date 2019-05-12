package com.laibao.spring.diy.beans;

import java.util.List;

/**
 * 新增List<PropertyValue> getPropertyValues()方法
 *
 * 主要用于setter方式注入的时候获对象所包含的属性值列表
 */
public interface BeanDefinitionV3 {

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

}
