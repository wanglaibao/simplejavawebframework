package com.laibao.simplemvc.service;

import com.laibao.simplemvc.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface CustomerService {
    List<Customer> getCustomerList(String keyWord);
    Customer getCustomer(long id);
    boolean createCustomer(Map<String,Object> fieldMap);
    boolean updateCustomer(long id,Map<String,Object> fieldMap);
    boolean deleteCustomer(long id);
}
