package com.zhangll.visitor.version4;

public class CompressImpl implements Compress {
    @Override
    public void compress(PdfResource pdfResource) {
        System.out.println("compress pdf");
    }

    @Override
    public void compress(WordResource wordResource) {
        System.out.println("compress word");
    }
}
