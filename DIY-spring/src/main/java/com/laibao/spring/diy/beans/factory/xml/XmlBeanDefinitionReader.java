package com.laibao.spring.diy.beans.factory.xml;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistry;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinition;
import com.laibao.spring.diy.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;

public class XmlBeanDefinitionReader {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    private BeanDefinitionRegistry register;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry register) {
        this.register = register;
    }

    public void loadBeanDefinition(String configFile) {

        InputStream inputStream = null;
        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            inputStream = classLoader.getResourceAsStream(configFile);

            /**
             * 下面的判断是为了兼容从文件系统中读取配置文件
             */
            if (inputStream == null) {
                try {
                    inputStream = new FileInputStream(new File(configFile));
                } catch (FileNotFoundException e) {
                    throw new BeanDefinitionStoreException("IOException parsing XML document ",e.getCause());
                }
            }   //判断逻辑结束

            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            Element rootElement = document.getRootElement(); // <beans>
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                //beanDefinitionMap.put(id,beanDefinition);
                this.register.registerBeanDefinition(id,beanDefinition);
            }

        } catch (DocumentException e) {
            //e.printStackTrace();
            throw new BeanDefinitionStoreException("IOException parsing XML document ",e.getCause());
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
