package com.zhangll.visitor.version5;

public class WordResource extends FileResource {
    public WordResource(String s) {
        super(s);
    }

    @Override
    public void visit(Operator operator) {
        operator.vistor(this);
    }

}
