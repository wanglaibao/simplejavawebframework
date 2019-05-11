package com.laibao.spring.diy.core.io;

import com.laibao.spring.diy.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        init(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        init(path,classLoader);
    }

    private void init(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.classLoader.getResourceAsStream(path);

        if (inputStream == null) {
            throw new FileNotFoundException(path + " can not opened ");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
