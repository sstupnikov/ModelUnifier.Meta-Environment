grammar Sql;

options {
	output = AST;
	ASTLabelType = CommonTree;
}

tokens {
	UNION = 'UNION';
	SELECT = 'SELECT';
	INTO = 'INTO';
	SELECT_LIST;
	COLUMN_EXPR;
	COLUMN_NAME;
	FROM = 'FROM';
	FROM_ELEM;
	WHERE = 'WHERE';
	AND = 'AND';
	BETWEEN = 'BETWEEN';
	DISTINCT = 'DISTINCT';
	IN = 'IN';
	LIKE = 'LIKE';
	USER_FUNCTION;
	OBJ_NAME;
	SQL_FUNCTION;
	ESCAPE = 'ESCAPE';
	AS = 'AS';
	ARITH_PREDICATE;
	ARITH_BINARY_OP;
	ARITH_UNARY_OP;
	XMATCH = 'XMATCH';
}

@header {
	package sql;
}

@lexer::header {
	package sql;
}

select
	:	select_clause (UNION^ select_clause)*
	;

select_clause	
	: 	SELECT^ (DISTINCT!)? select_list into_clause? from_clause where_clause?
	;
	
into_clause
	:	INTO^ ID
	;

select_list
	:	STAR
	|	select_elem (COMMA select_elem)*	-> ^(SELECT_LIST select_elem+)
	;	

from_clause
	:	FROM^ from_elem (COMMA! from_elem)*
	;

where_clause
	:	WHERE^ condition
	;
	
select_elem
	:	expr (AS? ID)? 		-> ^(COLUMN_EXPR expr ID?)
	|	ID '=' expr			-> ^(COLUMN_EXPR expr ID)
	|	ID '.' STAR 		-> ^(STAR ID)
	;

from_elem
	:	obj_name (AS? ID)?	-> ^(FROM_ELEM obj_name ID?)
	;

condition
	options {backtrack=true;}
	:	'('! condition ')'!
	|	logical_op (AND^ logical_op)*
	;

logical_op
	options {backtrack=true;}
	:	compare_op
	|	between_op
	|	like_op
	|	in_op
	|	xmatch
	;

number
	:	INTEGER
	|	DECIMAL
	|	FLOAT
	;

xmatch
	:	XMATCH '(' ID COMMA ID COMMA number (COMMA number)* ')'		-> ^(XMATCH ID+ number+)
	;

in_op
	:	expr IN '(' expr (',' expr)* ')'	->	^(IN expr+)
	;

like_op
	:	expr LIKE PRIMESTRING (ESCAPE PRIMESTRING)?		-> ^(LIKE expr PRIMESTRING+)
	;

between_op
	:	expr BETWEEN expr AND expr		->	^(BETWEEN expr+)
	;

compare_op
	:	expr compare_op_symbol^ expr 
	;

compare_op_symbol
	:	 (t=LT | t=LTE | t=EQUAL | t=GTE | t=GT | t=UN_EQUAL) 	-> ^(ARITH_PREDICATE[$t]) 
	;

function
	:	user_function
	|	sql_function
	;

user_function
	:	obj_name '('(expr (',' expr)*)? ')'		-> ^(USER_FUNCTION obj_name expr*)
	;

obj_name
	:	ID ('.' ID)? -> ^(OBJ_NAME ID+)	
	;

sql_function
	:	sql_funcs1 '(' expr ')' 			-> ^(sql_funcs1 expr)
	|	sql_funcs2 '(' expr ',' expr ')'	-> ^(sql_funcs2 expr+)
	|	sql_funcs0 '(' ')' 					-> sql_funcs0
	;

sql_funcs0
	:	(f='PI' | f='RAND')		-> ^(SQL_FUNCTION[$f])
	;
	
sql_funcs2
	:	(f='ATAN2' | f='MOD' | f='POWER' | f='ROUND' | f='TRUNCATE')	-> ^(SQL_FUNCTION[$f])
	;

sql_funcs1
	:	( f='ACOS' | f='ASIN' | f='ATAN' | f='COS' | f='COT' | f='SIN' | f='TAN' | f='ABS' 
		| f='CEILING' | f='DEGREES' | f='EXP' | f='FLOOR' | f='LOG' | f='LOG10' | f='RADIANS'
		| f='SQRT' )	-> ^(SQL_FUNCTION[$f])
	;

column_name
	:	ID ('.' ID)? 	-> ^(COLUMN_NAME ID+)
	;

expr
	options {memoize=true;}
	:	arithm_binary1_term (additive_op^ arithm_binary1_term)*
	;

	
additive_op
	:	(t=PLUS | t=MINUS) 	-> ^(ARITH_BINARY_OP[$t])
	;

arithm_binary1_term
	:	arithm_unary_term (multiplication_op^ arithm_unary_term)*
	;
	
multiplication_op
	:	(t=STAR | t=SLASH | t=MOD) 	-> ^(ARITH_BINARY_OP[$t])
	;

arithm_unary_term
	:	unary_arithm_op^ arithm_term
	|	arithm_term
	;

unary_arithm_op
	:	(t=PLUS | t=MINUS) 	-> ^(ARITH_UNARY_OP[$t])
	;

arithm_term
	:	'('! expr ')'!
	|	constant
	|	column_name
	|	function
	;

constant
	:	INTEGER
	|	PRIMESTRING
	|	DECIMAL
	|	FLOAT
	;

ID	
	:	( LETTER | '_' | '#' ) ( LETTER | DIGIT | '_' | '#' | '@' | '$' )* { setText(getText().toUpperCase()); }
	|	'[' ( ~( '\n' | ']' ) )* ']' {setText(getText().substring(1, getText().length()-1).toUpperCase());}
	|	'"' ( ~( '\n' | '"' ) )* '"' {setText(getText().substring(1, getText().length()-1).toUpperCase());}
	;

fragment LETTER
	:	'a'..'z' 
	| 	'A'..'Z'
	;

	
INTEGER
	:	(PLUS | MINUS)? DIGIT+
	;
	
DECIMAL
	:	(PLUS | MINUS)? (DIGIT+ | '.' DIGIT+ | DIGIT+ '.' DIGIT*)
	;
	
FLOAT
	: DECIMAL 'e' INTEGER
	;

fragment DIGIT	
	:	'0'..'9'
	;

PRIMESTRING
    :	'\'' ( ~('\n' | '\'') | '\'' '\''!)* '\'' {setText(getText().substring(1, getText().length()-1));}
    ;

WHITESPACE
	:	('\t' | ' ' | '\r' | '\n'| '\u000C')+ { $channel = HIDDEN; }
	;

PLUS
	:	'+'
	;
	
MINUS
	:	'-'
	;
	
SLASH
	:	'/'
	;
	
MOD
	:	'%'
	;

STAR
	:	'*'		
	;
	
COMMA 
	:	','
	;

LT	
	:	'<'		
	;

LTE	
	:	'<='	
	;
	
GTE	
	:	'>='	
	;
	
GT	
	:	'>'		
	;

EQUAL
	:	'='
	;
	
UN_EQUAL
	:	'<>'
	;
	
