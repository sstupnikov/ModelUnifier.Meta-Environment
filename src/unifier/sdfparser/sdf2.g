grammar sdf;

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
	
	MODULENAME;
	PRIORITY;	
	SORT;
	GROUP;
	
	LIST; ORIGIN; SEPARATOR; MULTIPLICITY;
	PRODUCTION; LEFT; RIGHT;
	ATTRIBUTE;
}


@header {
	package unifier.sdfparser;
}

@lexer::header {
	package unifier.sdfparser;
}

sdf
	:	DEFINITION^ definition EOF!
	;

definition
	:	(module)*
	;

module
	:	MODULE^ moduleName impSection* section*
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
	:	IMPORTS^ import*
	;

import
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
	:	group (GREATER! group)* -> ^(PRIORITY group+)
	;		

group
	:	production	-> ^(GROUP production)
	;
	
symbol
	:	
	(	sort (LDOUBLEBRACKET! symbol (COMMA! symbol)*  RDOUBLEBRACKET!)?
	| 	STRING	
	|	list
	|   	LPAREN! symbol* RPAREN! 
	)
	s=(STAR | PLUS | QUESTION)?
	;

sort
	:	id=ID	-> ^(SORT $id)
	;


list
	:	LBRACE! o=symbol s=symbol RBRACE! m=(PLUS | STAR) -> ^(LIST ^(ORIGIN $o) ^(SEPARATOR $s) ^(MULTIPLICITY $m))
	;


attribute
	:	id=ID	-> ^(ATTRIBUTE $id)
	;
	
production
	:	l=symbol* IMP! r=symbol (LBRACE! a=attribute RBRACE!)?	-> ^(PRODUCTION ^(LEFT $l) ^(RIGHT $r) $a)
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
