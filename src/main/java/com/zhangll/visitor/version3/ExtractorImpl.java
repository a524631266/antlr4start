package com.zhangll.visitor.version3;

public class ExtractorImpl implements Extractor {
    @Override
    public void extract(PdfResource pdfResource) {
        System.out.println("extract pdf");
    }

    @Override
    public void extract(WordResource wordResource) {
        System.out.println("extract word");
    }
}
