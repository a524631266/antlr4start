package com.zhangll.visitor.version5;

public class PdfResource extends FileResource {
    public PdfResource(String s) {
        super(s);
    }

    @Override
    public void visit(Operator operator) {
        operator.vistor(this);
    }

}
