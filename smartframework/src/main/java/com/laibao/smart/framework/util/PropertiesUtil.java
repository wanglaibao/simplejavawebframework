package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface PropertiesUtil {
    Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(inputStream==null) {
                throw new FileNotFoundException("file is not found : "+ fileName);
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("load properties file failure",e);
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close input stream failure",e);
                }
            }
        }
        return properties;
    }

    static String getString(Properties properties,String key) {
        return getString(properties, key,"");
    }

    static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if(properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    static int getInt(Properties properties,String key) {
        return getInt(properties, key,0);
    }

    static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if(properties.containsKey(key)) {
            value=CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    static long getLong(Properties properties,String key) {
        return getLong(properties, key,0);
    }

    static long getLong(Properties properties, String key, int defaultValue) {
        long value = defaultValue;
        if(properties.containsKey(key)) {
            value = CastUtil.castLong(properties.getProperty(key));
        }
        return value;
    }

    static double getDouble(Properties properties,String key) {
        return getDouble(properties, key,0.0);
    }

    static double getDouble(Properties properties, String key, double defaultValue) {
        double value = defaultValue;
        if(properties.containsKey(key)) {
            value = CastUtil.castDouble(properties.getProperty(key));
        }
        return value;
    }

    static boolean getBoolean(Properties props,String key) {
        return getBoolean(props, key,false);
    }

    static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value=defaultValue;
        if(props.containsKey(key)) {
            value=CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
