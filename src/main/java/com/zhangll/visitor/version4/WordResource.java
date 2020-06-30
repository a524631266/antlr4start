package com.zhangll.visitor.version4;

public class WordResource extends FileResource {
    public WordResource(String s) {
        super(s);
    }

    @Override
    public void visit(Extractor extractor) {
        extractor.extract(this);
    }

    @Override
    public void visit(Compress compress) {
        compress.compress(this);
    }

}
