grammar Expr;

/** The start rule; begin parsing here. */
prog:   stat+ ;

stat:   expr NEWLINE                
    |   ID '=' expr NEWLINE        
    |   NEWLINE                   
    ;
// 优先级 * / 是等价的，高于 + -
expr:   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   INT                    
    |   ID                    
    |   '(' expr ')'         
    ;

ID  :   [a-zA-Z]+ ;      // match identifiers <label id="code.tour.expr.3"/>
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace
