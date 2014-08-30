class FractIntParser extends Parser;
options {
	buildAST = true;	// uses CommonAST by default
}
imaginaryTokenDefinitions // Taken From TinyBasic.g
	:	
		IF_THEN_BLOCK	IF_BLOCK	ELSE_IF_BLOCK	ELSE_BLOCK
		CODE_BLOCK	CONDITION
	;

// Start Rule, Each file
compilationUnit[Context context]
	{
		theContext=context; //new Context();
	}
	:
		// Next we have a series of one or more formula blocks
		(	formula
		)*

		EOF!
	;

formula
	:	init loop bailout
	;

init
	:	INIT^ expr ( expr )* 
	;
loop
	:	LOOP^ expr ( expr )*
	;
bailout
	:	BAILOUT^ expr ( expr )*
	;
expr    :

comment :    WORD*

class CalcLexer extends Lexer;

WS	:	(' '
	|	'\t'
	|	'\n'
	|	'\r')
		{ _ttype = Token.SKIP; }
	;

LPAREN:	'('
	;

RPAREN:	')'
	;

STAR:	'*'
	;

PLUS:	'+'
	;

INIT:	'init:' 
        ;
LOOP:	'loop:' 
        ;
BAILOUT:'bailout:' 
        ;
SEMI:	';'
	;

protected
DIGIT
	:	'0'..'9'
	;

INT	:	(DIGIT)+
	;
// Words, which include our operators
WORD: ('a'..'z' | 'A'..'Z' | '.')+ ;

NEWLINE
    :   '\r' '\n'   // DOS
    |   '\n'        // UNIX
    ;