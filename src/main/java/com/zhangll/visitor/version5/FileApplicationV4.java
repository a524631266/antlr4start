package com.zhangll.visitor.version5;

import java.util.ArrayList;
import java.util.List;

public class FileApplicationV4 {
    public static void main(String[] args) {
        Operator compress = new CompressImpl();
        Operator extractor = new ExtractorImpl();
        Operator store = new StoreImpl();
        List<FileResource> fileResources = getAllFileResource();
        for (FileResource fileResource : fileResources) {
            fileResource.visit(extractor);
            fileResource.visit(compress);
            fileResource.visit(store);
        }

    }

    private static List<FileResource> getAllFileResource() {
        ArrayList<FileResource> objects = new ArrayList<>();

        objects.add(new WordResource("/wordpath"));
        objects.add(new PdfResource("/pdfpath"));
        return objects;

    }


}
