package com.laibao.simplemvc.test;

import com.laibao.simplemvc.domain.Customer;
import com.laibao.simplemvc.service.CustomerService;
import com.laibao.simplemvc.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerServiceImpl();
    }

    @Before
    public void init() {
        //TODO
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList(null);
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerTest() {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() {
        //TODO
    }

    @Test
    public void updateCustomerTest() {
        //TODO
    }

    @Test
    public void deleteCustomerTest() {
        //TODO
    }
}
