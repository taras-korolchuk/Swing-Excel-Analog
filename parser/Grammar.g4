/**
* Define a grammar called Grammar
*/
grammar Grammar;
/*
* Parser Rules
*/

expression :
LPAREN expression RPAREN #ParenthesizedExpr
| operatorToken=(SUBTRACT | ADD) expression #UnarExpr
| operatorToken=(NMAX|NMIN) LPAREN expression (COMA expression)* RPAREN #NmaxExpr
| expression EXPONENT expression #ExponentialExpr
| expression operatorToken=(MULTIPLY | DIVIDE) expression #MultiplicativeExpr
| expression operatorToken=(DIV|MOD) expression #DivisiveExpr
| expression operatorToken=(ADD | SUBTRACT) expression #AdditiveExpr
| NUMBER #NumberExpr

;
/*
* Lexer Rules
*/
NUMBER : INT ('.' INT)?;
INT : ('0'..'9')+;
EXPONENT : '^';
MULTIPLY : '*';
DIVIDE : '/';
SUBTRACT : '-';
ADD : '+';
LPAREN : '(';
RPAREN : ')';
DIV: 'div';
MOD: 'mod';
NMAX: 'nmax';
NMIN: 'nmin';
COMA: ',';
WS : [ \t\r\n] -> channel(HIDDEN);