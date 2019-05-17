package com.laibao.spring.diy.beans;

import java.util.List;

/**
 * 第五版本 新增对注解和auto-scan注入的支持
 *
 * 新增 ConstructorArgument getConstructorArgument()方法
 *
 * 主要用于构造函数方式注入的时候
 *
 * 获对象的构造函数参数列表所包含的参数值
 */
public interface BeanDefinitionV5 {

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

    /**
     * 判断是否存在构造函数参数
     * @return boolean
     */
    boolean hasConstructorArgumentValues();

    /**
     * 获取Bean对象所对应的Id
     * @return String
     */
    String getId();
}
