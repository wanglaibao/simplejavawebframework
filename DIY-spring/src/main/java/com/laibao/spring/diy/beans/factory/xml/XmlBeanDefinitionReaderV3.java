package com.laibao.spring.diy.beans.factory.xml;

import com.laibao.spring.diy.beans.BeanDefinitionV2;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistryV2;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinitionV2;
import com.laibao.spring.diy.core.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReaderV3 {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    private static String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistryV2 register;

    public XmlBeanDefinitionReaderV3(BeanDefinitionRegistryV2 register) {
        this.register = register;
    }

    /**
     * 根据对应的资源加载Bean定义并且注册Bean
     * @param resource
     */
    public void loadBeanDefinition(Resource resource) {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();

            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            Element rootElement = document.getRootElement(); // <beans>
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinitionV2 beanDefinition = new GenericBeanDefinitionV2(id,beanClassName);

                //新增对Bean定义作用域的判断
                if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }

                register.registerBeanDefinitionV2(id,beanDefinition);
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
