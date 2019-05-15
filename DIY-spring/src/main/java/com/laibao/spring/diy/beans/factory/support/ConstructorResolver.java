package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.ConstructorArgument;
import com.laibao.spring.diy.beans.SimpleTypeConverter;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.config.ConfigurableFactoryV1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 该类的作用是 找到一个合适的构造函数,
 * 并且通过构造函数的参数来完成类的实例化操作
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 只是要求通过BeanFactory来获取Bean的实例
     * 所以无需要DeFaultBeanFactory之类的了
     */
    private ConfigurableFactoryV1 beanFactory;

    public ConstructorResolver(ConfigurableFactoryV1 beanFactory) {
        this.beanFactory = beanFactory;
    }


    /**
     * 构造函数参数解析以及与Bean定义绑定
     * @param beanDefinition
     * @return Object
     */
    public Object autowireConstructor(final BeanDefinitionV4 beanDefinition) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;

        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getId(), "nstantiation of bean failed, can't resolve class", e);
        }
        Constructor<?>[] candidates = beanClass.getConstructors();
        //BeanDefinitionValueResolverV2 valueResolve = new BeanDefinitionValueResolverV2(this.beanFactory);
        CommonBeanDefinitionValueResolver valueResolver = new CommonBeanDefinitionValueResolver(this.beanFactory);
        ConstructorArgument constructorArgument = beanDefinition.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        for (int i = 0; i < candidates.length; i++) {

            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != constructorArgument.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];

            boolean result = this.valuesMatchTypes(parameterTypes,
                    constructorArgument.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);

            if (result) {
                constructorToUse = candidates[i];
                break;
            }

        }
        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a create instance using " + constructorToUse);
        }
    }

    /**
     *
     * @param parameterTypes
     * @param valueHolders
     * @param argsToUse
     * @param valueResolver
     * @param typeConverter
     * @return boolean
     */
    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolverTemplate valueResolver,
                                     SimpleTypeConverter typeConverter) {


        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            //获取参数的值,可能是TypedStringValue,也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();
            try {
                //获得真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                //如果参数类型是int,但是值是字符串,例如"3",还需要转型
                //如果转型失败,则抛出异常,说明这个构造器不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功,记录下来
                argsToUse[i] = convertedValue;

            } catch (Exception e) {
                logger.error(e);
                return false;
            }

        }
        return true;
    }
}
