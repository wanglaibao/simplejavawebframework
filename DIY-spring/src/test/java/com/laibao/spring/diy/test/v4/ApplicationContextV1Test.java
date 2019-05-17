package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV8;
import com.laibao.spring.diy.service.v3.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ApplicationContextV1Test {

    @Test
    public void testGetBean_With_Annotation() {
        ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV8("petstore-v4.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
        assertEquals(1,petStoreService.getVersion());
    }
}
