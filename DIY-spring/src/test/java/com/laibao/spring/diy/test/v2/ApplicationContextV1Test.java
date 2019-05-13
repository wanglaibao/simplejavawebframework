package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.context.support.AbstractApplicationContextV5;
import com.laibao.spring.diy.context.support.AbstractApplicationContextV6;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV6;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV7;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.dao.v2.AccountDao;
import com.laibao.spring.diy.dao.v2.ItemDao;
import com.laibao.spring.diy.service.v2.PetStoreService;
import com.laibao.spring.diy.service.v2.PetStoreServiceV2;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicationContextV1Test {


    @Test
    public void testGetBeanProperty_With_RefProperty_And_Only_StringTypeValueProperty() {
        /*
        ApplicationContextV2 applicationContext = new AbstractApplicationContextV5("petstore-v2.xml"){
            @Override
            protected Resource getResourceByPath(String path) {
                return new ClassPathResource(path);
            }
        };

        */
        ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV6("petstore-v2.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService.getAccountDao());

        assertNotNull(petStoreService.getItemDao());

        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        //
        assertEquals("金戈",petStoreService.getOwner());

    }



    @Test
    public void testGetBeanProperty_With_RefProperty_And_OtherTypedValueProperty() {
        /*
        ApplicationContextV2 applicationContext = new AbstractApplicationContextV6("petstore-v2_1.xml"){
            @Override
            protected Resource getResourceByPath(String path) {
                return new ClassPathResource(path);
            }
        };
        */
        ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV7("petstore-v2_1.xml");

        PetStoreServiceV2 petStoreService = (PetStoreServiceV2) applicationContext.getBean("petStore");

        assertNotNull(petStoreService.getAccountDao());

        assertNotNull(petStoreService.getItemDao());

        assertEquals("金戈",petStoreService.getOwner());

        //other type value property
        assertEquals(100,petStoreService.getVersion());
    }
}
