package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface CastUtil {
    Logger logger = LoggerFactory.getLogger(CastUtil.class);

    /**
     * 转为String类型
     * @param obj
     * @return String
     */
    static String castString(Object obj) {
        return CastUtil.castString(obj,"");
    }

    /**
     * 转为String类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return String
     */
     static String castString(Object obj,String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    static int castInt(Object obj) {
        return castInt(obj,0);
    }

    static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if( obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try{
                    intValue = Integer.parseInt(strValue);
                }catch(NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    static long castLong(Object obj) {
        return castLong(obj,0);
    }

    static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try{
                    longValue = Long.parseLong(strValue);
                }catch(NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    static Double castDouble(Object obj) {
        return castDouble(obj,0);
    }

    static Double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try{
                    doubleValue = Double.parseDouble(strValue);
                }catch(NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    static boolean castBoolean(Object obj) {
        return castBoolean(obj,false);
    }

    static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if(obj!=null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
