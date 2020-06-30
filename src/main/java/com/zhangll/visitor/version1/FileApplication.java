package com.zhangll.visitor.version1;

import java.util.ArrayList;
import java.util.List;

public class FileApplication {
    public static void main(String[] args) {
        List<FileResource> fileResources = getAllFileResource();
        for (FileResource fileResource : fileResources) {

            fileResource.extract2txt();
            fileResource.zip();
        }

    }

    private static List<FileResource> getAllFileResource() {
        ArrayList<FileResource> objects = new ArrayList<>();

        objects.add(new WordResource("/aaa"));
        objects.add(new PdfResource("/aaa"));
        return objects;

    }


}
