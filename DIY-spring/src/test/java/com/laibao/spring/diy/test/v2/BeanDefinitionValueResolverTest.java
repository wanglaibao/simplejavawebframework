package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.config.TypedStringValue;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionValueResolver;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV5;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV4;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.dao.v2.AccountDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanDefinitionValueResolverTest {

    private DefaultBeanFactoryV5 factory;

    private XmlBeanDefinitionReaderV4 reader;

    Resource resource;

    BeanDefinitionValueResolver resolver;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactoryV5();

        reader = new XmlBeanDefinitionReaderV4(factory);

        resource = new ClassPathResource("petstore-v2.xml");

        reader.loadBeanDefinition(resource);

        resolver = new BeanDefinitionValueResolver(factory);
    }

    @After
    public void tearDown() {
        System.out.println("关闭Bean定义引用属性Ref的有关测试");
    }




    @Test
    public void testResolveRuntimeBeanReference() {
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);

    }

    @Test
    public void testResolveTypeStringValue() {
        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
