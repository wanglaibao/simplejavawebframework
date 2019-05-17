package com.laibao.spring.diy.beans.factory.xml;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.beans.ConstructorArgument;
import com.laibao.spring.diy.beans.PropertyValue;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.config.TypedStringValue;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistryV4;
import com.laibao.spring.diy.beans.factory.support.BeanDefinitionRegistryV5;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinitionV4;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinitionV5;
import com.laibao.spring.diy.context.annotation.ClassPathBeanDefinitionScanner;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.util.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReaderV6 {

    private final static Logger logger = Logger.getLogger(XmlBeanDefinitionReaderV6.class);

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    private static String SCOPE_ATTRIBUTE = "scope";

    private static final String PROPERTY_ELEMENT = "property";

    private static final String REF_ATTRIBUTE = "ref";

    private static final String VALUE_ATTRIBUTE = "value";

    private static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

    public static final String AOP_NAMESPACE_URI = "http://www.springframework.org/schema/aop";

    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    private BeanDefinitionRegistryV5 register;

    public XmlBeanDefinitionReaderV6(BeanDefinitionRegistryV5 register) {
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
                String namespaceUri = element.getNamespaceURI();

                if (this.isDefaultNamespace(namespaceUri)) {
                    parseDefaultElement(element);//普通的bean
                } else if (this.isContextNamespace(namespaceUri)) {
                    parseComponentElement(element);//例如<context:component-scan>
                }

            /*
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinitionV5 beanDefinition = new GenericBeanDefinitionV5(id,beanClassName);

                //新增对Bean定义作用域的判断
                if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }

                //新增对Bean的构造函数参数的处理
                parseConstructorArgElements(element,beanDefinition);

                //新增对Bean的各种属性的处理
                parsePropertyElement(element,beanDefinition);
                register.registerBeanDefinitionV5(id,beanDefinition);

                */
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

    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }
    public boolean isContextNamespace(String namespaceUri){
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }

    private void parseComponentElement(Element element) {
        String basePackages = element.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(register);
        scanner.doScan(basePackages);

    }
    private void parseDefaultElement(Element element) {
        String id = element.attributeValue(ID_ATTRIBUTE);
        String className = element.attributeValue(CLASS_ATTRIBUTE);
        BeanDefinitionV5 beanDefinition = new GenericBeanDefinitionV5(id, className);
        if (element.attribute(SCOPE_ATTRIBUTE) != null) {
            beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
        }
        parseConstructorArgElements(element, beanDefinition);
        parsePropertyElement(element, beanDefinition);
        register.registerBeanDefinitionV5(id, beanDefinition);
    }
    /**
     *  获取Bean对象的属性节点并且委托 parsePropertyValue(....)方法,进行属性的解析
     * @param beanElem
     * @param beanDefinition
     */
    private void parsePropertyElement(Element beanElem, BeanDefinitionV5 beanDefinition) {
        Iterator iterator = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(propElem, beanDefinition, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            beanDefinition.getPropertyValues().add(pv);
        }
    }

    /**
     * 解析Bean对象的属性值
     * @param ele
     * @param beanDefinition
     * @param propertyName
     * @return Object
     */
    private Object parsePropertyValue(Element ele, BeanDefinitionV5 beanDefinition, String propertyName) {

        String elementName = (propertyName != null) ? "<property> element for property '" + propertyName + "'" : "<constructor-arg> element";

        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);

        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }

            RuntimeBeanReference ref = new RuntimeBeanReference(refName);

            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue typedStringValue = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return typedStringValue;

        } else {
            //throw new RuntimeException(elementName + " must specify a ref or value");
            throw new BeanDefinitionStoreException(elementName + " must specify a ref or value");
        }
    }


    /**
     * 解析构造函数参数列表
     * @param beanElement
     * @param beanDefinition
     */
    public void parseConstructorArgElements(Element beanElement, BeanDefinitionV5 beanDefinition) {
        Iterator iterator = beanElement.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            parseConstructorArgElement(element, beanDefinition);
        }
    }

    /**
     * 解析构造函数参数列表中每一个具体的参数
     * @param ele
     * @param beanDefinition
     */
    public void parseConstructorArgElement(Element ele, BeanDefinitionV5 beanDefinition) {
        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);

        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);

        Object value = parsePropertyValue(ele, beanDefinition, null);

        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);

        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }

        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        beanDefinition.getConstructorArgument().addArgumentValue(valueHolder);
    }

}
