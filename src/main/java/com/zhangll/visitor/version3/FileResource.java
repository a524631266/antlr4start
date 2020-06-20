package com.zhangll.visitor.version3;

public abstract class FileResource {
    final private String path;

    protected FileResource(String path) {
        this.path = path;
    }

    public abstract  void visit(Extractor extractor);
}
