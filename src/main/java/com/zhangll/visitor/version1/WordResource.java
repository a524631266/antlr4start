package com.zhangll.visitor.version1;

public class WordResource extends FileResource {
    protected WordResource(String path) {
        super(path);
    }

    @Override
    public void extract2txt() {
        System.out.println("extract word txt");
    }

    @Override
    public void zip() {
        System.out.println("zip word");
    }
}
