#           SPRING-DIY
```
  学习Spring框架的主要目的是下面三个
    1:框架背后的设计思想

    2:概念抽象[设计人员是如何进行概念抽象的]

    3:代码组织结构[设计人员是如何进行代码组织的]
```

### DIY的过程

```
    1:  从简单的结构开始[基于XML的BeanFactory,只有缺省构造函数的Bean]

    2:  setter注入和有参构造函数

    3:  注解和auto-scan扫描

    4:  基于Java动态代理来实现AOP

    5:  基于Cglib来实现AOP
```

### DIY-Spring 大纲

* 概述[第一周]

```
    1:  课程简介

    2:  介绍Spring IOC 和 AOP

    3:  介绍TDD开发方式以及重构的方法
```

* Basic BeanFactory and ApplicationContextV1 [第二周]

```
    1:  最简单的结构,基于XML的BeanFactory,缺省构造函数的Bean

    2:  BeanDefinition接口和默认实现GenericBeanDefinition

    3:  Exception的处理[设计合理的异常类]

    4:  SRP单一职责的理解和应用[对不合理的地方利用该原则来进行重构]

    5:  ApplicationContext抽象以及ClassPathXmlApplicationContext的实现

    6:  Resource,BeanDefinitionRegistry的抽象
```

* 实现setter注入 [第三周]

```
    1:  Bean的Scope问题

    2:  SingletonBeanRegistry接口

    3:  PropertyValue

    4:  RuntimeBeanReference

    5:  BeanDefinitionValueResolve的抽象

    6:  使用Intospector机制[内省机制]

    7:  TypeConverter实现从字符串到特定类型的转换

    8:  从createBean【Bean创建】到initiateBean【Bean初始化】和populateBean【注入Bean】的拆分

```

* 实现构造函数注入 [第四周]

```

```

* 实现注解和auto-scan [第五周]

* 实现AOP [第六周]
*
*
























