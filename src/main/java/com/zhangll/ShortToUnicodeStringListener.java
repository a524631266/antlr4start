package com.zhangll;

public class ShortToUnicodeStringListener extends ArrayInitBaseListener {
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {

        if(ctx.INT() != null){
            Integer integer = Integer.valueOf(ctx.INT().getText());
            System.out.printf("\\u%04x", integer);
        }
    }
}
