package com.laibao.simplemvc.helper;

import com.laibao.simplemvc.service.impl.CustomerServiceImpl;
import com.laibao.simplemvc.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public final class DataBaseHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(DataBaseHelper.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //静态代码块不可以位于接口中
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

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure",e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
            }
        }
    }
}
