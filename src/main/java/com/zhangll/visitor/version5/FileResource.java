package com.zhangll.visitor.version5;

public abstract class FileResource {
    final public String path;

    protected FileResource(String path) {
        this.path = path;
    }

    public abstract  void visit(Operator operator);
}
