grammar PlusMinusExpr;
// 左递归会自动解析成非左递归的流程
// A -> Aa1|Aa2|Aa3...|b1|b2|b3...
// 转化成非左递归
// 提取只有只出现一次的(1)A -> b1A'|b2A'|....
// A'代表会出现无数次 (2)A’-> a1A'|a2A'|a3A'...
// v4只能识别直接左递归，对于间件的左递归没法做吗？？？？？？？？？
stat:  expr ';' ;
expr : expr '+' expr
     | expr '-' expr
     | INT
     ;
INT :   [0-9]+ ;
WS  :   [ \t\n\r]+ -> skip ;
