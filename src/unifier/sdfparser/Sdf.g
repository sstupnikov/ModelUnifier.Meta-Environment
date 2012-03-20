// GRAMMAR NOTES
// 1. do not use '!' together with rewrite rules '->' (java.lang.IllegalArgumentException: Can't find template tokenRefBangTrack.st)
// 2. 'grammar' is a reserved word
// 3. Different lexical sorts are to not to be subsorts or to intersect


grammar Sdf;

options {
	output = AST;
	ASTLabelType = CommonTree;
}

tokens {
	DEFINITION = 'definition';
	MODULE = 'module';
	IMPORTS = 'imports';
	EXPORTS = 'exports';
	HIDDENS = 'hiddens';
	CONTEXTFREE = 'context-free';
	SYNTAX = 'syntax';
	PRIORITIES = 'priorities';
	STARTSYMBOLS = 'start-symbols';
	SORTS = 'sorts';
	
	MODULENAME = 'moduleName';
	PRIORITY = 'priority';	
	SORT = 'sort';
	GROUP = 'group';
	COMPOUNDSYMBOL = 'compoundSymbol'; MARKEDSYMBOL = 'markedSymbol'; PARAMETERIZEDSYMBOL = 'parameterizedSymbol';
	LIST = 'list'; ORIGIN = 'origin'; SEPARATOR = 'seperator'; MULTIPLICITY = 'multiplicity';
	PRODUCTION = 'production'; LEFT = 'leftPart'; RIGHT = 'rightPart';
	ATTRIBUTE = 'attribute';
}


@header {
	package unifier.sdfparser;
}

@lexer::header {
	package unifier.sdfparser;
}


module
	:	MODULE^ moduleName impSection* section* EOF
	;

moduleName
	:	mid=moduleId	-> ^(MODULENAME $mid)
	;
	
moduleId	
	:	moduleWord (SLASH! moduleWord)*
	;

moduleWord
	:	ID
	;
	
impSection
	:	IMPORTS^ imports*
	;

imports
	:	moduleName
	;
		
section
	:	(EXPORTS^ | HIDDENS^) sdfGrammar+
	;

sdfGrammar
	:	CONTEXTFREE! (SYNTAX^ production* | PRIORITIES^ priority* | STARTSYMBOLS^ symbol*)
	|	SORTS^ symbol*
	;
	
priority
	:	group (GREATER group)* -> ^(PRIORITY group+)
	;		

group
	:	production	-> ^(GROUP production)
	;
	
symbol
	:	markedSymbol
	|	list
	;

markedSymbol
	:	a=nonMarkedSymbol 
		( (s=STAR | s=PLUS | s=QUESTION) -> ^(MARKEDSYMBOL $a $s)	
		| /* EMPTY */ -> nonMarkedSymbol
		) 	
	;

nonMarkedSymbol
	:	parameterizedSort
	| 	STRING^	
	|   	LPAREN symbol* RPAREN -> ^(COMPOUNDSYMBOL symbol*)		
	;

parameterizedSort
	:	sort 
		( LDOUBLEBRACKET symbol (COMMA symbol)*  RDOUBLEBRACKET -> ^(PARAMETERIZEDSYMBOL sort symbol+)
		| /* EMPTY */ -> sort
		)
	;

sort
	:	id=ID	-> ^(SORT $id)
	;


list
	:	LBRACE o=symbol s=STRING RBRACE (m=PLUS | m=STAR) -> ^(LIST ^(ORIGIN $o) ^(SEPARATOR $s) ^(MULTIPLICITY $m))
	;


attribute
	:	id=ID	-> ^(ATTRIBUTE $id)
	;
	
production
	:	l+=symbol* IMP r=symbol (LBRACE a=attribute RBRACE)?	-> ^(PRODUCTION ^(LEFT $l*) ^(RIGHT $r))
	;	



// LEXICAL PART

fragment DIGIT: '0'..'9';
fragment LETTER: (SMALLLETTER | CAPITALLETTER);
fragment CAPITALLETTER: 'A'..'Z';
fragment SMALLLETTER: 'a'..'z';	
fragment MINUS: '-';
fragment UNDERSCORE: '_';
PLUS: '+';
STAR: '*';
LBRACKET: '[';
RBRACKET: ']';
LDOUBLEBRACKET:	'[[';
RDOUBLEBRACKET:	']]';
LPAREN:	'(';
RPAREN:	')';
COMMA:	',';
SLASH: '/';
BOLD_IMP: '=>';
IMP: '->';
LBRACE: '{';
RBRACE: '}';
GREATER: '>';
QUESTION: '?';


ID
	:	(LETTER | DIGIT | UNDERSCORE | MINUS)+
	;



STRING
	:	'\"' ( ~('\n' | '\"') | '\"' '\"'!)* '\"' {setText(getText().substring(1, getText().length()-1));}    
	;


WHITESPACE
	:	('\t' | ' ' | '\r' | '\n'| '\u000C')+ { $channel = HIDDEN; }
	;

SL_COMMENT
	:	'%%' .* ('\r' | '\n'| '\u000C') { $channel = HIDDEN; }
	;
