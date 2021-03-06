package com.laibao.spring.diy.core.io;

import com.laibao.spring.diy.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path,"File Path must be not null");
        //init(path);
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    /*
    private void init(String path) {
        this.path = path;
        this.file = new File(path);
    }*/

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file [ "+ this.file.getAbsolutePath() + " ] ";
    }
}
