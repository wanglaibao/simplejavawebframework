package com.laibao.spring.diy.test.v3;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.factory.support.ConstructorResolver;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV7;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV5;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.service.v3.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ConstructorResolverTest {


    @Test
    public void testConstructorResolver_AutoWireConstructor_Arguments() {
        DefaultBeanFactoryV7 factory = new DefaultBeanFactoryV7();
        XmlBeanDefinitionReaderV5 reader = new XmlBeanDefinitionReaderV5(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
        BeanDefinitionV4 beanDefinition = factory.getBeanDefinitionV4("petStore");
        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStoreService petStoreService = (PetStoreService) resolver.autowireConstructor(beanDefinition);
        assertEquals(1, petStoreService.getVersion());
        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
    }
}
