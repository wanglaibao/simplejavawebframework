package com.laibao.simplemvc.service.impl;

import com.laibao.simplemvc.domain.Customer;
import com.laibao.simplemvc.helper.DataBaseHelper;
import com.laibao.simplemvc.service.CustomerService;
import com.laibao.simplemvc.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

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
        Connection connection = null;
        try {
            connection = DataBaseHelper.getConnection();
            String sql = "select * from customer";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setRemark(resultSet.getString("remark"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("execute sql error",e);
        }finally {
            DataBaseHelper.closeConnection(connection);
        }
        return customerList;
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
