package com.laibao.spring.diy.beans.factory.xml;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistry;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinition;
import com.laibao.spring.diy.core.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;

public class XmlBeanDefinitionReaderV2 {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    private BeanDefinitionRegistry register;

    public XmlBeanDefinitionReaderV2(BeanDefinitionRegistry register) {
        this.register = register;
    }

    /**
     * 根据对应的资源加载Bean定义并且注册Bean
     * @param resource
     */
    public void loadBeanDefinition(Resource resource) {
        InputStream inputStream = null;
        try {
            //ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            inputStream = resource.getInputStream();

            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            Element rootElement = document.getRootElement(); // <beans>
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                register.registerBeanDefinition(id,beanDefinition);
            }

        } catch (Exception e) {
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
