grammar sdf;

options {
	output = AST;
	ASTLabelType = CommonTree;
}


@header {
	package unifier.sdfparser;
}

@lexer::header {
	package unifier.sdfparser;
}

SDF
	:	'definition' Definition
	;

Definition
	:	(Module)*
	;

Module
	:	'module' ModuleName ImpSection* Sections
	;

ModuleName
	:	ModuleId (LBRACKET! Symbols RBRACKET!)?
	;
	
ModuleWord
	:	(LETTER | DIGIT | UNDERSCORE | MINUS)+
	;

ModuleId	
	:	ModuleWord (SLASH! ModuleWord)*
	|	(SLASH! ModuleWord)+
	;
	
ImpSection
	:	'imports' Imports
	;

Imports
	:	Import
	;

Import
	:	ModuleName Renamings?
	;

Renamings
	:	LBRACKET! Renaming*  RBRACKET!
	{}
	;

Renaming
	:	Symbol IMP! Symbol
	|	Production IMP! Production
	;
	
Sections
	:	Section*
	;
	
Section
	:	'exports' Grammar
	|	'hiddens' Grammar
	;

Grammar
	:	'lexical' 'priorities' Priorities
	|	'context-free' 'priorities' Priorities
	|	'lexical' 'restrictions' Restrictions
	|	'context-free' 'restrictions' Restrictions
	|	'start-symbols' Symbols
	|	'lexical' 'start-symbols' Symbols
	|	'context-free' 'start-symbols' Symbols
	|	'sorts' Symbols
	;
	
Priorities
	:
	;		

Restrictions
	:
	;


Symbols
	:	Symbol*
	;

Symbol
	:	Sort (LDOUBLEBRACKET! Sort (COMMA! Sort)*  RDOUBLEBRACKET!)?
	;

Sort
	:	CAPITALLETTER
	|	CAPITALLETTER (LETTER | DIGIT | MINUS)* (LETTER | DIGIT)
	;

Attribute
	:	'id' LPAREN! ModuleName  RPAREN! 
	;

	
Production
	:
	;	

// LEXICAL PART

fragment DIGIT: '0'..'9';
fragment LETTER: SMALLLETTER | CAPITALLETTER;
fragment CAPITALLETTER: 'A'..'Z';
fragment SMALLLETTER: 'a'..'z';	
MINUS: '-';
LBRACKET: '[';
RBRACKET: ']';
LDOUBLEBRACKET:	'[[';
RDOUBLEBRACKET:	']]';
LPAREN:	'(';
RPAREN:	')';
COMMA:	',';
UNDERSCORE: '_';
SLASH: '/';
IMP: '=>';


WHITESPACE
	:	('\t' | ' ' | '\r' | '\n'| '\u000C')+ { $channel = HIDDEN; }
	;
