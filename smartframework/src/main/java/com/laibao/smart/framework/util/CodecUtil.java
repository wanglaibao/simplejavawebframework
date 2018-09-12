package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 编码和解码操作工具类
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public interface CodecUtil {
    Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    String DEFAULT_CHARSET  = String.valueOf(StandardCharsets.UTF_8);

    /**
     * 对字符串数据源进行编码
     * @param source
     * @return String
     */
    static String encodeURL(String source) {
        String encodeStr = null;
        try {
            encodeStr = URLEncoder.encode(source, DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("encode source failure",e);
            throw new RuntimeException(e);
        }
        return encodeStr;
    }

    static String decodeURL(String source) {
        String decodeStr = null;
        try {
            decodeStr = URLDecoder.decode(source,DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("decode source failure",e);
            throw new RuntimeException(e);
        }
        return decodeStr;
    }
}
