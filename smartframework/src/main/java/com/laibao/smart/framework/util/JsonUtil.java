package com.laibao.smart.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 工具类
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public interface JsonUtil {
    Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO对象转化成JSON
     * @param obj
     * @param <T>
     * @return String
     */
    static <T> String toJson(T obj) {
        String jsonStr = null;
        try {
            jsonStr = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("convert ojb to json failure",e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将JSON转化成POJO对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return T
     */
    static <T> T fromJson(String jsonStr,Class<T> clazz) {
        T obj = null;
        try {
            obj = OBJECT_MAPPER.readValue(jsonStr, clazz);
        } catch (Exception e) {
            logger.error("convert json to object failure",e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
