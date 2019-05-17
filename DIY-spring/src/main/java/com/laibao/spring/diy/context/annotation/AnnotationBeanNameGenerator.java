package com.laibao.spring.diy.context.annotation;

import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.beans.factory.annotation.AnnotatedBeanDefinition;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistryV5;
import com.laibao.spring.diy.beans.factory.support.BeanNameGenerator;
import com.laibao.spring.diy.core.annotation.AnnotationAttributes;
import com.laibao.spring.diy.core.type.AnnotationMetadata;
import com.laibao.spring.diy.util.ClassUtils;
import com.laibao.spring.diy.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinitionV5 beanDefinition, BeanDefinitionRegistryV5 registry) {
        if (beanDefinition instanceof AnnotatedBeanDefinition) {
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) beanDefinition);
            if (StringUtils.hasText(beanName)) {
                return beanName;
            }
        }
        return buildDefaultBeanName(beanDefinition, registry);
    }

    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition  annotatedDef) {
        AnnotationMetadata annotationMetadata = annotatedDef.getMetadata();
        Set<String> types = annotationMetadata.getAnnotationTypes();
        String beanName = null;
        for (String type : types) {
            AnnotationAttributes attributes = annotationMetadata.getAnnotationAttributes(type);
            if (attributes.get("value") != null) {
                Object value = attributes.get("value");
                if (value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.hasLength(strVal)) {
                        beanName = strVal;
                    }
                }
            }
        }
        return beanName;
    }
    /**
     * Derive a default bean name from the given bean definition.
     * <p>The default implementation delegates to {@link #buildDefaultBeanName(BeanDefinitionV5)}.
     * @param definition the bean definition to build a bean name for
     * @param registry the registry that the given bean definition is being registered with
     * @return the default bean name (never {@code null})
     */
    protected String buildDefaultBeanName(BeanDefinitionV5 definition, BeanDefinitionRegistryV5 registry) {
        return buildDefaultBeanName(definition);
    }

    /**
     * Derive a default bean name from the given bean definition.
     * <p>The default implementation simply builds a decapitalized version
     * of the short class name: e.g. "mypackage.MyJdbcDao" -> "myJdbcDao".
     * <p>Note that inner classes will thus have names of the form
     * "outerClassName.InnerClassName", which because of the period in the
     * name may be an issue if you are autowiring by name.
     * @param definition the bean definition to build a bean name for
     * @return the default bean name (never {@code null})
     */
    protected String buildDefaultBeanName(BeanDefinitionV5 definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }
}
