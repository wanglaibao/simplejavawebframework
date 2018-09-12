package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IO流操作工具类
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public interface IOStreamUtil {
    Logger logger = LoggerFactory.getLogger(IOStreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param inputStream
     */
    static String getStrFromInputStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
//        BufferedReader reader;
//        reader = new BufferedReader(new InputStreamReader(inputStream));
//        try {
//            while ((str = reader.readLine()) != null) {
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            logger.error("get string line failure",e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
