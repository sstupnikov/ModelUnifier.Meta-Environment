// $ANTLR 3.0.1 D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g 2008-05-14 19:22:04

	package unifier.sdfparser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SdfLexer extends Lexer {
    public static final int STAR=32;
    public static final int LETTER=47;
    public static final int LBRACE=41;
    public static final int ATTRIBUTE=28;
    public static final int CONTEXTFREE=9;
    public static final int SEPARATOR=23;
    public static final int ID=30;
    public static final int MARKEDSYMBOL=19;
    public static final int EOF=-1;
    public static final int LPAREN=36;
    public static final int SORTS=13;
    public static final int LBRACKET=50;
    public static final int MULTIPLICITY=24;
    public static final int SYNTAX=10;
    public static final int RPAREN=37;
    public static final int GREATER=31;
    public static final int SLASH=29;
    public static final int EXPORTS=7;
    public static final int COMMA=39;
    public static final int LEFT=26;
    public static final int ORIGIN=22;
    public static final int PRODUCTION=25;
    public static final int DEFINITION=4;
    public static final int IMP=43;
    public static final int PLUS=33;
    public static final int DIGIT=44;
    public static final int RBRACKET=51;
    public static final int COMPOUNDSYMBOL=18;
    public static final int STARTSYMBOLS=12;
    public static final int SMALLLETTER=45;
    public static final int RBRACE=42;
    public static final int CAPITALLETTER=46;
    public static final int WHITESPACE=53;
    public static final int SORT=16;
    public static final int UNDERSCORE=49;
    public static final int RIGHT=27;
    public static final int MINUS=48;
    public static final int MODULE=5;
    public static final int Tokens=55;
    public static final int LIST=21;
    public static final int MODULENAME=14;
    public static final int PARAMETERIZEDSYMBOL=20;
    public static final int GROUP=17;
    public static final int QUESTION=34;
    public static final int PRIORITY=15;
    public static final int BOLD_IMP=52;
    public static final int SL_COMMENT=54;
    public static final int LDOUBLEBRACKET=38;
    public static final int RDOUBLEBRACKET=40;
    public static final int HIDDENS=8;
    public static final int IMPORTS=6;
    public static final int PRIORITIES=11;
    public static final int STRING=35;
    public SdfLexer() {;} 
    public SdfLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g"; }

    // $ANTLR start DEFINITION
    public final void mDEFINITION() throws RecognitionException {
        try {
            int _type = DEFINITION;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:6:12: ( 'definition' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:6:14: 'definition'
            {
            match("definition"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DEFINITION

    // $ANTLR start MODULE
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:7:8: ( 'module' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:7:10: 'module'
            {
            match("module"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MODULE

    // $ANTLR start IMPORTS
    public final void mIMPORTS() throws RecognitionException {
        try {
            int _type = IMPORTS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:8:9: ( 'imports' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:8:11: 'imports'
            {
            match("imports"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPORTS

    // $ANTLR start EXPORTS
    public final void mEXPORTS() throws RecognitionException {
        try {
            int _type = EXPORTS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:9:9: ( 'exports' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:9:11: 'exports'
            {
            match("exports"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EXPORTS

    // $ANTLR start HIDDENS
    public final void mHIDDENS() throws RecognitionException {
        try {
            int _type = HIDDENS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:10:9: ( 'hiddens' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:10:11: 'hiddens'
            {
            match("hiddens"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end HIDDENS

    // $ANTLR start CONTEXTFREE
    public final void mCONTEXTFREE() throws RecognitionException {
        try {
            int _type = CONTEXTFREE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:11:13: ( 'context-free' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:11:15: 'context-free'
            {
            match("context-free"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONTEXTFREE

    // $ANTLR start SYNTAX
    public final void mSYNTAX() throws RecognitionException {
        try {
            int _type = SYNTAX;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:12:8: ( 'syntax' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:12:10: 'syntax'
            {
            match("syntax"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYNTAX

    // $ANTLR start PRIORITIES
    public final void mPRIORITIES() throws RecognitionException {
        try {
            int _type = PRIORITIES;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:13:12: ( 'priorities' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:13:14: 'priorities'
            {
            match("priorities"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRIORITIES

    // $ANTLR start STARTSYMBOLS
    public final void mSTARTSYMBOLS() throws RecognitionException {
        try {
            int _type = STARTSYMBOLS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:14:14: ( 'start-symbols' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:14:16: 'start-symbols'
            {
            match("start-symbols"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STARTSYMBOLS

    // $ANTLR start SORTS
    public final void mSORTS() throws RecognitionException {
        try {
            int _type = SORTS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:15:7: ( 'sorts' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:15:9: 'sorts'
            {
            match("sorts"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SORTS

    // $ANTLR start MODULENAME
    public final void mMODULENAME() throws RecognitionException {
        try {
            int _type = MODULENAME;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:16:12: ( 'moduleName' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:16:14: 'moduleName'
            {
            match("moduleName"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MODULENAME

    // $ANTLR start PRIORITY
    public final void mPRIORITY() throws RecognitionException {
        try {
            int _type = PRIORITY;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:17:10: ( 'priority' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:17:12: 'priority'
            {
            match("priority"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRIORITY

    // $ANTLR start SORT
    public final void mSORT() throws RecognitionException {
        try {
            int _type = SORT;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:18:6: ( 'sort' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:18:8: 'sort'
            {
            match("sort"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SORT

    // $ANTLR start GROUP
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:19:7: ( 'group' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:19:9: 'group'
            {
            match("group"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GROUP

    // $ANTLR start COMPOUNDSYMBOL
    public final void mCOMPOUNDSYMBOL() throws RecognitionException {
        try {
            int _type = COMPOUNDSYMBOL;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:20:16: ( 'compoundSymbol' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:20:18: 'compoundSymbol'
            {
            match("compoundSymbol"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMPOUNDSYMBOL

    // $ANTLR start MARKEDSYMBOL
    public final void mMARKEDSYMBOL() throws RecognitionException {
        try {
            int _type = MARKEDSYMBOL;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:21:14: ( 'markedSymbol' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:21:16: 'markedSymbol'
            {
            match("markedSymbol"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MARKEDSYMBOL

    // $ANTLR start PARAMETERIZEDSYMBOL
    public final void mPARAMETERIZEDSYMBOL() throws RecognitionException {
        try {
            int _type = PARAMETERIZEDSYMBOL;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:22:21: ( 'parameterizedSymbol' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:22:23: 'parameterizedSymbol'
            {
            match("parameterizedSymbol"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PARAMETERIZEDSYMBOL

    // $ANTLR start LIST
    public final void mLIST() throws RecognitionException {
        try {
            int _type = LIST;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:23:6: ( 'list' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:23:8: 'list'
            {
            match("list"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LIST

    // $ANTLR start ORIGIN
    public final void mORIGIN() throws RecognitionException {
        try {
            int _type = ORIGIN;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:24:8: ( 'origin' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:24:10: 'origin'
            {
            match("origin"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ORIGIN

    // $ANTLR start SEPARATOR
    public final void mSEPARATOR() throws RecognitionException {
        try {
            int _type = SEPARATOR;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:25:11: ( 'seperator' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:25:13: 'seperator'
            {
            match("seperator"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEPARATOR

    // $ANTLR start MULTIPLICITY
    public final void mMULTIPLICITY() throws RecognitionException {
        try {
            int _type = MULTIPLICITY;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:26:14: ( 'multiplicity' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:26:16: 'multiplicity'
            {
            match("multiplicity"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MULTIPLICITY

    // $ANTLR start PRODUCTION
    public final void mPRODUCTION() throws RecognitionException {
        try {
            int _type = PRODUCTION;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:27:12: ( 'production' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:27:14: 'production'
            {
            match("production"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRODUCTION

    // $ANTLR start LEFT
    public final void mLEFT() throws RecognitionException {
        try {
            int _type = LEFT;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:28:6: ( 'leftPart' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:28:8: 'leftPart'
            {
            match("leftPart"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFT

    // $ANTLR start RIGHT
    public final void mRIGHT() throws RecognitionException {
        try {
            int _type = RIGHT;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:29:7: ( 'rightPart' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:29:9: 'rightPart'
            {
            match("rightPart"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHT

    // $ANTLR start ATTRIBUTE
    public final void mATTRIBUTE() throws RecognitionException {
        try {
            int _type = ATTRIBUTE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:30:11: ( 'attribute' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:30:13: 'attribute'
            {
            match("attribute"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ATTRIBUTE

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:134:15: ( '0' .. '9' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:134:17: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start LETTER
    public final void mLETTER() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:135:16: ( ( SMALLLETTER | CAPITALLETTER ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:135:18: ( SMALLLETTER | CAPITALLETTER )
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end LETTER

    // $ANTLR start CAPITALLETTER
    public final void mCAPITALLETTER() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:136:23: ( 'A' .. 'Z' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:136:25: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end CAPITALLETTER

    // $ANTLR start SMALLLETTER
    public final void mSMALLLETTER() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:137:21: ( 'a' .. 'z' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:137:23: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end SMALLLETTER

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:138:15: ( '-' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:138:17: '-'
            {
            match('-'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start UNDERSCORE
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:139:20: ( '_' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:139:22: '_'
            {
            match('_'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end UNDERSCORE

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:140:5: ( '+' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:140:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:141:5: ( '*' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:141:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start LBRACKET
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:142:9: ( '[' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:142:11: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACKET

    // $ANTLR start RBRACKET
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:143:9: ( ']' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:143:11: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACKET

    // $ANTLR start LDOUBLEBRACKET
    public final void mLDOUBLEBRACKET() throws RecognitionException {
        try {
            int _type = LDOUBLEBRACKET;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:144:15: ( '[[' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:144:17: '[['
            {
            match("[["); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LDOUBLEBRACKET

    // $ANTLR start RDOUBLEBRACKET
    public final void mRDOUBLEBRACKET() throws RecognitionException {
        try {
            int _type = RDOUBLEBRACKET;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:145:15: ( ']]' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:145:17: ']]'
            {
            match("]]"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RDOUBLEBRACKET

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:146:7: ( '(' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:146:9: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:147:7: ( ')' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:147:9: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:148:6: ( ',' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:148:8: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SLASH
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:149:6: ( '/' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:149:8: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLASH

    // $ANTLR start BOLD_IMP
    public final void mBOLD_IMP() throws RecognitionException {
        try {
            int _type = BOLD_IMP;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:150:9: ( '=>' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:150:11: '=>'
            {
            match("=>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOLD_IMP

    // $ANTLR start IMP
    public final void mIMP() throws RecognitionException {
        try {
            int _type = IMP;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:151:4: ( '->' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:151:6: '->'
            {
            match("->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMP

    // $ANTLR start LBRACE
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:152:7: ( '{' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:152:9: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACE

    // $ANTLR start RBRACE
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:153:7: ( '}' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:153:9: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACE

    // $ANTLR start GREATER
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:154:8: ( '>' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:154:10: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATER

    // $ANTLR start QUESTION
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:155:9: ( '?' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:155:11: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUESTION

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:159:2: ( ( LETTER | DIGIT | UNDERSCORE | MINUS )+ )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:159:4: ( LETTER | DIGIT | UNDERSCORE | MINUS )+
            {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:159:4: ( LETTER | DIGIT | UNDERSCORE | MINUS )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start STRING
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:165:2: ( '\\\"' (~ ( '\\n' | '\\\"' ) | '\\\"' '\\\"' )* '\\\"' )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:165:4: '\\\"' (~ ( '\\n' | '\\\"' ) | '\\\"' '\\\"' )* '\\\"'
            {
            match('\"'); 
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:165:9: (~ ( '\\n' | '\\\"' ) | '\\\"' '\\\"' )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\"') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='\"') ) {
                        alt2=2;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='\uFFFE')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:165:11: ~ ( '\\n' | '\\\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;
            	case 2 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:165:28: '\\\"' '\\\"'
            	    {
            	    match('\"'); 
            	    match('\"'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('\"'); 
            setText(getText().substring(1, getText().length()-1));

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING

    // $ANTLR start WHITESPACE
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:170:2: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:170:4: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:170:4: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHITESPACE

    // $ANTLR start SL_COMMENT
    public final void mSL_COMMENT() throws RecognitionException {
        try {
            int _type = SL_COMMENT;
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:174:2: ( '%%' ( . )* ( '\\r' | '\\n' | '\\u000C' ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:174:4: '%%' ( . )* ( '\\r' | '\\n' | '\\u000C' )
            {
            match("%%"); 

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:174:9: ( . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\n'||(LA4_0>='\f' && LA4_0<='\r')) ) {
                    alt4=2;
                }
                else if ( ((LA4_0>='\u0000' && LA4_0<='\t')||LA4_0=='\u000B'||(LA4_0>='\u000E' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:174:9: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            if ( input.LA(1)=='\n'||(input.LA(1)>='\f' && input.LA(1)<='\r') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SL_COMMENT

    public void mTokens() throws RecognitionException {
        // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:8: ( DEFINITION | MODULE | IMPORTS | EXPORTS | HIDDENS | CONTEXTFREE | SYNTAX | PRIORITIES | STARTSYMBOLS | SORTS | MODULENAME | PRIORITY | SORT | GROUP | COMPOUNDSYMBOL | MARKEDSYMBOL | PARAMETERIZEDSYMBOL | LIST | ORIGIN | SEPARATOR | MULTIPLICITY | PRODUCTION | LEFT | RIGHT | ATTRIBUTE | PLUS | STAR | LBRACKET | RBRACKET | LDOUBLEBRACKET | RDOUBLEBRACKET | LPAREN | RPAREN | COMMA | SLASH | BOLD_IMP | IMP | LBRACE | RBRACE | GREATER | QUESTION | ID | STRING | WHITESPACE | SL_COMMENT )
        int alt5=45;
        switch ( input.LA(1) ) {
        case 'd':
            {
            int LA5_1 = input.LA(2);

            if ( (LA5_1=='e') ) {
                int LA5_32 = input.LA(3);

                if ( (LA5_32=='f') ) {
                    int LA5_57 = input.LA(4);

                    if ( (LA5_57=='i') ) {
                        int LA5_79 = input.LA(5);

                        if ( (LA5_79=='n') ) {
                            int LA5_101 = input.LA(6);

                            if ( (LA5_101=='i') ) {
                                int LA5_124 = input.LA(7);

                                if ( (LA5_124=='t') ) {
                                    int LA5_145 = input.LA(8);

                                    if ( (LA5_145=='i') ) {
                                        int LA5_165 = input.LA(9);

                                        if ( (LA5_165=='o') ) {
                                            int LA5_183 = input.LA(10);

                                            if ( (LA5_183=='n') ) {
                                                int LA5_198 = input.LA(11);

                                                if ( (LA5_198=='-'||(LA5_198>='0' && LA5_198<='9')||(LA5_198>='A' && LA5_198<='Z')||LA5_198=='_'||(LA5_198>='a' && LA5_198<='z')) ) {
                                                    alt5=42;
                                                }
                                                else {
                                                    alt5=1;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'm':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA5_33 = input.LA(3);

                if ( (LA5_33=='r') ) {
                    int LA5_58 = input.LA(4);

                    if ( (LA5_58=='k') ) {
                        int LA5_80 = input.LA(5);

                        if ( (LA5_80=='e') ) {
                            int LA5_102 = input.LA(6);

                            if ( (LA5_102=='d') ) {
                                int LA5_125 = input.LA(7);

                                if ( (LA5_125=='S') ) {
                                    int LA5_146 = input.LA(8);

                                    if ( (LA5_146=='y') ) {
                                        int LA5_166 = input.LA(9);

                                        if ( (LA5_166=='m') ) {
                                            int LA5_184 = input.LA(10);

                                            if ( (LA5_184=='b') ) {
                                                int LA5_199 = input.LA(11);

                                                if ( (LA5_199=='o') ) {
                                                    int LA5_212 = input.LA(12);

                                                    if ( (LA5_212=='l') ) {
                                                        int LA5_221 = input.LA(13);

                                                        if ( (LA5_221=='-'||(LA5_221>='0' && LA5_221<='9')||(LA5_221>='A' && LA5_221<='Z')||LA5_221=='_'||(LA5_221>='a' && LA5_221<='z')) ) {
                                                            alt5=42;
                                                        }
                                                        else {
                                                            alt5=16;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'o':
                {
                int LA5_34 = input.LA(3);

                if ( (LA5_34=='d') ) {
                    int LA5_59 = input.LA(4);

                    if ( (LA5_59=='u') ) {
                        int LA5_81 = input.LA(5);

                        if ( (LA5_81=='l') ) {
                            int LA5_103 = input.LA(6);

                            if ( (LA5_103=='e') ) {
                                switch ( input.LA(7) ) {
                                case 'N':
                                    {
                                    int LA5_147 = input.LA(8);

                                    if ( (LA5_147=='a') ) {
                                        int LA5_167 = input.LA(9);

                                        if ( (LA5_167=='m') ) {
                                            int LA5_185 = input.LA(10);

                                            if ( (LA5_185=='e') ) {
                                                int LA5_200 = input.LA(11);

                                                if ( (LA5_200=='-'||(LA5_200>='0' && LA5_200<='9')||(LA5_200>='A' && LA5_200<='Z')||LA5_200=='_'||(LA5_200>='a' && LA5_200<='z')) ) {
                                                    alt5=42;
                                                }
                                                else {
                                                    alt5=11;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                    }
                                    break;
                                case '-':
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                case 'A':
                                case 'B':
                                case 'C':
                                case 'D':
                                case 'E':
                                case 'F':
                                case 'G':
                                case 'H':
                                case 'I':
                                case 'J':
                                case 'K':
                                case 'L':
                                case 'M':
                                case 'O':
                                case 'P':
                                case 'Q':
                                case 'R':
                                case 'S':
                                case 'T':
                                case 'U':
                                case 'V':
                                case 'W':
                                case 'X':
                                case 'Y':
                                case 'Z':
                                case '_':
                                case 'a':
                                case 'b':
                                case 'c':
                                case 'd':
                                case 'e':
                                case 'f':
                                case 'g':
                                case 'h':
                                case 'i':
                                case 'j':
                                case 'k':
                                case 'l':
                                case 'm':
                                case 'n':
                                case 'o':
                                case 'p':
                                case 'q':
                                case 'r':
                                case 's':
                                case 't':
                                case 'u':
                                case 'v':
                                case 'w':
                                case 'x':
                                case 'y':
                                case 'z':
                                    {
                                    alt5=42;
                                    }
                                    break;
                                default:
                                    alt5=2;}

                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'u':
                {
                int LA5_35 = input.LA(3);

                if ( (LA5_35=='l') ) {
                    int LA5_60 = input.LA(4);

                    if ( (LA5_60=='t') ) {
                        int LA5_82 = input.LA(5);

                        if ( (LA5_82=='i') ) {
                            int LA5_104 = input.LA(6);

                            if ( (LA5_104=='p') ) {
                                int LA5_127 = input.LA(7);

                                if ( (LA5_127=='l') ) {
                                    int LA5_149 = input.LA(8);

                                    if ( (LA5_149=='i') ) {
                                        int LA5_168 = input.LA(9);

                                        if ( (LA5_168=='c') ) {
                                            int LA5_186 = input.LA(10);

                                            if ( (LA5_186=='i') ) {
                                                int LA5_201 = input.LA(11);

                                                if ( (LA5_201=='t') ) {
                                                    int LA5_214 = input.LA(12);

                                                    if ( (LA5_214=='y') ) {
                                                        int LA5_222 = input.LA(13);

                                                        if ( (LA5_222=='-'||(LA5_222>='0' && LA5_222<='9')||(LA5_222>='A' && LA5_222<='Z')||LA5_222=='_'||(LA5_222>='a' && LA5_222<='z')) ) {
                                                            alt5=42;
                                                        }
                                                        else {
                                                            alt5=21;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            default:
                alt5=42;}

            }
            break;
        case 'i':
            {
            int LA5_3 = input.LA(2);

            if ( (LA5_3=='m') ) {
                int LA5_36 = input.LA(3);

                if ( (LA5_36=='p') ) {
                    int LA5_61 = input.LA(4);

                    if ( (LA5_61=='o') ) {
                        int LA5_83 = input.LA(5);

                        if ( (LA5_83=='r') ) {
                            int LA5_105 = input.LA(6);

                            if ( (LA5_105=='t') ) {
                                int LA5_128 = input.LA(7);

                                if ( (LA5_128=='s') ) {
                                    int LA5_150 = input.LA(8);

                                    if ( (LA5_150=='-'||(LA5_150>='0' && LA5_150<='9')||(LA5_150>='A' && LA5_150<='Z')||LA5_150=='_'||(LA5_150>='a' && LA5_150<='z')) ) {
                                        alt5=42;
                                    }
                                    else {
                                        alt5=3;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'e':
            {
            int LA5_4 = input.LA(2);

            if ( (LA5_4=='x') ) {
                int LA5_37 = input.LA(3);

                if ( (LA5_37=='p') ) {
                    int LA5_62 = input.LA(4);

                    if ( (LA5_62=='o') ) {
                        int LA5_84 = input.LA(5);

                        if ( (LA5_84=='r') ) {
                            int LA5_106 = input.LA(6);

                            if ( (LA5_106=='t') ) {
                                int LA5_129 = input.LA(7);

                                if ( (LA5_129=='s') ) {
                                    int LA5_151 = input.LA(8);

                                    if ( (LA5_151=='-'||(LA5_151>='0' && LA5_151<='9')||(LA5_151>='A' && LA5_151<='Z')||LA5_151=='_'||(LA5_151>='a' && LA5_151<='z')) ) {
                                        alt5=42;
                                    }
                                    else {
                                        alt5=4;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'h':
            {
            int LA5_5 = input.LA(2);

            if ( (LA5_5=='i') ) {
                int LA5_38 = input.LA(3);

                if ( (LA5_38=='d') ) {
                    int LA5_63 = input.LA(4);

                    if ( (LA5_63=='d') ) {
                        int LA5_85 = input.LA(5);

                        if ( (LA5_85=='e') ) {
                            int LA5_107 = input.LA(6);

                            if ( (LA5_107=='n') ) {
                                int LA5_130 = input.LA(7);

                                if ( (LA5_130=='s') ) {
                                    int LA5_152 = input.LA(8);

                                    if ( (LA5_152=='-'||(LA5_152>='0' && LA5_152<='9')||(LA5_152>='A' && LA5_152<='Z')||LA5_152=='_'||(LA5_152>='a' && LA5_152<='z')) ) {
                                        alt5=42;
                                    }
                                    else {
                                        alt5=5;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'c':
            {
            int LA5_6 = input.LA(2);

            if ( (LA5_6=='o') ) {
                switch ( input.LA(3) ) {
                case 'n':
                    {
                    int LA5_64 = input.LA(4);

                    if ( (LA5_64=='t') ) {
                        int LA5_86 = input.LA(5);

                        if ( (LA5_86=='e') ) {
                            int LA5_108 = input.LA(6);

                            if ( (LA5_108=='x') ) {
                                int LA5_131 = input.LA(7);

                                if ( (LA5_131=='t') ) {
                                    int LA5_153 = input.LA(8);

                                    if ( (LA5_153=='-') ) {
                                        int LA5_172 = input.LA(9);

                                        if ( (LA5_172=='f') ) {
                                            int LA5_187 = input.LA(10);

                                            if ( (LA5_187=='r') ) {
                                                int LA5_202 = input.LA(11);

                                                if ( (LA5_202=='e') ) {
                                                    int LA5_215 = input.LA(12);

                                                    if ( (LA5_215=='e') ) {
                                                        int LA5_223 = input.LA(13);

                                                        if ( (LA5_223=='-'||(LA5_223>='0' && LA5_223<='9')||(LA5_223>='A' && LA5_223<='Z')||LA5_223=='_'||(LA5_223>='a' && LA5_223<='z')) ) {
                                                            alt5=42;
                                                        }
                                                        else {
                                                            alt5=6;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                    }
                    break;
                case 'm':
                    {
                    int LA5_65 = input.LA(4);

                    if ( (LA5_65=='p') ) {
                        int LA5_87 = input.LA(5);

                        if ( (LA5_87=='o') ) {
                            int LA5_109 = input.LA(6);

                            if ( (LA5_109=='u') ) {
                                int LA5_132 = input.LA(7);

                                if ( (LA5_132=='n') ) {
                                    int LA5_154 = input.LA(8);

                                    if ( (LA5_154=='d') ) {
                                        int LA5_173 = input.LA(9);

                                        if ( (LA5_173=='S') ) {
                                            int LA5_188 = input.LA(10);

                                            if ( (LA5_188=='y') ) {
                                                int LA5_203 = input.LA(11);

                                                if ( (LA5_203=='m') ) {
                                                    int LA5_216 = input.LA(12);

                                                    if ( (LA5_216=='b') ) {
                                                        int LA5_224 = input.LA(13);

                                                        if ( (LA5_224=='o') ) {
                                                            int LA5_230 = input.LA(14);

                                                            if ( (LA5_230=='l') ) {
                                                                int LA5_233 = input.LA(15);

                                                                if ( (LA5_233=='-'||(LA5_233>='0' && LA5_233<='9')||(LA5_233>='A' && LA5_233<='Z')||LA5_233=='_'||(LA5_233>='a' && LA5_233<='z')) ) {
                                                                    alt5=42;
                                                                }
                                                                else {
                                                                    alt5=15;}
                                                            }
                                                            else {
                                                                alt5=42;}
                                                        }
                                                        else {
                                                            alt5=42;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                    }
                    break;
                default:
                    alt5=42;}

            }
            else {
                alt5=42;}
            }
            break;
        case 's':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA5_40 = input.LA(3);

                if ( (LA5_40=='p') ) {
                    int LA5_66 = input.LA(4);

                    if ( (LA5_66=='e') ) {
                        int LA5_88 = input.LA(5);

                        if ( (LA5_88=='r') ) {
                            int LA5_110 = input.LA(6);

                            if ( (LA5_110=='a') ) {
                                int LA5_133 = input.LA(7);

                                if ( (LA5_133=='t') ) {
                                    int LA5_155 = input.LA(8);

                                    if ( (LA5_155=='o') ) {
                                        int LA5_174 = input.LA(9);

                                        if ( (LA5_174=='r') ) {
                                            int LA5_189 = input.LA(10);

                                            if ( (LA5_189=='-'||(LA5_189>='0' && LA5_189<='9')||(LA5_189>='A' && LA5_189<='Z')||LA5_189=='_'||(LA5_189>='a' && LA5_189<='z')) ) {
                                                alt5=42;
                                            }
                                            else {
                                                alt5=20;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'y':
                {
                int LA5_41 = input.LA(3);

                if ( (LA5_41=='n') ) {
                    int LA5_67 = input.LA(4);

                    if ( (LA5_67=='t') ) {
                        int LA5_89 = input.LA(5);

                        if ( (LA5_89=='a') ) {
                            int LA5_111 = input.LA(6);

                            if ( (LA5_111=='x') ) {
                                int LA5_134 = input.LA(7);

                                if ( (LA5_134=='-'||(LA5_134>='0' && LA5_134<='9')||(LA5_134>='A' && LA5_134<='Z')||LA5_134=='_'||(LA5_134>='a' && LA5_134<='z')) ) {
                                    alt5=42;
                                }
                                else {
                                    alt5=7;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 't':
                {
                int LA5_42 = input.LA(3);

                if ( (LA5_42=='a') ) {
                    int LA5_68 = input.LA(4);

                    if ( (LA5_68=='r') ) {
                        int LA5_90 = input.LA(5);

                        if ( (LA5_90=='t') ) {
                            int LA5_112 = input.LA(6);

                            if ( (LA5_112=='-') ) {
                                int LA5_135 = input.LA(7);

                                if ( (LA5_135=='s') ) {
                                    int LA5_157 = input.LA(8);

                                    if ( (LA5_157=='y') ) {
                                        int LA5_175 = input.LA(9);

                                        if ( (LA5_175=='m') ) {
                                            int LA5_190 = input.LA(10);

                                            if ( (LA5_190=='b') ) {
                                                int LA5_205 = input.LA(11);

                                                if ( (LA5_205=='o') ) {
                                                    int LA5_217 = input.LA(12);

                                                    if ( (LA5_217=='l') ) {
                                                        int LA5_225 = input.LA(13);

                                                        if ( (LA5_225=='s') ) {
                                                            int LA5_231 = input.LA(14);

                                                            if ( (LA5_231=='-'||(LA5_231>='0' && LA5_231<='9')||(LA5_231>='A' && LA5_231<='Z')||LA5_231=='_'||(LA5_231>='a' && LA5_231<='z')) ) {
                                                                alt5=42;
                                                            }
                                                            else {
                                                                alt5=9;}
                                                        }
                                                        else {
                                                            alt5=42;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'o':
                {
                int LA5_43 = input.LA(3);

                if ( (LA5_43=='r') ) {
                    int LA5_69 = input.LA(4);

                    if ( (LA5_69=='t') ) {
                        switch ( input.LA(5) ) {
                        case 's':
                            {
                            int LA5_113 = input.LA(6);

                            if ( (LA5_113=='-'||(LA5_113>='0' && LA5_113<='9')||(LA5_113>='A' && LA5_113<='Z')||LA5_113=='_'||(LA5_113>='a' && LA5_113<='z')) ) {
                                alt5=42;
                            }
                            else {
                                alt5=10;}
                            }
                            break;
                        case '-':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '_':
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                            {
                            alt5=42;
                            }
                            break;
                        default:
                            alt5=13;}

                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            default:
                alt5=42;}

            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA5_44 = input.LA(3);

                if ( (LA5_44=='r') ) {
                    int LA5_70 = input.LA(4);

                    if ( (LA5_70=='a') ) {
                        int LA5_92 = input.LA(5);

                        if ( (LA5_92=='m') ) {
                            int LA5_115 = input.LA(6);

                            if ( (LA5_115=='e') ) {
                                int LA5_137 = input.LA(7);

                                if ( (LA5_137=='t') ) {
                                    int LA5_158 = input.LA(8);

                                    if ( (LA5_158=='e') ) {
                                        int LA5_176 = input.LA(9);

                                        if ( (LA5_176=='r') ) {
                                            int LA5_191 = input.LA(10);

                                            if ( (LA5_191=='i') ) {
                                                int LA5_206 = input.LA(11);

                                                if ( (LA5_206=='z') ) {
                                                    int LA5_218 = input.LA(12);

                                                    if ( (LA5_218=='e') ) {
                                                        int LA5_226 = input.LA(13);

                                                        if ( (LA5_226=='d') ) {
                                                            int LA5_232 = input.LA(14);

                                                            if ( (LA5_232=='S') ) {
                                                                int LA5_235 = input.LA(15);

                                                                if ( (LA5_235=='y') ) {
                                                                    int LA5_237 = input.LA(16);

                                                                    if ( (LA5_237=='m') ) {
                                                                        int LA5_238 = input.LA(17);

                                                                        if ( (LA5_238=='b') ) {
                                                                            int LA5_239 = input.LA(18);

                                                                            if ( (LA5_239=='o') ) {
                                                                                int LA5_240 = input.LA(19);

                                                                                if ( (LA5_240=='l') ) {
                                                                                    int LA5_241 = input.LA(20);

                                                                                    if ( (LA5_241=='-'||(LA5_241>='0' && LA5_241<='9')||(LA5_241>='A' && LA5_241<='Z')||LA5_241=='_'||(LA5_241>='a' && LA5_241<='z')) ) {
                                                                                        alt5=42;
                                                                                    }
                                                                                    else {
                                                                                        alt5=17;}
                                                                                }
                                                                                else {
                                                                                    alt5=42;}
                                                                            }
                                                                            else {
                                                                                alt5=42;}
                                                                        }
                                                                        else {
                                                                            alt5=42;}
                                                                    }
                                                                    else {
                                                                        alt5=42;}
                                                                }
                                                                else {
                                                                    alt5=42;}
                                                            }
                                                            else {
                                                                alt5=42;}
                                                        }
                                                        else {
                                                            alt5=42;}
                                                    }
                                                    else {
                                                        alt5=42;}
                                                }
                                                else {
                                                    alt5=42;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA5_71 = input.LA(4);

                    if ( (LA5_71=='o') ) {
                        int LA5_93 = input.LA(5);

                        if ( (LA5_93=='r') ) {
                            int LA5_116 = input.LA(6);

                            if ( (LA5_116=='i') ) {
                                int LA5_138 = input.LA(7);

                                if ( (LA5_138=='t') ) {
                                    switch ( input.LA(8) ) {
                                    case 'y':
                                        {
                                        int LA5_177 = input.LA(9);

                                        if ( (LA5_177=='-'||(LA5_177>='0' && LA5_177<='9')||(LA5_177>='A' && LA5_177<='Z')||LA5_177=='_'||(LA5_177>='a' && LA5_177<='z')) ) {
                                            alt5=42;
                                        }
                                        else {
                                            alt5=12;}
                                        }
                                        break;
                                    case 'i':
                                        {
                                        int LA5_178 = input.LA(9);

                                        if ( (LA5_178=='e') ) {
                                            int LA5_193 = input.LA(10);

                                            if ( (LA5_193=='s') ) {
                                                int LA5_207 = input.LA(11);

                                                if ( (LA5_207=='-'||(LA5_207>='0' && LA5_207<='9')||(LA5_207>='A' && LA5_207<='Z')||LA5_207=='_'||(LA5_207>='a' && LA5_207<='z')) ) {
                                                    alt5=42;
                                                }
                                                else {
                                                    alt5=8;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                        }
                                        break;
                                    default:
                                        alt5=42;}

                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                    }
                    break;
                case 'o':
                    {
                    int LA5_72 = input.LA(4);

                    if ( (LA5_72=='d') ) {
                        int LA5_94 = input.LA(5);

                        if ( (LA5_94=='u') ) {
                            int LA5_117 = input.LA(6);

                            if ( (LA5_117=='c') ) {
                                int LA5_139 = input.LA(7);

                                if ( (LA5_139=='t') ) {
                                    int LA5_160 = input.LA(8);

                                    if ( (LA5_160=='i') ) {
                                        int LA5_179 = input.LA(9);

                                        if ( (LA5_179=='o') ) {
                                            int LA5_194 = input.LA(10);

                                            if ( (LA5_194=='n') ) {
                                                int LA5_208 = input.LA(11);

                                                if ( (LA5_208=='-'||(LA5_208>='0' && LA5_208<='9')||(LA5_208>='A' && LA5_208<='Z')||LA5_208=='_'||(LA5_208>='a' && LA5_208<='z')) ) {
                                                    alt5=42;
                                                }
                                                else {
                                                    alt5=22;}
                                            }
                                            else {
                                                alt5=42;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                    }
                    break;
                default:
                    alt5=42;}

                }
                break;
            default:
                alt5=42;}

            }
            break;
        case 'g':
            {
            int LA5_9 = input.LA(2);

            if ( (LA5_9=='r') ) {
                int LA5_46 = input.LA(3);

                if ( (LA5_46=='o') ) {
                    int LA5_73 = input.LA(4);

                    if ( (LA5_73=='u') ) {
                        int LA5_95 = input.LA(5);

                        if ( (LA5_95=='p') ) {
                            int LA5_118 = input.LA(6);

                            if ( (LA5_118=='-'||(LA5_118>='0' && LA5_118<='9')||(LA5_118>='A' && LA5_118<='Z')||LA5_118=='_'||(LA5_118>='a' && LA5_118<='z')) ) {
                                alt5=42;
                            }
                            else {
                                alt5=14;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'l':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA5_47 = input.LA(3);

                if ( (LA5_47=='s') ) {
                    int LA5_74 = input.LA(4);

                    if ( (LA5_74=='t') ) {
                        int LA5_96 = input.LA(5);

                        if ( (LA5_96=='-'||(LA5_96>='0' && LA5_96<='9')||(LA5_96>='A' && LA5_96<='Z')||LA5_96=='_'||(LA5_96>='a' && LA5_96<='z')) ) {
                            alt5=42;
                        }
                        else {
                            alt5=18;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            case 'e':
                {
                int LA5_48 = input.LA(3);

                if ( (LA5_48=='f') ) {
                    int LA5_75 = input.LA(4);

                    if ( (LA5_75=='t') ) {
                        int LA5_97 = input.LA(5);

                        if ( (LA5_97=='P') ) {
                            int LA5_120 = input.LA(6);

                            if ( (LA5_120=='a') ) {
                                int LA5_141 = input.LA(7);

                                if ( (LA5_141=='r') ) {
                                    int LA5_161 = input.LA(8);

                                    if ( (LA5_161=='t') ) {
                                        int LA5_180 = input.LA(9);

                                        if ( (LA5_180=='-'||(LA5_180>='0' && LA5_180<='9')||(LA5_180>='A' && LA5_180<='Z')||LA5_180=='_'||(LA5_180>='a' && LA5_180<='z')) ) {
                                            alt5=42;
                                        }
                                        else {
                                            alt5=23;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
                }
                break;
            default:
                alt5=42;}

            }
            break;
        case 'o':
            {
            int LA5_11 = input.LA(2);

            if ( (LA5_11=='r') ) {
                int LA5_49 = input.LA(3);

                if ( (LA5_49=='i') ) {
                    int LA5_76 = input.LA(4);

                    if ( (LA5_76=='g') ) {
                        int LA5_98 = input.LA(5);

                        if ( (LA5_98=='i') ) {
                            int LA5_121 = input.LA(6);

                            if ( (LA5_121=='n') ) {
                                int LA5_142 = input.LA(7);

                                if ( (LA5_142=='-'||(LA5_142>='0' && LA5_142<='9')||(LA5_142>='A' && LA5_142<='Z')||LA5_142=='_'||(LA5_142>='a' && LA5_142<='z')) ) {
                                    alt5=42;
                                }
                                else {
                                    alt5=19;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'r':
            {
            int LA5_12 = input.LA(2);

            if ( (LA5_12=='i') ) {
                int LA5_50 = input.LA(3);

                if ( (LA5_50=='g') ) {
                    int LA5_77 = input.LA(4);

                    if ( (LA5_77=='h') ) {
                        int LA5_99 = input.LA(5);

                        if ( (LA5_99=='t') ) {
                            int LA5_122 = input.LA(6);

                            if ( (LA5_122=='P') ) {
                                int LA5_143 = input.LA(7);

                                if ( (LA5_143=='a') ) {
                                    int LA5_163 = input.LA(8);

                                    if ( (LA5_163=='r') ) {
                                        int LA5_181 = input.LA(9);

                                        if ( (LA5_181=='t') ) {
                                            int LA5_196 = input.LA(10);

                                            if ( (LA5_196=='-'||(LA5_196>='0' && LA5_196<='9')||(LA5_196>='A' && LA5_196<='Z')||LA5_196=='_'||(LA5_196>='a' && LA5_196<='z')) ) {
                                                alt5=42;
                                            }
                                            else {
                                                alt5=24;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case 'a':
            {
            int LA5_13 = input.LA(2);

            if ( (LA5_13=='t') ) {
                int LA5_51 = input.LA(3);

                if ( (LA5_51=='t') ) {
                    int LA5_78 = input.LA(4);

                    if ( (LA5_78=='r') ) {
                        int LA5_100 = input.LA(5);

                        if ( (LA5_100=='i') ) {
                            int LA5_123 = input.LA(6);

                            if ( (LA5_123=='b') ) {
                                int LA5_144 = input.LA(7);

                                if ( (LA5_144=='u') ) {
                                    int LA5_164 = input.LA(8);

                                    if ( (LA5_164=='t') ) {
                                        int LA5_182 = input.LA(9);

                                        if ( (LA5_182=='e') ) {
                                            int LA5_197 = input.LA(10);

                                            if ( (LA5_197=='-'||(LA5_197>='0' && LA5_197<='9')||(LA5_197>='A' && LA5_197<='Z')||LA5_197=='_'||(LA5_197>='a' && LA5_197<='z')) ) {
                                                alt5=42;
                                            }
                                            else {
                                                alt5=25;}
                                        }
                                        else {
                                            alt5=42;}
                                    }
                                    else {
                                        alt5=42;}
                                }
                                else {
                                    alt5=42;}
                            }
                            else {
                                alt5=42;}
                        }
                        else {
                            alt5=42;}
                    }
                    else {
                        alt5=42;}
                }
                else {
                    alt5=42;}
            }
            else {
                alt5=42;}
            }
            break;
        case '+':
            {
            alt5=26;
            }
            break;
        case '*':
            {
            alt5=27;
            }
            break;
        case '[':
            {
            int LA5_16 = input.LA(2);

            if ( (LA5_16=='[') ) {
                alt5=30;
            }
            else {
                alt5=28;}
            }
            break;
        case ']':
            {
            int LA5_17 = input.LA(2);

            if ( (LA5_17==']') ) {
                alt5=31;
            }
            else {
                alt5=29;}
            }
            break;
        case '(':
            {
            alt5=32;
            }
            break;
        case ')':
            {
            alt5=33;
            }
            break;
        case ',':
            {
            alt5=34;
            }
            break;
        case '/':
            {
            alt5=35;
            }
            break;
        case '=':
            {
            alt5=36;
            }
            break;
        case '-':
            {
            int LA5_23 = input.LA(2);

            if ( (LA5_23=='>') ) {
                alt5=37;
            }
            else {
                alt5=42;}
            }
            break;
        case '{':
            {
            alt5=38;
            }
            break;
        case '}':
            {
            alt5=39;
            }
            break;
        case '>':
            {
            alt5=40;
            }
            break;
        case '?':
            {
            alt5=41;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '_':
        case 'b':
        case 'f':
        case 'j':
        case 'k':
        case 'n':
        case 'q':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=42;
            }
            break;
        case '\"':
            {
            alt5=43;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt5=44;
            }
            break;
        case '%':
            {
            alt5=45;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( DEFINITION | MODULE | IMPORTS | EXPORTS | HIDDENS | CONTEXTFREE | SYNTAX | PRIORITIES | STARTSYMBOLS | SORTS | MODULENAME | PRIORITY | SORT | GROUP | COMPOUNDSYMBOL | MARKEDSYMBOL | PARAMETERIZEDSYMBOL | LIST | ORIGIN | SEPARATOR | MULTIPLICITY | PRODUCTION | LEFT | RIGHT | ATTRIBUTE | PLUS | STAR | LBRACKET | RBRACKET | LDOUBLEBRACKET | RDOUBLEBRACKET | LPAREN | RPAREN | COMMA | SLASH | BOLD_IMP | IMP | LBRACE | RBRACE | GREATER | QUESTION | ID | STRING | WHITESPACE | SL_COMMENT );", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:10: DEFINITION
                {
                mDEFINITION(); 

                }
                break;
            case 2 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:21: MODULE
                {
                mMODULE(); 

                }
                break;
            case 3 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:28: IMPORTS
                {
                mIMPORTS(); 

                }
                break;
            case 4 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:36: EXPORTS
                {
                mEXPORTS(); 

                }
                break;
            case 5 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:44: HIDDENS
                {
                mHIDDENS(); 

                }
                break;
            case 6 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:52: CONTEXTFREE
                {
                mCONTEXTFREE(); 

                }
                break;
            case 7 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:64: SYNTAX
                {
                mSYNTAX(); 

                }
                break;
            case 8 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:71: PRIORITIES
                {
                mPRIORITIES(); 

                }
                break;
            case 9 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:82: STARTSYMBOLS
                {
                mSTARTSYMBOLS(); 

                }
                break;
            case 10 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:95: SORTS
                {
                mSORTS(); 

                }
                break;
            case 11 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:101: MODULENAME
                {
                mMODULENAME(); 

                }
                break;
            case 12 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:112: PRIORITY
                {
                mPRIORITY(); 

                }
                break;
            case 13 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:121: SORT
                {
                mSORT(); 

                }
                break;
            case 14 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:126: GROUP
                {
                mGROUP(); 

                }
                break;
            case 15 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:132: COMPOUNDSYMBOL
                {
                mCOMPOUNDSYMBOL(); 

                }
                break;
            case 16 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:147: MARKEDSYMBOL
                {
                mMARKEDSYMBOL(); 

                }
                break;
            case 17 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:160: PARAMETERIZEDSYMBOL
                {
                mPARAMETERIZEDSYMBOL(); 

                }
                break;
            case 18 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:180: LIST
                {
                mLIST(); 

                }
                break;
            case 19 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:185: ORIGIN
                {
                mORIGIN(); 

                }
                break;
            case 20 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:192: SEPARATOR
                {
                mSEPARATOR(); 

                }
                break;
            case 21 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:202: MULTIPLICITY
                {
                mMULTIPLICITY(); 

                }
                break;
            case 22 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:215: PRODUCTION
                {
                mPRODUCTION(); 

                }
                break;
            case 23 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:226: LEFT
                {
                mLEFT(); 

                }
                break;
            case 24 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:231: RIGHT
                {
                mRIGHT(); 

                }
                break;
            case 25 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:237: ATTRIBUTE
                {
                mATTRIBUTE(); 

                }
                break;
            case 26 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:247: PLUS
                {
                mPLUS(); 

                }
                break;
            case 27 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:252: STAR
                {
                mSTAR(); 

                }
                break;
            case 28 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:257: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 29 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:266: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 30 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:275: LDOUBLEBRACKET
                {
                mLDOUBLEBRACKET(); 

                }
                break;
            case 31 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:290: RDOUBLEBRACKET
                {
                mRDOUBLEBRACKET(); 

                }
                break;
            case 32 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:305: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 33 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:312: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 34 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:319: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 35 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:325: SLASH
                {
                mSLASH(); 

                }
                break;
            case 36 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:331: BOLD_IMP
                {
                mBOLD_IMP(); 

                }
                break;
            case 37 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:340: IMP
                {
                mIMP(); 

                }
                break;
            case 38 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:344: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 39 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:351: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 40 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:358: GREATER
                {
                mGREATER(); 

                }
                break;
            case 41 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:366: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 42 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:375: ID
                {
                mID(); 

                }
                break;
            case 43 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:378: STRING
                {
                mSTRING(); 

                }
                break;
            case 44 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:385: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 45 :
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:1:396: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;

        }

    }


 

}