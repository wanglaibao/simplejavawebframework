package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.PropertyValue;
import com.laibao.spring.diy.beans.SimpleTypeConverter;
import com.laibao.spring.diy.beans.TypeConverter;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.config.ConfigurableFactoryV1;
import com.laibao.spring.diy.util.Assert;
import com.laibao.spring.diy.util.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactoryV6 extends DefaultSingletonBeanRegistry
        implements ConfigurableFactoryV1,BeanDefinitionRegistryV3{

    private final Map<String,BeanDefinitionV3> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

    public DefaultBeanFactoryV6() {

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


    public Object createBean(BeanDefinitionV3 beanDefinition) {
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


    /**
     * 设置Bean对象的有关属性值
     * @param beanDefinition
     * @param bean
     */
    private void populateBean(BeanDefinitionV3 beanDefinition, Object bean) {
        // 获取一个bean下所有的PropertyValue
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }

        // 实例化BeanDefinitionValueResolve，并传入当前的DefaultBeanFactory5
        BeanDefinitionValueResolverV2 valueResolver = new BeanDefinitionValueResolverV2(this);

        //实例化类型转换对象,以便Bean对象的各个属性进行必要的转换,String类型转换成各个具体类型【int.boolean,double】等等
        TypeConverter typeConverter = new SimpleTypeConverter();

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
                        Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, propertyDescriptor.getPropertyType());
                        //通过反射的方式调用set方法
                        propertyDescriptor.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class["+beanDefinition.getBeanClassName()+"]",e);
        }

    }


    /**
     * 采用 Apache Common BeanUtils工具类来设置Bean对象的有关属性值
     * @param beanDefinition
     * @param bean
     */
    private void populateBeanUseApacheCommonBeanUtils(BeanDefinitionV3 beanDefinition, Object bean) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        BeanDefinitionValueResolverV2 valueResolver = new BeanDefinitionValueResolverV2(this);
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
    public BeanDefinitionV3 getBeanDefinitionV3(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinitionV3(String beanId, BeanDefinitionV3 beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }
}
