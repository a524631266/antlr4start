package com.tour.alter;



import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DataDriver {
    public static void main(String[] args) throws IOException {
        String inputFile = null;
        if ( args.length>0 ) {
            inputFile = args[0];
        }
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        DataLexer dataLexer = new DataLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(dataLexer);
        DataParser dataParser = new DataParser(tokens);
        ParseTree file = dataParser.file();
        System.out.println(file.toStringTree(dataParser));
        //(file (group 2 (sequence 1 2)) (group 3 (sequence 1 2 3)))
        // 这个是序列文件的树形表达式，但是anttlr Preview 是错误的表达
    }
}
