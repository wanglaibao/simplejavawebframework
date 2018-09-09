package com.laibao.simplemvc.service.impl;

import com.laibao.simplemvc.domain.Customer;
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

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties dbConfigProperty = PropsUtil.loadProps("config.properties");
        DRIVER = dbConfigProperty.getProperty("jdbc.driver");
        URL = dbConfigProperty.getProperty("jdbc.url");
        USERNAME = dbConfigProperty.getProperty("jdbc.username");
        PASSWORD = dbConfigProperty.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver",e);
        }
    }

    @Override
    public List<Customer> getCustomerList(String keyWord) {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("close connection failure",e);
                }
            }
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
