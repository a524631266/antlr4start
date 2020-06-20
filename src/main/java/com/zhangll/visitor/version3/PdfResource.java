package com.zhangll.visitor.version3;

public class PdfResource extends FileResource {
    public PdfResource(String s) {
        super(s);
    }

    @Override
    public void visit(Extractor extractor) {
        extractor.extract(this);
    }


}
