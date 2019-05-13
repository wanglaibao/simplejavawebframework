package com.laibao.spring.diy.test.v3;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.ConstructorArgument;
import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.config.TypedStringValue;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV7;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV5;
import com.laibao.spring.diy.core.io.ClassPathResource;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BeanDefinitionTest {

    @Test
    public void testGetBeanDefinition_With_Constructor_Argument() {

        DefaultBeanFactoryV7 factory = new DefaultBeanFactoryV7();

        XmlBeanDefinitionReaderV5 reader = new XmlBeanDefinitionReaderV5(factory);

        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));

        BeanDefinitionV4 beanDefinition = factory.getBeanDefinitionV4("petStore");

        assertEquals("com.laibao.spring.diy.service.v3.PetStoreService", beanDefinition.getBeanClassName());

        ConstructorArgument args = beanDefinition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolderList = args.getArgumentValues();

        assertEquals(3, valueHolderList.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference) valueHolderList.get(0).getValue();
        assertEquals("accountDao", ref1.getBeanName());

        RuntimeBeanReference ref2 = (RuntimeBeanReference) valueHolderList.get(1).getValue();
        assertEquals("itemDao", ref2.getBeanName());

        TypedStringValue strValue = (TypedStringValue) valueHolderList.get(2).getValue();
        assertEquals("1", strValue.getValue());
    }
}
