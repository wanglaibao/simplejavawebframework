package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.PropertyValue;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.config.ConfigurableFactoryV1;
import com.laibao.spring.diy.util.Assert;
import com.laibao.spring.diy.util.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactoryV7 extends DefaultSingletonBeanRegistry
        implements ConfigurableFactoryV1,BeanDefinitionRegistryV4{

    private final Map<String, BeanDefinitionV4> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

    public DefaultBeanFactoryV7() {

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
        BeanDefinitionV4 beanDefinition = getBeanDefinitionV4(beanId);
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


    public Object createBean(BeanDefinitionV4 beanDefinition) {
        //创建实例
        Object bean = instantiateBean(beanDefinition);

        //设置属性
        //populateBean(beanDefinition, bean);
        populateBeanUseApacheCommonBeanUtils(beanDefinition,bean);

        return bean;
    }

    /**
     *  实例化Bean对象【创建一个Bean对象】
     * @param beanDefinition
     * @return Object
     */
    private Object instantiateBean(BeanDefinitionV4 beanDefinition) {

        ClassLoader classLoader = this.getBeanClassLoader();//ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }
    }


    /**
     * 采用 Apache Common BeanUtils工具类来设置Bean对象的有关属性值
     * @param beanDefinition
     * @param bean
     */
    private void populateBeanUseApacheCommonBeanUtils(BeanDefinitionV4 beanDefinition, Object bean) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        CommonBeanDefinitionValueResolver valueResolver = new CommonBeanDefinitionValueResolver(this);
        try {
            for (PropertyValue propertyValue : propertyValues) {
                String propertyName = propertyValue.getName();
                Object originalValue = propertyValue.getValue();
                Object resolver = valueResolver.resolveValueIfNecessary(originalValue);

                //假设现在originalValue表示的是ref=accountDao,已经通过resolver得到了accountDao对象，接下来
                //如何调用petStoreService的setAccountDao方法？
                //通过Apache Common BeanUtils工具类来完成属性值的设置,避免了又一次的循环
                BeanUtils.copyProperty(bean, propertyName, resolver);

            }
        } catch (Exception e) {
            throw new BeanCreationException("Populate bean property failed for["+beanDefinition.getBeanClassName()+"");
        }

    }

    @Override
    public BeanDefinitionV4 getBeanDefinitionV4(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinitionV4(String beanId, BeanDefinitionV4 beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }
}
