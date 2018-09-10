package com.laibao.simplemvc.helper;

import com.laibao.simplemvc.util.CollectionUtil;
import com.laibao.simplemvc.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    /**
     * 针对单表的查询
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,Object... params) {
        List<T> entityList = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT * FROM " + getTableName(entityClass);
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

    /**
     * 针对单表的查询
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass,Object... params) {
        T entity = null;
        Connection connection = getConnection();
        String sql = "SELECT * FROM "+ getTableName(entityClass) + " WHERE id = ?";
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

    public static List<Map<String,Object>> executeQuery(String sql,Object... params) {
        List<Map<String,Object>> result = null;
        try{
            Connection connection = getConnection();
            result = QUERY_RUNNER.query(connection,sql,new MapListHandler(),params);
        }catch (Exception e) {
            LOGGER.error("execute query failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return result;
    }

    public static int executeUpdate(String sql,Object... params) {
        int rows = 0;
        try{
            Connection connection = getConnection();
            rows = QUERY_RUNNER.execute(connection,sql,params);
        }catch (Exception e) {
            LOGGER.error("execute update failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return rows;
    }

    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not insert entity: fieldMap is empty!");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(entityClass);

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
//        for (String fieldName: fieldMap.keySet()) {
//            columns.append(fieldName).append(", ");
//            values.append("?, ");
//        }
        fieldMap.forEach((key,value) -> {
            columns.append(key).append(", ");
            values.append("?, ");
        });
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf("?, "),values.length(),")");
        sql += columns + " VALUES " + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;
    }

    public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not update entity: fieldMap is empty!");
            return false;
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        fieldMap.forEach((key,value) -> {
            columns.append(key).append("=?, ");
        });
        sql += columns.substring(0,columns.lastIndexOf(", ")) + "WHERE id=? ";

        List<Object> paramsList = new ArrayList<>();
        paramsList.addAll(fieldMap.values());
        paramsList.add(id);
        Object[] params = paramsList.toArray();

        return executeUpdate(sql,params) == 1;
    }

    public static <T> boolean deleteEntity(Class<T> entityClass,long id) {
        String sql = "DELETE FROM " + getTableName(entityClass) + "WHERE id=? ";
        return executeUpdate(sql,id) == 1;
    }

    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName().toLowerCase();
    }
}
