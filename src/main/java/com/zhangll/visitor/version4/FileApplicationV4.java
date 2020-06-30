package com.zhangll.visitor.version4;

import java.util.ArrayList;
import java.util.List;

public class FileApplicationV4 {
    public static void main(String[] args) {
        Compress compress = new CompressImpl();
        Extractor extractor = new ExtractorImpl();
        List<FileResource> fileResources = getAllFileResource();
        for (FileResource fileResource : fileResources) {
            fileResource.visit(extractor);
            fileResource.visit(compress);
        }

    }

    private static List<FileResource> getAllFileResource() {
        ArrayList<FileResource> objects = new ArrayList<>();

        objects.add(new WordResource("/wordpath"));
        objects.add(new PdfResource("/pdfpath"));
        return objects;

    }


}
