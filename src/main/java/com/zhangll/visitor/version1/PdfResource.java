package com.zhangll.visitor.version1;

public class PdfResource extends FileResource {
    protected PdfResource(String path) {
        super(path);
    }

    @Override
    public void extract2txt() {
        System.out.println("extract pdf word");
    }

    @Override
    public void zip() {
        System.out.println("zip pdf");
    }
}
