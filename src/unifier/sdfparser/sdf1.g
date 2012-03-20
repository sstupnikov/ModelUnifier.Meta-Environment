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
}


@header {
	package unifier.sdfparser;
}

@lexer::header {
	package unifier.sdfparser;
}

sdf
	:	DEFINITION definition EOF!
	;

definition
	options {backtrack=true;}
	:	(module)*
	;

module
	:	MODULE moduleName impSection* section*
	;

moduleName
	:	moduleId
	;
	
moduleId	
	:	moduleWord (SLASH! moduleWord)*
	;

moduleWord
	:	ID
	;
	
impSection
	:	IMPORTS import*
	;

import
	:	moduleName
	;
		
section
	:	(EXPORTS | HIDDENS) sdfGrammar+
	;

sdfGrammar
	:	CONTEXTFREE (SYNTAX production* | PRIORITIES priority* | STARTSYMBOLS symbol*)
	|	SORTS symbol*
	;
	
priority
	:	group (GREATER! group)*
	;		

group
	:	production
	;
	
symbol
	:	
	(	sort (LDOUBLEBRACKET! symbol (COMMA! symbol)*  RDOUBLEBRACKET!)?
	| 	STRING	
	|	list
	|   	LPAREN! symbol* RPAREN!
	)
	(STAR | PLUS | QUESTION)?
	;

sort
	:	ID
	;


list
	:	LBRACE! symbol symbol RBRACE! (PLUS | STAR)
	;


attribute
	:	ID
	;
	
production
	:	symbol* IMP! symbol (LBRACE! attribute RBRACE!)?
	;	
	

// LEXICAL PART

fragment DIGIT: '0'..'9';
fragment LETTER: (SMALLLETTER | CAPITALLETTER);
fragment CAPITALLETTER: 'A'..'Z';
fragment SMALLLETTER: 'a'..'z';	
fragment MINUS: '-';
PLUS: '+';
STAR: '*';
LBRACKET: '[';
RBRACKET: ']';
LDOUBLEBRACKET:	'[[';
RDOUBLEBRACKET:	']]';
LPAREN:	'(';
RPAREN:	')';
COMMA:	',';
fragment UNDERSCORE: '_';
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
	:	'%%' .* '\n' { $channel = HIDDEN; }
	;
