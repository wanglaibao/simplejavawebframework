package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.BeanFactoryV1;
import com.laibao.spring.diy.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  DefaultBeanFactoryV1
 *  违背了单一职责原则 SRP[Single Responsibility Principle]
 *  所以需要对进行重构使之满足单一职责原则
 *
 */

public class DefaultBeanFactoryV1 implements BeanFactoryV1 {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactoryV1(String configFile) {
        loadBeanDefinition(configFile);
    }

    @Deprecated
    private void loadBeanDefinition(String configFile) {

        InputStream inputStream = null;
        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            inputStream = classLoader.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            Element rootElement = document.getRootElement(); // <beans>
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                beanDefinitionMap.put(id,beanDefinition);
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

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {

        BeanDefinition beanDefinition = getBeanDefinition(beanId);
        if (beanDefinition == null) {
            //return null;
            throw new BeanCreationException("Bean Definition dose not exist");
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }
        /*
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        */
        //return null;
    }
}
