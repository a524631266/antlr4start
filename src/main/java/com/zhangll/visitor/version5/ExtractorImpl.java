package com.zhangll.visitor.version5;

public class ExtractorImpl implements Operator {

    @Override
    public void vistor(PdfResource pdfResource) {
        System.out.println("extract pdf: " + pdfResource.path);
    }

    @Override
    public void vistor(WordResource wordResource) {
        System.out.println("extract word" + wordResource.path);
    }
}
