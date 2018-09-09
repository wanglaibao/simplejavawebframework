package com.laibao.simplemvc.util;

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
public final class PropsUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName) {
        Properties props=null;
        InputStream ins=null;
        try {
            ins=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if(ins == null) {
                throw new FileNotFoundException(fileName+" file is not found");
            }
            props=new Properties();
            props.load(ins);
        }
        catch(IOException e) {
            LOGGER.error("load properties file failure",e);
        }finally {
            if(ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure",e);
                }
            }
        }
        return props;
    }

    public static String getString(Properties props,String key) {
        return getString(props, key,"");
    }

    public static String getString(Properties props, String key, String defaultValue) {
        String value=defaultValue;
        if(props.containsKey(key)) {
            value=props.getProperty(key);
        }
        return value;
    }

    public static int getInt(Properties props,String key) {
        return getInt(props, key,0);
    }

    public static int getInt(Properties props, String key, int defaultValue) {
        int value=defaultValue;
        if(props.containsKey(key)) {
            value=CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    public static boolean getBoolean(Properties props,String key) {
        return getBoolean(props, key,false);
    }

    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value=defaultValue;
        if(props.containsKey(key)) {
            value=CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
