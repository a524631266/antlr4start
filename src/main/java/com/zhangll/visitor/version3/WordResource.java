package com.zhangll.visitor.version3;

public class WordResource extends FileResource {
    public WordResource(String s) {
        super(s);
    }

    @Override
    public void visit(Extractor extractor) {
        extractor.extract(this);
    }

}
