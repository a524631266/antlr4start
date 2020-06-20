package com.zhangll.visitor.version3;

import java.util.ArrayList;
import java.util.List;

public class FileApplicationV3 {
    public static void main(String[] args) {
        Extractor extractor = new ExtractorImpl();
        List<FileResource> fileResources = getAllFileResource();
        for (FileResource fileResource : fileResources) {
            fileResource.visit(extractor);
        }

    }

    private static List<FileResource> getAllFileResource() {
        ArrayList<FileResource> objects = new ArrayList<>();

        objects.add(new WordResource("/wordpath"));
        objects.add(new PdfResource("/pdfpath"));
        return objects;

    }


}
