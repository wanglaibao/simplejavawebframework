package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.context.support.AbstractApplicationContextV5;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.dao.v2.AccountDao;
import com.laibao.spring.diy.dao.v2.ItemDao;
import com.laibao.spring.diy.service.v2.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextV1Test {


    @Test
    public void testGetBeanProperty_With_RefPropertyOnly() {
        ApplicationContextV2 applicationContext = new AbstractApplicationContextV5("petstore-v2.xml"){
            @Override
            protected Resource getResourceByPath(String path) {
                return new ClassPathResource(path);
            }
        };

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDao());

        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStoreService.getItemDao() instanceof ItemDao);
    }
}
