package com.zhangll.visitor.version3;

public interface Extractor {
    void extract(PdfResource pdfResource);
    void extract(WordResource wordResource);
}
