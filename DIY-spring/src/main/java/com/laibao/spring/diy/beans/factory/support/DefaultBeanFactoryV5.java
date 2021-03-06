package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.PropertyValue;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.config.ConfigurableFactoryV1;
import com.laibao.spring.diy.util.Assert;
import com.laibao.spring.diy.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactoryV5 extends DefaultSingletonBeanRegistry
                                    implements ConfigurableFactoryV1,BeanDefinitionRegistryV3{

    private final Map<String,BeanDefinitionV3> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

    public DefaultBeanFactoryV5() {

    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return  (this.beanClassLoader != null) ? beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public Object getBean(String beanId) {
        Assert.notNull(beanId,"beanId can not be null");
        BeanDefinitionV3 beanDefinition = getBeanDefinitionV3(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition dose not exist !");
        }

        //判断BeanDefinition的作用域范围
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    /*
    private Object createBean(BeanDefinitionV3 beanDefinition) {

        ClassLoader classLoader = this.getBeanClassLoader();//ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }
    }*/


    public Object createBean(BeanDefinitionV3 beanDefinition) {
        //创建实例
        Object bean = instantiateBean(beanDefinition);

        //设置属性
        populateBean(beanDefinition, bean);

        return bean;
    }


    private Object instantiateBean(BeanDefinitionV3 beanDefinition) {

        ClassLoader classLoader = this.getBeanClassLoader();//ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }
    }


    private void populateBean(BeanDefinitionV3 beanDefinition, Object bean) {
        // 获取一个bean下所有的PropertyValue
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }

        // 实例化BeanDefinitionValueResolve，并传入当前的DefaultBeanFactory5
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);

        //SimpleTypeConverter coverter = new SimpleTypeConverter();

        try {
            for (PropertyValue propertyValue : propertyValues) {
                String propertyName = propertyValue.getName();
                //对于ref来说就是beanName,对于value 来说就是value
                Object originalValue = propertyValue.getValue();
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);


                //假设现在originalValue表示的是ref=accountDao,已经通过resolve得到了accountDao对象，接下来
                //如何调用petStoreService的setAccountDao方法？
                //备注:使用到了java.beans规范中的Introspector的内省机制拿到bean的相关信息,包括其属性,方法


                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getName().equals(propertyName)) {
                        //Object convertedValue = coverter.convertIfNecessary(resolvedValue, propertyDescriptor.getPropertyType());
                        //通过反射的方式调用set方法
                        propertyDescriptor.getWriteMethod().invoke(bean, resolvedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class["+beanDefinition.getBeanClassName()+"]",e);
        }

    }


    @Override
    public BeanDefinitionV3 getBeanDefinitionV3(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinitionV3(String beanId, BeanDefinitionV3 beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }
}
