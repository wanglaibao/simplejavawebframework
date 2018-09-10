package com.laibao.simplemvc.service.impl;

import com.laibao.simplemvc.domain.Customer;
import com.laibao.simplemvc.helper.DataBaseHelper;
import com.laibao.simplemvc.service.CustomerService;
import com.laibao.simplemvc.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService{
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public List<Customer> getCustomerList(String keyWord) {
        List<Customer> customerList = new ArrayList<>();
//        Connection connection = null;
//        String sql = "select * from customer";
//        try {

//            connection = DataBaseHelper.getConnection();
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            ResultSet resultSet = pstm.executeQuery();
//            while (resultSet.next()) {
//                Customer customer = new Customer();
//                customer.setId(resultSet.getLong("id"));
//                customer.setName(resultSet.getString("name"));
//                customer.setContact(resultSet.getString("contact"));
//                customer.setEmail(resultSet.getString("email"));
//                customer.setTelephone(resultSet.getString("telephone"));
//                customer.setRemark(resultSet.getString("remark"));
//                customerList.add(customer);
//            }
/*        } catch (SQLException e) {
            LOGGER.error("execute sql error",e);
        }finally {
            DataBaseHelper.closeConnection(connection);
        }*/
        customerList = DataBaseHelper.queryEntityList(Customer.class,null);
        return customerList;
    }

    @Override
    public Customer getCustomer(long id) {
        //String sql = "select * from customer where id = ?";
        return DataBaseHelper.queryEntity(Customer.class,id);
    }

    @Override
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }

    @Override
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    @Override
    public boolean deleteCustomer(long id) {
        return DataBaseHelper.deleteEntity(Customer.class,id);
    }
}
