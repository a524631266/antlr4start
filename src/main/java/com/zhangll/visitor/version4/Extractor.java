package com.zhangll.visitor.version4;

public interface Extractor {
    void extract(PdfResource pdfResource);
    void extract(WordResource wordResource);
}
