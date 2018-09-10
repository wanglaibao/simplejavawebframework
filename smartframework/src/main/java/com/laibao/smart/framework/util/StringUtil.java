package com.laibao.smart.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface StringUtil {

    static boolean isEmpty(String str) {
        if(str !=null) {
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    static String[] splitString(String str,String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
