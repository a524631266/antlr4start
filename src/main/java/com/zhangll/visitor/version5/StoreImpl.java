package com.zhangll.visitor.version5;

public class StoreImpl implements Operator {
    @Override
    public void vistor(PdfResource pdfResource) {
        System.out.println("store some into :" + pdfResource.path);
    }

    @Override
    public void vistor(WordResource wordResource) {
        System.out.println("store somei into word:" + wordResource.path);
    }
}
