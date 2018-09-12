package com.laibao.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 类操作工具类
 * @author laibao wang
 * @date 2018-09-11
 * @version 1.0
 */
public interface ClassUtil {
    Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    String FILE_TYPE = "file";
    String JAR_TYPE = "jar";

    /**
     * 获取类加载器
     * @return ClassLoader
     */
    static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    static Class<?> loadClass(String className,boolean isInitialized) {
        Class<?> clazz = null;
        try {
            clazz =Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    static Set<Class<?>> getClassSet(String packageName){
        //TODO
        return null;
    }



    /**
     * 根据className【类名】获取对应的JVM class 并且添加到集合中
     * @param classSet
     * @param className
     */
    static void addClass(Set<Class<?>> classSet,String className) {
        Class<?> clazz = loadClass(className,false);
        classSet.add(clazz);
    }

    /**
     * 根据包路径和包名获取包名下所有文件对应的JVM class 并且添加到集合中
     * @param classSet
     * @param packagePath
     * @param packageName
     */
    static void addClass(Set<Class<?>> classSet,String packagePath,String packageName) {
//        屏蔽下列代码 采用Java8的lambda表达式类改下
//        File[] files = new File(packagePath).listFiles(
//                new FileFilter() {
//                        public boolean accept(File file) {
//                        // 过滤文件，必须为文件且以.class结尾，或者是文件目录
//                        return ((file.isFile() && file.getName().endsWith(".class")) || (file.isDirectory()));
//                }
//        });

        // 获取子目录
        FileFilter fileFilter = (File file) -> ((file.isFile() && file.getName().endsWith(".class")) || (file.isDirectory()));
        File[] files = new File(packagePath).listFiles(fileFilter);

        List<File> fileList = Arrays.asList(files);

        //获取文件信息
        fileList.stream()
                .filter(File::isFile)
                .map(file -> file.getName().substring(0, file.getName().lastIndexOf(".")))
                .filter(className -> StringUtil.isNotEmpty(className))
                .forEach(className -> {
                    addClass(classSet, packageName + "." + className);
                });

        // 获取文件目录洗信息
        fileList.stream()
                .filter(File::isDirectory)
                .map(file -> file.getName())
                .forEach(fileName -> {
                    // 获取文件为目录
                    String subPackagePath = fileName;
                    // 获取包名
                    String subPackageName = fileName;
                    if (StringUtil.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" +subPackagePath;
                    }
                    if (StringUtil.isNotEmpty(packageName)) {
                        subPackageName = packageName + "/" + subPackageName;
                    }
                    /// 递归遍历文件信息
                    addClass(classSet,subPackagePath,subPackageName);
                });
    /*
        // 遍历获取文件信息
        for (File file : files) {
            String fileName = file.getName();
            // 如果为文件
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(className)) {
                    className = packageName + "." + className;
                }
                addClass(classSet, className);
            }else {
                // 获取文件名称，该文件为目录
                String subPath = fileName;
                if (StringUtils.isNotEmpty(subPath)) {
                    subPath = packageName + "/" + subPath;
                }

                // 获取包名
                String subPackage = packageName;
                if (StringUtils.isNotEmpty(subPackage)) {
                    subPackage = packageName + "." + fileName;
                }
                // 递归遍历文件信息
                addClass(classSet, subPath, subPackage);
            }
        }
        */
    }
}
