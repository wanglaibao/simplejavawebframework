package com.laibao.simplemvc.service.impl;

import com.laibao.simplemvc.domain.Customer;
import com.laibao.simplemvc.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService{
    @Override
    public List<Customer> getCustomerList(String keyWord) {
        //TODO
        return null;
    }

    @Override
    public Customer getCustomer(long id) {
        //TODO
        return null;
    }

    @Override
    public boolean createCustomer(Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    @Override
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    @Override
    public boolean deleteCustomer(long id) {
        //TODO
        return false;
    }
}
