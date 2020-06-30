package com.zhangll.visitor.version1;

public abstract class FileResource {
    final private String path;

    protected FileResource(String path) {
        this.path = path;
    }

    public abstract void extract2txt();
    public abstract void zip();
}
