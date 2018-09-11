package com.laibao.smart.framework.helper;

import com.laibao.smart.framework.ConfigConstant;
import com.laibao.smart.framework.util.PropertiesUtil;

import java.util.Properties;

/**
 * 属性文件 辅助类
 * @author laibao wang
 * @date 2018-09-09
 * @version 1.0
 */
public interface ConfigHelper {
     Properties CONFIG_PROPS = PropertiesUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * @Description: 获取jdbc驱动
     * @return String
     */
     static String getJdbcDriver() {
        return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
     }

    /**
     * @Description: 获取jdbc url
     * @return String
     */
     static String getJdbcUrl() {
        return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
     }

     /**
     * @Description: 获取jdbc 用户名
     * @return String
     */
     static String getJdbcUsername() {
        return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
     }

     /**
      * @Description: 获取jdbc 密码
      * @return String
      */
      static String getJdbcPassword() {
        return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
      }

      /**
       * @Description: 获取应用基础报名
       * @return String
       */
       static String getAppBasePackage() {
        return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
       }

      /**
       *
       * @Description: 获取应用 jsp 路径
       * @return String    返回类型
       */
       static String getAppJspPath() {
         return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
       }

       /**
        * @Description: 获取应用 静态资源路径
        * @return String
        */
        static String getAppAssetPath() {
           return PropertiesUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
        }
}
