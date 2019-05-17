package com.laibao.spring.diy.context.annotation;

import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistryV5;
import com.laibao.spring.diy.beans.factory.support.BeanNameGenerator;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.core.io.support.PackageResourceLoader;
import com.laibao.spring.diy.core.type.classreading.MetadataReader;
import com.laibao.spring.diy.core.type.classreading.SimpleMetadataReader;
import com.laibao.spring.diy.stereotype.Component;
import com.laibao.spring.diy.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1:   给定一个package的名称列表，例如com.laibao.spring.diy.service.v4,com.laibao.spring.diy.dao.v4
 *
 * 2:   对指定的package进行扫描,找到那些标记为@Component的类,创建ScannedGenericBeanDefinition,并且注册到BeanFactory中。
 */
public class ClassPathBeanDefinitionScanner {
    private final Log logger = LogFactory.getLog(getClass());

    private final BeanDefinitionRegistryV5 registry;

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistryV5 registry) {
        this.registry = registry;
    }

    /**
     *
     * @param packagesToScan 传入的base-package 字符串
     * @return
     */
    public Set<BeanDefinitionV5> doScan(String packagesToScan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");

        Set<BeanDefinitionV5> beanDefinitions = new LinkedHashSet<BeanDefinitionV5>();
//      对每一个basePackages进行循环
        for (String basePackage : basePackages) {
            Set<BeanDefinitionV5> candidates = findCandidateComponents(basePackage);
            for (BeanDefinitionV5 candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinitionV5(candidate.getId(), candidate);
            }
        }
        return beanDefinitions;
    }


    /**
     *
     * @param basePackage
     * @return Set<BeanDefinitionV5>
     */
    public Set<BeanDefinitionV5> findCandidateComponents(String basePackage) {
        Set<BeanDefinitionV5> candidates = new LinkedHashSet<BeanDefinitionV5>();
        try {
            // 先拿到Resource
            Resource[] resources = this.resourceLoader.getResources(basePackage);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    //  通过metadataReader 获取AnnotationMetadata 看看其是否有Component注解
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                        //实例化一个ScannedGenericBeanDefinition
                        ScannedGenericBeanDefinition scannedGenericBeanDefinition = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(scannedGenericBeanDefinition, this.registry);
                        scannedGenericBeanDefinition.setId(beanName);
                        candidates.add(scannedGenericBeanDefinition);
                    }
                } catch (Throwable ex) {
                    throw new BeanDefinitionStoreException("Failed to read candidate component class:" + resource, ex
                    );
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", e);
        }
        return candidates;
    }
}
