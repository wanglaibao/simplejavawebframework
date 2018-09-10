package com.laibao.simplemvc.helper;

import com.laibao.simplemvc.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

    private static final QueryRunner  QUERY_RUNNER = new QueryRunner();

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
            connection = CONNECTION_HOLDER.get();
            if (connection == null) {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.error("get connection failure",e);
        }finally {
            CONNECTION_HOLDER.set(connection);
        }
        return connection;
    }

  /*
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
            }
        }
    }*/

    public static void closeConnection() {
        Connection connection = CONNECTION_HOLDER.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection,sql,new BeanListHandler<>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        }finally {
            //closeConnection(connection);
            closeConnection();
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;
        Connection connection = getConnection();
        try {
            entity = QUERY_RUNNER.query(connection,sql,new BeanHandler<>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure",e);
            throw new RuntimeException(e);
        }finally {
            //closeConnection(connection);
            closeConnection();
        }
        return entity;
    }
}
