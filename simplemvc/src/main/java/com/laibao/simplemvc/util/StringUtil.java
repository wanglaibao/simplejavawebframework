package com.laibao.simplemvc.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public final class StringUtil {

    public static boolean isEmpty(String str) {
        if(str !=null) {
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String[] splitString(String str,String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
