package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.PropertyValue;
import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV5;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV4;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * 专门用来进行setter注入测试使用的
 * 目前Bean的属性中只有引用类型的属性
 * eg: <property name="accountDao" ref="accountDao"/>
 */
public class BeanDefinitionTest {

    private DefaultBeanFactoryV5 factory;

    private XmlBeanDefinitionReaderV4 reader;

    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactoryV5();

        reader = new XmlBeanDefinitionReaderV4(factory);

        resource = new ClassPathResource("petstore-v2.xml");
    }

    @After
    public void tearDown() {
        System.out.println("关闭Bean定义属性的有关测试");
    }

    @Test
    public void testGetBeanDefinition_With_RefProperty_And_No_ValueProperty() {
        //DefaultBeanFactoryV5 factory = new DefaultBeanFactoryV5();
        //XmlBeanDefinitionReaderV4 reader = new XmlBeanDefinitionReaderV4(factory);
        reader.loadBeanDefinition(resource);

        BeanDefinitionV3 petStoreService = factory.getBeanDefinitionV3("petStore");
        List<PropertyValue> pvs = petStoreService.getPropertyValues();

        Assert.assertTrue(pvs.size() == 3);

        {
            PropertyValue pv = this.getPropertyValue("accountDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValue("itemDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }


    private PropertyValue getPropertyValue(String name, List<PropertyValue> propertyValues) {

        /*
        for (PropertyValue propertyValue : propertyValues) {
            if (propertyValue.getName().equals(name)) {
                return propertyValue;
            }
        }*/

        Optional<PropertyValue> optionalPropertyValue =  propertyValues.stream()
                                                        .filter(propertyValue -> propertyValue.getName().equals(name))
                                                        .findAny();

        return optionalPropertyValue.orElse(null);
    }

}
