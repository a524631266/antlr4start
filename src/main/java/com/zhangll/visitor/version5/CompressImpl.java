package com.zhangll.visitor.version5;

public class CompressImpl implements Operator {

    @Override
    public void vistor(PdfResource pdfResource) {
        System.out.println("compress pdf: " + pdfResource.path);
    }

    @Override
    public void vistor(WordResource wordResource) {
        System.out.println("compress word: " + wordResource.path);
    }
}
