package com.zhangll.visitor.version2;

public interface Extractor {
    void extract(PdfResource pdfResource);
    void extract(WordResource wordResource);
}
