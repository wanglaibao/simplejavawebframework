package com.laibao.smart.framework;

/**
 * @ClassName ConfigConstant
 * @Description 提供相关配置项常量
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface ConfigConstant {
    String CONFIG_FILE="smart.properties";
    String JDBC_DRIVER="smart.framework.jdbc.driver";
    String JDBC_URL="smart.framework.jdbc.url";
    String JDBC_USERNAME="smart.framework.jdbc.username";
    String JDBC_PASSWORD="smart.framework.jdbc.password";

    String APP_BASE_PACKAGE="smart.framework.app.base.package";
    String APP_JSP_PATH="smart.framework.app.jsp.path";
    String APP_ASSET_PATH="smart.framework.app.asset.path";
}
