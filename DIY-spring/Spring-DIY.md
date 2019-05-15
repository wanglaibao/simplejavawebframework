#           SPRING-DIY || SPRING-DIY || SPRING-DIY || SPRING-DIY

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

* OverView[第一周]

```
    1:  课程简介

    2:  介绍Spring IOC 和 AOP

    3:  介绍TDD开发方式以及Refactoring重构的方法
```

* Basic BeanFactory and ApplicationContext and Resource and ClassLoader and Bean Scope[第二,三周]

```

    1:  最简单的结构,基于XML的BeanFactory,缺省构造函数的Bean

    2:  BeanDefinition接口和默认实现GenericBeanDefinition

    3:  Exception的处理[设计合理的异常类]

    4:  SRP单一职责的理解和应用[对不合理的地方利用该原则来进行重构]

    5:  BeanDefinitionRegistry的抽象

    6:  ApplicationContext抽象以及ClassPathXmlApplicationContext,FileSystemXmlApplicationContext的实现

    7:  Resource的抽象以及ClassPathResource,FileSystemResource的实现

    8:  基于Resource的实现来重构ClassPathXmlApplicationContext,FileSystemXmlApplicationContext的实现

    9:  基于模板设计模式来重构ClassPathXmlApplicationContext,FileSystemXmlApplicationContext的实现

    10: 目前的所有实现中ClassLoader都是默认的，如何进行改造，使能够动态设置类加载器ClassLoader

    11: 抽象ConfigurableFactory接口,提供设置ClassLoader的能力

    12：根据ConfigurableFactory来重构有关的类

    13: 如何在抽象类AbstractApplicationContext中传递类加载器ClassLoader

    14:  Bean的Scope问题:Singleton or Prototype

    15:  修改BeanDefinition的定义以及GenericBeanDefinition默认实现,使他们满足Bean的Scope:Singleton or Prototype

    16:  专门为Singleton Scope而生的SingletonBeanRegistry接口

```

* Setter Injection [第四,五周]

```
    1:

    2:

    3:

    4:  PropertyValue

    5:  RuntimeBeanReference

    6:  BeanDefinitionValueResolve的抽象

    7:  使用Intospector机制[内省机制]

    8:  TypeConverter实现从字符串到特定类型的转换

    9:  从createBean【Bean创建】到initiateBean【Bean初始化】和populateBean【注入Bean】的拆分

    10: 引入Apache Common-BeanUtils的解决方案
```

* Constructor Injection [第六周]

```

    1:  引入Constructor-Argument

    2:  如何找到合适的构造器

    3:  ConstrutorResolver

    4:  TestCase的整理引入TestSuit

```

* Java Annotation And Bean Auto-Scan Implements[第七,八周]

```
    1:  Java Annotation注解的讲解

    2:  使用ASM读取类的Metadata

    3:  读取一个包下所有的class作为Resource

    4:  新的BeanDefinition实现类引入

    5:  给auto-scan的Bean命名:BeanNameGenerator新的抽象

    6:  DependencyDescriptor,InjectionMetadata,InjectedElement

    7:  用AutowiredAnnotationProcessor实现注入

    8:  Bean的生命周期

    9:  BeanPostProcessor接口及其实现和调用

```

* AOP Implements[第九,十周]

```
    1:  代理模式和动态代理模式

    2:  CGLib和Java动态代理的原理

    3:  PointCut，Advice等AOP概念

    4:  实现Pointcut和MethodMatcher，MethodLocatingFactory

    5:  给定一个对象及相应的方法和一系列拦截器(beforeAdvice,afterAdvice)

    6:  需要实现正确的调用次序实现CGLibProxyFactory

    7:  实现“合成”Bean

    8:  实现对resolveInnerBean使用AspectJAutoProxyCreator进行封装

    9:  用Java动态代理实现AOP

    10: 课程总结

```

*
*
























