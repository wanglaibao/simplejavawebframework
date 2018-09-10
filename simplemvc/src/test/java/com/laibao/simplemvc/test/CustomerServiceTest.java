package com.laibao.simplemvc.test;

import com.alibaba.fastjson.JSON;
import com.laibao.simplemvc.domain.Customer;
import com.laibao.simplemvc.service.CustomerService;
import com.laibao.simplemvc.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(JSON.toJSONString(customerList));
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerTest() {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        System.out.println(JSON.toJSONString(customer));
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name","毛马甲");
        fieldMap.put("contact","小花猫");
        fieldMap.put("telephone","12312312313");
        fieldMap.put("email","1313asdf@gmail.com");
        boolean flag = customerService.createCustomer(fieldMap);
        Assert.assertTrue(flag);
    }

    @Test
    public void updateCustomerTest() {
        long id = 3;
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name","毛马甲123");
        fieldMap.put("contact","小花猫123");
        fieldMap.put("telephone","135000000");
        fieldMap.put("email","aaaaa@gmail.com");
        boolean flag = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(flag);
    }

    @Test
    public void deleteCustomerTest() {
        long id = 3;
        boolean flag = customerService.deleteCustomer(id);
        Assert.assertTrue(flag);
    }
}
