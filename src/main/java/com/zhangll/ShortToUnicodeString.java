package com.zhangll;

public class ShortToUnicodeString extends ArrayInitBaseListener {
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.println('"');
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.println('"');
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        Integer integer = Integer.valueOf(ctx.INT().getText());

        System.out.printf("\\u%04x", integer);
    }
}
