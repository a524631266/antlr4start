grammar Ambiguities;
expr:   expr
    |   ID                  // primary (precedence 1)
    |   BEGIN                 // primary (precedence 2)
    ;
ID  :  [a-z]+;
BEGIN : 'begin';
//WS  :   [ \t\n\r]+ -> skip ;
