package com.zhangll;

import antlr.ParseTree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        // 1. 创建一个CHarStream,从标准输入读取数据
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        // 2. 创建一个词法分析器,处理输入CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input);

        // 3. 新建一个词法符号的缓冲区,用于存储词法分析其将生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. 新建一个语法分析器,处理词法符号缓冲区中的内容
        ArrayInitParser parser = new ArrayInitParser(tokens);

        ArrayInitParser.InitContext init = parser.init();


        // 5.注入监听其
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ShortToUnicodeString(), init);
        System.out.println();
        // 5. 直接答应结果
//        String string = init.toStringTree(parser);
//        System.out.println(string);

    }
}
