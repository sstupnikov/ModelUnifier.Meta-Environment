lexer grammar Sdf;
@header {
	package unifier.sdfparser;
}

DEFINITION : 'definition' ;
MODULE : 'module' ;
IMPORTS : 'imports' ;
EXPORTS : 'exports' ;
HIDDENS : 'hiddens' ;
CONTEXTFREE : 'context-free' ;
SYNTAX : 'syntax' ;
PRIORITIES : 'priorities' ;
STARTSYMBOLS : 'start-symbols' ;
SORTS : 'sorts' ;
MODULENAME : 'moduleName' ;
PRIORITY : 'priority' ;
SORT : 'sort' ;
GROUP : 'group' ;
COMPOUNDSYMBOL : 'compoundSymbol' ;
MARKEDSYMBOL : 'markedSymbol' ;
PARAMETERIZEDSYMBOL : 'parameterizedSymbol' ;
LIST : 'list' ;
ORIGIN : 'origin' ;
SEPARATOR : 'seperator' ;
MULTIPLICITY : 'multiplicity' ;
PRODUCTION : 'production' ;
LEFT : 'leftPart' ;
RIGHT : 'rightPart' ;
ATTRIBUTE : 'attribute' ;

// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 134
fragment DIGIT: '0'..'9';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 135
fragment LETTER: (SMALLLETTER | CAPITALLETTER);
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 136
fragment CAPITALLETTER: 'A'..'Z';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 137
fragment SMALLLETTER: 'a'..'z';	
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 138
fragment MINUS: '-';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 139
fragment UNDERSCORE: '_';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 140
PLUS: '+';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 141
STAR: '*';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 142
LBRACKET: '[';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 143
RBRACKET: ']';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 144
LDOUBLEBRACKET:	'[[';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 145
RDOUBLEBRACKET:	']]';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 146
LPAREN:	'(';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 147
RPAREN:	')';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 148
COMMA:	',';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 149
SLASH: '/';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 150
BOLD_IMP: '=>';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 151
IMP: '->';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 152
LBRACE: '{';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 153
RBRACE: '}';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 154
GREATER: '>';
// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 155
QUESTION: '?';


// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 158
ID
	:	(LETTER | DIGIT | UNDERSCORE | MINUS)+
	;



// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 164
STRING
	:	'\"' ( ~('\n' | '\"') | '\"' '\"'!)* '\"' {setText(getText().substring(1, getText().length()-1));}    
	;


// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 169
WHITESPACE
	:	('\t' | ' ' | '\r' | '\n'| '\u000C')+ { $channel = HIDDEN; }
	;

// $ANTLR src "D:\Documents\Serge\JavaProjects\Unifier\src\unifier\sdfparser\Sdf.g" 173
SL_COMMENT
	:	'%%' .* ('\r' | '\n'| '\u000C') { $channel = HIDDEN; }
	;
