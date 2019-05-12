package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 新增List<PropertyValue> getPropertyValues()方法
 *
 * 主要用于setter方式注入的时候获对象所包含的属性值列表
 */
public class GenericBeanDefinitionV3 implements BeanDefinitionV3 {

    private String id;

    private String beanClassName;

    private boolean singleton = true;

    private boolean prototype = false;

    private String scope = SCOPE_DEFAULT;

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public GenericBeanDefinitionV3(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);

    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValueList;
    }
}
