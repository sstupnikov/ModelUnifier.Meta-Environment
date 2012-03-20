// $ANTLR 3.0.1 D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g 2008-05-14 19:22:04

	package unifier.sdfparser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class SdfParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DEFINITION", "MODULE", "IMPORTS", "EXPORTS", "HIDDENS", "CONTEXTFREE", "SYNTAX", "PRIORITIES", "STARTSYMBOLS", "SORTS", "MODULENAME", "PRIORITY", "SORT", "GROUP", "COMPOUNDSYMBOL", "MARKEDSYMBOL", "PARAMETERIZEDSYMBOL", "LIST", "ORIGIN", "SEPARATOR", "MULTIPLICITY", "PRODUCTION", "LEFT", "RIGHT", "ATTRIBUTE", "SLASH", "ID", "GREATER", "STAR", "PLUS", "QUESTION", "STRING", "LPAREN", "RPAREN", "LDOUBLEBRACKET", "COMMA", "RDOUBLEBRACKET", "LBRACE", "RBRACE", "IMP", "DIGIT", "SMALLLETTER", "CAPITALLETTER", "LETTER", "MINUS", "UNDERSCORE", "LBRACKET", "RBRACKET", "BOLD_IMP", "WHITESPACE", "SL_COMMENT"
    };
    public static final int STAR=32;
    public static final int LETTER=47;
    public static final int LBRACE=41;
    public static final int ATTRIBUTE=28;
    public static final int CONTEXTFREE=9;
    public static final int SEPARATOR=23;
    public static final int ID=30;
    public static final int MARKEDSYMBOL=19;
    public static final int EOF=-1;
    public static final int SORTS=13;
    public static final int LPAREN=36;
    public static final int LBRACKET=50;
    public static final int SYNTAX=10;
    public static final int MULTIPLICITY=24;
    public static final int RPAREN=37;
    public static final int SLASH=29;
    public static final int GREATER=31;
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
    public static final int LIST=21;
    public static final int MODULENAME=14;
    public static final int PARAMETERIZEDSYMBOL=20;
    public static final int GROUP=17;
    public static final int PRIORITY=15;
    public static final int QUESTION=34;
    public static final int SL_COMMENT=54;
    public static final int BOLD_IMP=52;
    public static final int LDOUBLEBRACKET=38;
    public static final int HIDDENS=8;
    public static final int RDOUBLEBRACKET=40;
    public static final int IMPORTS=6;
    public static final int PRIORITIES=11;
    public static final int STRING=35;

        public SdfParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g"; }


    public static class module_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start module
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:46:1: module : MODULE moduleName ( impSection )* ( section )* EOF ;
    public final module_return module() throws RecognitionException {
        module_return retval = new module_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MODULE1=null;
        Token EOF5=null;
        moduleName_return moduleName2 = null;

        impSection_return impSection3 = null;

        section_return section4 = null;


        CommonTree MODULE1_tree=null;
        CommonTree EOF5_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:2: ( MODULE moduleName ( impSection )* ( section )* EOF )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:4: MODULE moduleName ( impSection )* ( section )* EOF
            {
            root_0 = (CommonTree)adaptor.nil();

            MODULE1=(Token)input.LT(1);
            match(input,MODULE,FOLLOW_MODULE_in_module256); 
            MODULE1_tree = (CommonTree)adaptor.create(MODULE1);
            root_0 = (CommonTree)adaptor.becomeRoot(MODULE1_tree, root_0);

            pushFollow(FOLLOW_moduleName_in_module259);
            moduleName2=moduleName();
            _fsp--;

            adaptor.addChild(root_0, moduleName2.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:23: ( impSection )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORTS) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:23: impSection
            	    {
            	    pushFollow(FOLLOW_impSection_in_module261);
            	    impSection3=impSection();
            	    _fsp--;

            	    adaptor.addChild(root_0, impSection3.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:35: ( section )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=EXPORTS && LA2_0<=HIDDENS)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:47:35: section
            	    {
            	    pushFollow(FOLLOW_section_in_module264);
            	    section4=section();
            	    _fsp--;

            	    adaptor.addChild(root_0, section4.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            EOF5=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_module267); 
            EOF5_tree = (CommonTree)adaptor.create(EOF5);
            adaptor.addChild(root_0, EOF5_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end module

    public static class moduleName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start moduleName
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:50:1: moduleName : mid= moduleId -> ^( MODULENAME $mid) ;
    public final moduleName_return moduleName() throws RecognitionException {
        moduleName_return retval = new moduleName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        moduleId_return mid = null;


        RewriteRuleSubtreeStream stream_moduleId=new RewriteRuleSubtreeStream(adaptor,"rule moduleId");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:51:2: (mid= moduleId -> ^( MODULENAME $mid) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:51:4: mid= moduleId
            {
            pushFollow(FOLLOW_moduleId_in_moduleName280);
            mid=moduleId();
            _fsp--;

            stream_moduleId.add(mid.getTree());

            // AST REWRITE
            // elements: mid
            // token labels: 
            // rule labels: retval, mid
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_mid=new RewriteRuleSubtreeStream(adaptor,"token mid",mid!=null?mid.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 51:17: -> ^( MODULENAME $mid)
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:51:20: ^( MODULENAME $mid)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(MODULENAME, "MODULENAME"), root_1);

                adaptor.addChild(root_1, stream_mid.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end moduleName

    public static class moduleId_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start moduleId
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:54:1: moduleId : moduleWord ( SLASH moduleWord )* ;
    public final moduleId_return moduleId() throws RecognitionException {
        moduleId_return retval = new moduleId_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SLASH7=null;
        moduleWord_return moduleWord6 = null;

        moduleWord_return moduleWord8 = null;


        CommonTree SLASH7_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:55:2: ( moduleWord ( SLASH moduleWord )* )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:55:4: moduleWord ( SLASH moduleWord )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_moduleWord_in_moduleId302);
            moduleWord6=moduleWord();
            _fsp--;

            adaptor.addChild(root_0, moduleWord6.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:55:15: ( SLASH moduleWord )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==SLASH) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:55:16: SLASH moduleWord
            	    {
            	    SLASH7=(Token)input.LT(1);
            	    match(input,SLASH,FOLLOW_SLASH_in_moduleId305); 
            	    pushFollow(FOLLOW_moduleWord_in_moduleId308);
            	    moduleWord8=moduleWord();
            	    _fsp--;

            	    adaptor.addChild(root_0, moduleWord8.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end moduleId

    public static class moduleWord_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start moduleWord
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:58:1: moduleWord : ID ;
    public final moduleWord_return moduleWord() throws RecognitionException {
        moduleWord_return retval = new moduleWord_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID9=null;

        CommonTree ID9_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:59:2: ( ID )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:59:4: ID
            {
            root_0 = (CommonTree)adaptor.nil();

            ID9=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_moduleWord321); 
            ID9_tree = (CommonTree)adaptor.create(ID9);
            adaptor.addChild(root_0, ID9_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end moduleWord

    public static class impSection_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start impSection
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:62:1: impSection : IMPORTS ( imports )* ;
    public final impSection_return impSection() throws RecognitionException {
        impSection_return retval = new impSection_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IMPORTS10=null;
        imports_return imports11 = null;


        CommonTree IMPORTS10_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:63:2: ( IMPORTS ( imports )* )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:63:4: IMPORTS ( imports )*
            {
            root_0 = (CommonTree)adaptor.nil();

            IMPORTS10=(Token)input.LT(1);
            match(input,IMPORTS,FOLLOW_IMPORTS_in_impSection333); 
            IMPORTS10_tree = (CommonTree)adaptor.create(IMPORTS10);
            root_0 = (CommonTree)adaptor.becomeRoot(IMPORTS10_tree, root_0);

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:63:13: ( imports )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==ID) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:63:13: imports
            	    {
            	    pushFollow(FOLLOW_imports_in_impSection336);
            	    imports11=imports();
            	    _fsp--;

            	    adaptor.addChild(root_0, imports11.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end impSection

    public static class imports_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start imports
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:66:1: imports : moduleName ;
    public final imports_return imports() throws RecognitionException {
        imports_return retval = new imports_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        moduleName_return moduleName12 = null;



        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:67:2: ( moduleName )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:67:4: moduleName
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_moduleName_in_imports348);
            moduleName12=moduleName();
            _fsp--;

            adaptor.addChild(root_0, moduleName12.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end imports

    public static class section_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start section
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:70:1: section : ( EXPORTS | HIDDENS ) ( sdfGrammar )+ ;
    public final section_return section() throws RecognitionException {
        section_return retval = new section_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EXPORTS13=null;
        Token HIDDENS14=null;
        sdfGrammar_return sdfGrammar15 = null;


        CommonTree EXPORTS13_tree=null;
        CommonTree HIDDENS14_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:2: ( ( EXPORTS | HIDDENS ) ( sdfGrammar )+ )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:4: ( EXPORTS | HIDDENS ) ( sdfGrammar )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:4: ( EXPORTS | HIDDENS )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==EXPORTS) ) {
                alt5=1;
            }
            else if ( (LA5_0==HIDDENS) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("71:4: ( EXPORTS | HIDDENS )", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:5: EXPORTS
                    {
                    EXPORTS13=(Token)input.LT(1);
                    match(input,EXPORTS,FOLLOW_EXPORTS_in_section362); 
                    EXPORTS13_tree = (CommonTree)adaptor.create(EXPORTS13);
                    root_0 = (CommonTree)adaptor.becomeRoot(EXPORTS13_tree, root_0);


                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:16: HIDDENS
                    {
                    HIDDENS14=(Token)input.LT(1);
                    match(input,HIDDENS,FOLLOW_HIDDENS_in_section367); 
                    HIDDENS14_tree = (CommonTree)adaptor.create(HIDDENS14);
                    root_0 = (CommonTree)adaptor.becomeRoot(HIDDENS14_tree, root_0);


                    }
                    break;

            }

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:26: ( sdfGrammar )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==CONTEXTFREE||LA6_0==SORTS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:71:26: sdfGrammar
            	    {
            	    pushFollow(FOLLOW_sdfGrammar_in_section371);
            	    sdfGrammar15=sdfGrammar();
            	    _fsp--;

            	    adaptor.addChild(root_0, sdfGrammar15.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end section

    public static class sdfGrammar_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sdfGrammar
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:74:1: sdfGrammar : ( CONTEXTFREE ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* ) | SORTS ( symbol )* );
    public final sdfGrammar_return sdfGrammar() throws RecognitionException {
        sdfGrammar_return retval = new sdfGrammar_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CONTEXTFREE16=null;
        Token SYNTAX17=null;
        Token PRIORITIES19=null;
        Token STARTSYMBOLS21=null;
        Token SORTS23=null;
        production_return production18 = null;

        priority_return priority20 = null;

        symbol_return symbol22 = null;

        symbol_return symbol24 = null;


        CommonTree CONTEXTFREE16_tree=null;
        CommonTree SYNTAX17_tree=null;
        CommonTree PRIORITIES19_tree=null;
        CommonTree STARTSYMBOLS21_tree=null;
        CommonTree SORTS23_tree=null;

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:2: ( CONTEXTFREE ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* ) | SORTS ( symbol )* )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==CONTEXTFREE) ) {
                alt12=1;
            }
            else if ( (LA12_0==SORTS) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("74:1: sdfGrammar : ( CONTEXTFREE ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* ) | SORTS ( symbol )* );", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:4: CONTEXTFREE ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CONTEXTFREE16=(Token)input.LT(1);
                    match(input,CONTEXTFREE,FOLLOW_CONTEXTFREE_in_sdfGrammar383); 
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:17: ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* )
                    int alt10=3;
                    switch ( input.LA(1) ) {
                    case SYNTAX:
                        {
                        alt10=1;
                        }
                        break;
                    case PRIORITIES:
                        {
                        alt10=2;
                        }
                        break;
                    case STARTSYMBOLS:
                        {
                        alt10=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("75:17: ( SYNTAX ( production )* | PRIORITIES ( priority )* | STARTSYMBOLS ( symbol )* )", 10, 0, input);

                        throw nvae;
                    }

                    switch (alt10) {
                        case 1 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:18: SYNTAX ( production )*
                            {
                            SYNTAX17=(Token)input.LT(1);
                            match(input,SYNTAX,FOLLOW_SYNTAX_in_sdfGrammar387); 
                            SYNTAX17_tree = (CommonTree)adaptor.create(SYNTAX17);
                            root_0 = (CommonTree)adaptor.becomeRoot(SYNTAX17_tree, root_0);

                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:26: ( production )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==ID||(LA7_0>=STRING && LA7_0<=LPAREN)||LA7_0==LBRACE||LA7_0==IMP) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:26: production
                            	    {
                            	    pushFollow(FOLLOW_production_in_sdfGrammar390);
                            	    production18=production();
                            	    _fsp--;

                            	    adaptor.addChild(root_0, production18.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop7;
                                }
                            } while (true);


                            }
                            break;
                        case 2 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:40: PRIORITIES ( priority )*
                            {
                            PRIORITIES19=(Token)input.LT(1);
                            match(input,PRIORITIES,FOLLOW_PRIORITIES_in_sdfGrammar395); 
                            PRIORITIES19_tree = (CommonTree)adaptor.create(PRIORITIES19);
                            root_0 = (CommonTree)adaptor.becomeRoot(PRIORITIES19_tree, root_0);

                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:52: ( priority )*
                            loop8:
                            do {
                                int alt8=2;
                                int LA8_0 = input.LA(1);

                                if ( (LA8_0==ID||(LA8_0>=STRING && LA8_0<=LPAREN)||LA8_0==LBRACE||LA8_0==IMP) ) {
                                    alt8=1;
                                }


                                switch (alt8) {
                            	case 1 :
                            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:52: priority
                            	    {
                            	    pushFollow(FOLLOW_priority_in_sdfGrammar398);
                            	    priority20=priority();
                            	    _fsp--;

                            	    adaptor.addChild(root_0, priority20.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop8;
                                }
                            } while (true);


                            }
                            break;
                        case 3 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:64: STARTSYMBOLS ( symbol )*
                            {
                            STARTSYMBOLS21=(Token)input.LT(1);
                            match(input,STARTSYMBOLS,FOLLOW_STARTSYMBOLS_in_sdfGrammar403); 
                            STARTSYMBOLS21_tree = (CommonTree)adaptor.create(STARTSYMBOLS21);
                            root_0 = (CommonTree)adaptor.becomeRoot(STARTSYMBOLS21_tree, root_0);

                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:78: ( symbol )*
                            loop9:
                            do {
                                int alt9=2;
                                int LA9_0 = input.LA(1);

                                if ( (LA9_0==ID||(LA9_0>=STRING && LA9_0<=LPAREN)||LA9_0==LBRACE) ) {
                                    alt9=1;
                                }


                                switch (alt9) {
                            	case 1 :
                            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:75:78: symbol
                            	    {
                            	    pushFollow(FOLLOW_symbol_in_sdfGrammar406);
                            	    symbol22=symbol();
                            	    _fsp--;

                            	    adaptor.addChild(root_0, symbol22.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop9;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:76:4: SORTS ( symbol )*
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    SORTS23=(Token)input.LT(1);
                    match(input,SORTS,FOLLOW_SORTS_in_sdfGrammar413); 
                    SORTS23_tree = (CommonTree)adaptor.create(SORTS23);
                    root_0 = (CommonTree)adaptor.becomeRoot(SORTS23_tree, root_0);

                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:76:11: ( symbol )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==ID||(LA11_0>=STRING && LA11_0<=LPAREN)||LA11_0==LBRACE) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:76:11: symbol
                    	    {
                    	    pushFollow(FOLLOW_symbol_in_sdfGrammar416);
                    	    symbol24=symbol();
                    	    _fsp--;

                    	    adaptor.addChild(root_0, symbol24.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sdfGrammar

    public static class priority_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start priority
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:79:1: priority : group ( GREATER group )* -> ^( PRIORITY ( group )+ ) ;
    public final priority_return priority() throws RecognitionException {
        priority_return retval = new priority_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token GREATER26=null;
        group_return group25 = null;

        group_return group27 = null;


        CommonTree GREATER26_tree=null;
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleSubtreeStream stream_group=new RewriteRuleSubtreeStream(adaptor,"rule group");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:80:2: ( group ( GREATER group )* -> ^( PRIORITY ( group )+ ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:80:4: group ( GREATER group )*
            {
            pushFollow(FOLLOW_group_in_priority429);
            group25=group();
            _fsp--;

            stream_group.add(group25.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:80:10: ( GREATER group )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==GREATER) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:80:11: GREATER group
            	    {
            	    GREATER26=(Token)input.LT(1);
            	    match(input,GREATER,FOLLOW_GREATER_in_priority432); 
            	    stream_GREATER.add(GREATER26);

            	    pushFollow(FOLLOW_group_in_priority434);
            	    group27=group();
            	    _fsp--;

            	    stream_group.add(group27.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            // AST REWRITE
            // elements: group
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 80:27: -> ^( PRIORITY ( group )+ )
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:80:30: ^( PRIORITY ( group )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PRIORITY, "PRIORITY"), root_1);

                if ( !(stream_group.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_group.hasNext() ) {
                    adaptor.addChild(root_1, stream_group.next());

                }
                stream_group.reset();

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end priority

    public static class group_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start group
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:83:1: group : production -> ^( GROUP production ) ;
    public final group_return group() throws RecognitionException {
        group_return retval = new group_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        production_return production28 = null;


        RewriteRuleSubtreeStream stream_production=new RewriteRuleSubtreeStream(adaptor,"rule production");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:84:2: ( production -> ^( GROUP production ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:84:4: production
            {
            pushFollow(FOLLOW_production_in_group458);
            production28=production();
            _fsp--;

            stream_production.add(production28.getTree());

            // AST REWRITE
            // elements: production
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 84:15: -> ^( GROUP production )
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:84:18: ^( GROUP production )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(GROUP, "GROUP"), root_1);

                adaptor.addChild(root_1, stream_production.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end group

    public static class symbol_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start symbol
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:87:1: symbol : ( markedSymbol | list );
    public final symbol_return symbol() throws RecognitionException {
        symbol_return retval = new symbol_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        markedSymbol_return markedSymbol29 = null;

        list_return list30 = null;



        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:88:2: ( markedSymbol | list )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ID||(LA14_0>=STRING && LA14_0<=LPAREN)) ) {
                alt14=1;
            }
            else if ( (LA14_0==LBRACE) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("87:1: symbol : ( markedSymbol | list );", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:88:4: markedSymbol
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_markedSymbol_in_symbol478);
                    markedSymbol29=markedSymbol();
                    _fsp--;

                    adaptor.addChild(root_0, markedSymbol29.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:89:4: list
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_list_in_symbol483);
                    list30=list();
                    _fsp--;

                    adaptor.addChild(root_0, list30.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end symbol

    public static class markedSymbol_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start markedSymbol
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:92:1: markedSymbol : a= nonMarkedSymbol ( (s= STAR | s= PLUS | s= QUESTION ) -> ^( MARKEDSYMBOL $a $s) | -> nonMarkedSymbol ) ;
    public final markedSymbol_return markedSymbol() throws RecognitionException {
        markedSymbol_return retval = new markedSymbol_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token s=null;
        nonMarkedSymbol_return a = null;


        CommonTree s_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleSubtreeStream stream_nonMarkedSymbol=new RewriteRuleSubtreeStream(adaptor,"rule nonMarkedSymbol");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:93:2: (a= nonMarkedSymbol ( (s= STAR | s= PLUS | s= QUESTION ) -> ^( MARKEDSYMBOL $a $s) | -> nonMarkedSymbol ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:93:4: a= nonMarkedSymbol ( (s= STAR | s= PLUS | s= QUESTION ) -> ^( MARKEDSYMBOL $a $s) | -> nonMarkedSymbol )
            {
            pushFollow(FOLLOW_nonMarkedSymbol_in_markedSymbol496);
            a=nonMarkedSymbol();
            _fsp--;

            stream_nonMarkedSymbol.add(a.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:3: ( (s= STAR | s= PLUS | s= QUESTION ) -> ^( MARKEDSYMBOL $a $s) | -> nonMarkedSymbol )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=STAR && LA16_0<=QUESTION)) ) {
                alt16=1;
            }
            else if ( (LA16_0==EOF||(LA16_0>=EXPORTS && LA16_0<=CONTEXTFREE)||LA16_0==SORTS||(LA16_0>=ID && LA16_0<=GREATER)||(LA16_0>=STRING && LA16_0<=RPAREN)||(LA16_0>=COMMA && LA16_0<=LBRACE)||LA16_0==IMP) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("94:3: ( (s= STAR | s= PLUS | s= QUESTION ) -> ^( MARKEDSYMBOL $a $s) | -> nonMarkedSymbol )", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:5: (s= STAR | s= PLUS | s= QUESTION )
                    {
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:5: (s= STAR | s= PLUS | s= QUESTION )
                    int alt15=3;
                    switch ( input.LA(1) ) {
                    case STAR:
                        {
                        alt15=1;
                        }
                        break;
                    case PLUS:
                        {
                        alt15=2;
                        }
                        break;
                    case QUESTION:
                        {
                        alt15=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("94:5: (s= STAR | s= PLUS | s= QUESTION )", 15, 0, input);

                        throw nvae;
                    }

                    switch (alt15) {
                        case 1 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:6: s= STAR
                            {
                            s=(Token)input.LT(1);
                            match(input,STAR,FOLLOW_STAR_in_markedSymbol506); 
                            stream_STAR.add(s);


                            }
                            break;
                        case 2 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:15: s= PLUS
                            {
                            s=(Token)input.LT(1);
                            match(input,PLUS,FOLLOW_PLUS_in_markedSymbol512); 
                            stream_PLUS.add(s);


                            }
                            break;
                        case 3 :
                            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:24: s= QUESTION
                            {
                            s=(Token)input.LT(1);
                            match(input,QUESTION,FOLLOW_QUESTION_in_markedSymbol518); 
                            stream_QUESTION.add(s);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: s, a
                    // token labels: s
                    // rule labels: retval, a
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"token a",a!=null?a.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 94:36: -> ^( MARKEDSYMBOL $a $s)
                    {
                        // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:94:39: ^( MARKEDSYMBOL $a $s)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(MARKEDSYMBOL, "MARKEDSYMBOL"), root_1);

                        adaptor.addChild(root_1, stream_a.next());
                        adaptor.addChild(root_1, stream_s.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:95:17: 
                    {

                    // AST REWRITE
                    // elements: nonMarkedSymbol
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 95:17: -> nonMarkedSymbol
                    {
                        adaptor.addChild(root_0, stream_nonMarkedSymbol.next());

                    }



                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end markedSymbol

    public static class nonMarkedSymbol_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start nonMarkedSymbol
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:99:1: nonMarkedSymbol : ( parameterizedSort | STRING | LPAREN ( symbol )* RPAREN -> ^( COMPOUNDSYMBOL ( symbol )* ) );
    public final nonMarkedSymbol_return nonMarkedSymbol() throws RecognitionException {
        nonMarkedSymbol_return retval = new nonMarkedSymbol_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STRING32=null;
        Token LPAREN33=null;
        Token RPAREN35=null;
        parameterizedSort_return parameterizedSort31 = null;

        symbol_return symbol34 = null;


        CommonTree STRING32_tree=null;
        CommonTree LPAREN33_tree=null;
        CommonTree RPAREN35_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_symbol=new RewriteRuleSubtreeStream(adaptor,"rule symbol");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:100:2: ( parameterizedSort | STRING | LPAREN ( symbol )* RPAREN -> ^( COMPOUNDSYMBOL ( symbol )* ) )
            int alt18=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt18=1;
                }
                break;
            case STRING:
                {
                alt18=2;
                }
                break;
            case LPAREN:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("99:1: nonMarkedSymbol : ( parameterizedSort | STRING | LPAREN ( symbol )* RPAREN -> ^( COMPOUNDSYMBOL ( symbol )* ) );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:100:4: parameterizedSort
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_parameterizedSort_in_nonMarkedSymbol559);
                    parameterizedSort31=parameterizedSort();
                    _fsp--;

                    adaptor.addChild(root_0, parameterizedSort31.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:101:5: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING32=(Token)input.LT(1);
                    match(input,STRING,FOLLOW_STRING_in_nonMarkedSymbol565); 
                    STRING32_tree = (CommonTree)adaptor.create(STRING32);
                    root_0 = (CommonTree)adaptor.becomeRoot(STRING32_tree, root_0);


                    }
                    break;
                case 3 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:102:7: LPAREN ( symbol )* RPAREN
                    {
                    LPAREN33=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_nonMarkedSymbol575); 
                    stream_LPAREN.add(LPAREN33);

                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:102:14: ( symbol )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==ID||(LA17_0>=STRING && LA17_0<=LPAREN)||LA17_0==LBRACE) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:102:14: symbol
                    	    {
                    	    pushFollow(FOLLOW_symbol_in_nonMarkedSymbol577);
                    	    symbol34=symbol();
                    	    _fsp--;

                    	    stream_symbol.add(symbol34.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    RPAREN35=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_nonMarkedSymbol580); 
                    stream_RPAREN.add(RPAREN35);


                    // AST REWRITE
                    // elements: symbol
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 102:29: -> ^( COMPOUNDSYMBOL ( symbol )* )
                    {
                        // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:102:32: ^( COMPOUNDSYMBOL ( symbol )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(COMPOUNDSYMBOL, "COMPOUNDSYMBOL"), root_1);

                        // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:102:49: ( symbol )*
                        while ( stream_symbol.hasNext() ) {
                            adaptor.addChild(root_1, stream_symbol.next());

                        }
                        stream_symbol.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end nonMarkedSymbol

    public static class parameterizedSort_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parameterizedSort
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:105:1: parameterizedSort : sort ( LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ ) | -> sort ) ;
    public final parameterizedSort_return parameterizedSort() throws RecognitionException {
        parameterizedSort_return retval = new parameterizedSort_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LDOUBLEBRACKET37=null;
        Token COMMA39=null;
        Token RDOUBLEBRACKET41=null;
        sort_return sort36 = null;

        symbol_return symbol38 = null;

        symbol_return symbol40 = null;


        CommonTree LDOUBLEBRACKET37_tree=null;
        CommonTree COMMA39_tree=null;
        CommonTree RDOUBLEBRACKET41_tree=null;
        RewriteRuleTokenStream stream_LDOUBLEBRACKET=new RewriteRuleTokenStream(adaptor,"token LDOUBLEBRACKET");
        RewriteRuleTokenStream stream_RDOUBLEBRACKET=new RewriteRuleTokenStream(adaptor,"token RDOUBLEBRACKET");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_sort=new RewriteRuleSubtreeStream(adaptor,"rule sort");
        RewriteRuleSubtreeStream stream_symbol=new RewriteRuleSubtreeStream(adaptor,"rule symbol");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:106:2: ( sort ( LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ ) | -> sort ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:106:4: sort ( LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ ) | -> sort )
            {
            pushFollow(FOLLOW_sort_in_parameterizedSort602);
            sort36=sort();
            _fsp--;

            stream_sort.add(sort36.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:107:3: ( LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ ) | -> sort )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==LDOUBLEBRACKET) ) {
                alt20=1;
            }
            else if ( (LA20_0==EOF||(LA20_0>=EXPORTS && LA20_0<=CONTEXTFREE)||LA20_0==SORTS||(LA20_0>=ID && LA20_0<=RPAREN)||(LA20_0>=COMMA && LA20_0<=LBRACE)||LA20_0==IMP) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("107:3: ( LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ ) | -> sort )", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:107:5: LDOUBLEBRACKET symbol ( COMMA symbol )* RDOUBLEBRACKET
                    {
                    LDOUBLEBRACKET37=(Token)input.LT(1);
                    match(input,LDOUBLEBRACKET,FOLLOW_LDOUBLEBRACKET_in_parameterizedSort609); 
                    stream_LDOUBLEBRACKET.add(LDOUBLEBRACKET37);

                    pushFollow(FOLLOW_symbol_in_parameterizedSort611);
                    symbol38=symbol();
                    _fsp--;

                    stream_symbol.add(symbol38.getTree());
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:107:27: ( COMMA symbol )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==COMMA) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:107:28: COMMA symbol
                    	    {
                    	    COMMA39=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_parameterizedSort614); 
                    	    stream_COMMA.add(COMMA39);

                    	    pushFollow(FOLLOW_symbol_in_parameterizedSort616);
                    	    symbol40=symbol();
                    	    _fsp--;

                    	    stream_symbol.add(symbol40.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    RDOUBLEBRACKET41=(Token)input.LT(1);
                    match(input,RDOUBLEBRACKET,FOLLOW_RDOUBLEBRACKET_in_parameterizedSort621); 
                    stream_RDOUBLEBRACKET.add(RDOUBLEBRACKET41);


                    // AST REWRITE
                    // elements: symbol, sort
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 107:59: -> ^( PARAMETERIZEDSYMBOL sort ( symbol )+ )
                    {
                        // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:107:62: ^( PARAMETERIZEDSYMBOL sort ( symbol )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PARAMETERIZEDSYMBOL, "PARAMETERIZEDSYMBOL"), root_1);

                        adaptor.addChild(root_1, stream_sort.next());
                        if ( !(stream_symbol.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_symbol.hasNext() ) {
                            adaptor.addChild(root_1, stream_symbol.next());

                        }
                        stream_symbol.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:108:17: 
                    {

                    // AST REWRITE
                    // elements: sort
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 108:17: -> sort
                    {
                        adaptor.addChild(root_0, stream_sort.next());

                    }



                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end parameterizedSort

    public static class sort_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sort
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:112:1: sort : id= ID -> ^( SORT $id) ;
    public final sort_return sort() throws RecognitionException {
        sort_return retval = new sort_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token id=null;

        CommonTree id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:113:2: (id= ID -> ^( SORT $id) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:113:4: id= ID
            {
            id=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_sort659); 
            stream_ID.add(id);


            // AST REWRITE
            // elements: id
            // token labels: id
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 113:10: -> ^( SORT $id)
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:113:13: ^( SORT $id)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SORT, "SORT"), root_1);

                adaptor.addChild(root_1, stream_id.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sort

    public static class list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start list
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:117:1: list : LBRACE o= symbol s= STRING RBRACE (m= PLUS | m= STAR ) -> ^( LIST ^( ORIGIN $o) ^( SEPARATOR $s) ^( MULTIPLICITY $m) ) ;
    public final list_return list() throws RecognitionException {
        list_return retval = new list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token s=null;
        Token m=null;
        Token LBRACE42=null;
        Token RBRACE43=null;
        symbol_return o = null;


        CommonTree s_tree=null;
        CommonTree m_tree=null;
        CommonTree LBRACE42_tree=null;
        CommonTree RBRACE43_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleSubtreeStream stream_symbol=new RewriteRuleSubtreeStream(adaptor,"rule symbol");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:2: ( LBRACE o= symbol s= STRING RBRACE (m= PLUS | m= STAR ) -> ^( LIST ^( ORIGIN $o) ^( SEPARATOR $s) ^( MULTIPLICITY $m) ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:4: LBRACE o= symbol s= STRING RBRACE (m= PLUS | m= STAR )
            {
            LBRACE42=(Token)input.LT(1);
            match(input,LBRACE,FOLLOW_LBRACE_in_list680); 
            stream_LBRACE.add(LBRACE42);

            pushFollow(FOLLOW_symbol_in_list684);
            o=symbol();
            _fsp--;

            stream_symbol.add(o.getTree());
            s=(Token)input.LT(1);
            match(input,STRING,FOLLOW_STRING_in_list688); 
            stream_STRING.add(s);

            RBRACE43=(Token)input.LT(1);
            match(input,RBRACE,FOLLOW_RBRACE_in_list690); 
            stream_RBRACE.add(RBRACE43);

            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:36: (m= PLUS | m= STAR )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==PLUS) ) {
                alt21=1;
            }
            else if ( (LA21_0==STAR) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("118:36: (m= PLUS | m= STAR )", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:37: m= PLUS
                    {
                    m=(Token)input.LT(1);
                    match(input,PLUS,FOLLOW_PLUS_in_list695); 
                    stream_PLUS.add(m);


                    }
                    break;
                case 2 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:46: m= STAR
                    {
                    m=(Token)input.LT(1);
                    match(input,STAR,FOLLOW_STAR_in_list701); 
                    stream_STAR.add(m);


                    }
                    break;

            }


            // AST REWRITE
            // elements: o, m, s
            // token labels: s, m
            // rule labels: retval, o
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
            RewriteRuleTokenStream stream_m=new RewriteRuleTokenStream(adaptor,"token m",m);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_o=new RewriteRuleSubtreeStream(adaptor,"token o",o!=null?o.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 118:54: -> ^( LIST ^( ORIGIN $o) ^( SEPARATOR $s) ^( MULTIPLICITY $m) )
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:57: ^( LIST ^( ORIGIN $o) ^( SEPARATOR $s) ^( MULTIPLICITY $m) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_1);

                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:64: ^( ORIGIN $o)
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ORIGIN, "ORIGIN"), root_2);

                adaptor.addChild(root_2, stream_o.next());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:77: ^( SEPARATOR $s)
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(SEPARATOR, "SEPARATOR"), root_2);

                adaptor.addChild(root_2, stream_s.next());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:118:93: ^( MULTIPLICITY $m)
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(MULTIPLICITY, "MULTIPLICITY"), root_2);

                adaptor.addChild(root_2, stream_m.next());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end list

    public static class attribute_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attribute
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:122:1: attribute : id= ID -> ^( ATTRIBUTE $id) ;
    public final attribute_return attribute() throws RecognitionException {
        attribute_return retval = new attribute_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token id=null;

        CommonTree id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:123:2: (id= ID -> ^( ATTRIBUTE $id) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:123:4: id= ID
            {
            id=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_attribute743); 
            stream_ID.add(id);


            // AST REWRITE
            // elements: id
            // token labels: id
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 123:10: -> ^( ATTRIBUTE $id)
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:123:13: ^( ATTRIBUTE $id)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                adaptor.addChild(root_1, stream_id.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end attribute

    public static class production_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start production
    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:126:1: production : (l+= symbol )* IMP r= symbol ( LBRACE a= attribute RBRACE )? -> ^( PRODUCTION ^( LEFT ( $l)* ) ^( RIGHT $r) ) ;
    public final production_return production() throws RecognitionException {
        production_return retval = new production_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IMP44=null;
        Token LBRACE45=null;
        Token RBRACE46=null;
        List list_l=null;
        symbol_return r = null;

        attribute_return a = null;

        RuleReturnScope l = null;
        CommonTree IMP44_tree=null;
        CommonTree LBRACE45_tree=null;
        CommonTree RBRACE46_tree=null;
        RewriteRuleTokenStream stream_IMP=new RewriteRuleTokenStream(adaptor,"token IMP");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_symbol=new RewriteRuleSubtreeStream(adaptor,"rule symbol");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:2: ( (l+= symbol )* IMP r= symbol ( LBRACE a= attribute RBRACE )? -> ^( PRODUCTION ^( LEFT ( $l)* ) ^( RIGHT $r) ) )
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:4: (l+= symbol )* IMP r= symbol ( LBRACE a= attribute RBRACE )?
            {
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:5: (l+= symbol )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==ID||(LA22_0>=STRING && LA22_0<=LPAREN)||LA22_0==LBRACE) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:5: l+= symbol
            	    {
            	    pushFollow(FOLLOW_symbol_in_production766);
            	    l=symbol();
            	    _fsp--;

            	    stream_symbol.add(l.getTree());
            	    if (list_l==null) list_l=new ArrayList();
            	    list_l.add(l);


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            IMP44=(Token)input.LT(1);
            match(input,IMP,FOLLOW_IMP_in_production769); 
            stream_IMP.add(IMP44);

            pushFollow(FOLLOW_symbol_in_production773);
            r=symbol();
            _fsp--;

            stream_symbol.add(r.getTree());
            // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:28: ( LBRACE a= attribute RBRACE )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==LBRACE) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==ID) ) {
                    int LA23_3 = input.LA(3);

                    if ( (LA23_3==RBRACE) ) {
                        alt23=1;
                    }
                }
            }
            switch (alt23) {
                case 1 :
                    // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:29: LBRACE a= attribute RBRACE
                    {
                    LBRACE45=(Token)input.LT(1);
                    match(input,LBRACE,FOLLOW_LBRACE_in_production776); 
                    stream_LBRACE.add(LBRACE45);

                    pushFollow(FOLLOW_attribute_in_production780);
                    a=attribute();
                    _fsp--;

                    stream_attribute.add(a.getTree());
                    RBRACE46=(Token)input.LT(1);
                    match(input,RBRACE,FOLLOW_RBRACE_in_production782); 
                    stream_RBRACE.add(RBRACE46);


                    }
                    break;

            }


            // AST REWRITE
            // elements: l, r
            // token labels: 
            // rule labels: retval, r
            // token list labels: 
            // rule list labels: l
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_r=new RewriteRuleSubtreeStream(adaptor,"token r",r!=null?r.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"token l",list_l);
            root_0 = (CommonTree)adaptor.nil();
            // 127:57: -> ^( PRODUCTION ^( LEFT ( $l)* ) ^( RIGHT $r) )
            {
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:60: ^( PRODUCTION ^( LEFT ( $l)* ) ^( RIGHT $r) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PRODUCTION, "PRODUCTION"), root_1);

                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:73: ^( LEFT ( $l)* )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LEFT, "LEFT"), root_2);

                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:80: ( $l)*
                while ( stream_l.hasNext() ) {
                    adaptor.addChild(root_2, ((ParserRuleReturnScope)stream_l.next()).getTree());

                }
                stream_l.reset();

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Documents\\Serge\\JavaProjects\\Unifier\\src\\unifier\\sdfparser\\Sdf.g:127:85: ^( RIGHT $r)
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(RIGHT, "RIGHT"), root_2);

                adaptor.addChild(root_2, stream_r.next());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end production


 

    public static final BitSet FOLLOW_MODULE_in_module256 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_moduleName_in_module259 = new BitSet(new long[]{0x00000000000001C0L});
    public static final BitSet FOLLOW_impSection_in_module261 = new BitSet(new long[]{0x00000000000001C0L});
    public static final BitSet FOLLOW_section_in_module264 = new BitSet(new long[]{0x0000000000000180L});
    public static final BitSet FOLLOW_EOF_in_module267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_moduleId_in_moduleName280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_moduleWord_in_moduleId302 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_moduleId305 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_moduleWord_in_moduleId308 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ID_in_moduleWord321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTS_in_impSection333 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_imports_in_impSection336 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_moduleName_in_imports348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORTS_in_section362 = new BitSet(new long[]{0x0000000000002200L});
    public static final BitSet FOLLOW_HIDDENS_in_section367 = new BitSet(new long[]{0x0000000000002200L});
    public static final BitSet FOLLOW_sdfGrammar_in_section371 = new BitSet(new long[]{0x0000000000002202L});
    public static final BitSet FOLLOW_CONTEXTFREE_in_sdfGrammar383 = new BitSet(new long[]{0x0000000000001C00L});
    public static final BitSet FOLLOW_SYNTAX_in_sdfGrammar387 = new BitSet(new long[]{0x00000A1840000002L});
    public static final BitSet FOLLOW_production_in_sdfGrammar390 = new BitSet(new long[]{0x00000A1840000002L});
    public static final BitSet FOLLOW_PRIORITIES_in_sdfGrammar395 = new BitSet(new long[]{0x00000A1840000002L});
    public static final BitSet FOLLOW_priority_in_sdfGrammar398 = new BitSet(new long[]{0x00000A1840000002L});
    public static final BitSet FOLLOW_STARTSYMBOLS_in_sdfGrammar403 = new BitSet(new long[]{0x0000021840000002L});
    public static final BitSet FOLLOW_symbol_in_sdfGrammar406 = new BitSet(new long[]{0x0000021840000002L});
    public static final BitSet FOLLOW_SORTS_in_sdfGrammar413 = new BitSet(new long[]{0x0000021840000002L});
    public static final BitSet FOLLOW_symbol_in_sdfGrammar416 = new BitSet(new long[]{0x0000021840000002L});
    public static final BitSet FOLLOW_group_in_priority429 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_GREATER_in_priority432 = new BitSet(new long[]{0x00000A1840000000L});
    public static final BitSet FOLLOW_group_in_priority434 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_production_in_group458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_markedSymbol_in_symbol478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_symbol483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonMarkedSymbol_in_markedSymbol496 = new BitSet(new long[]{0x0000000700000002L});
    public static final BitSet FOLLOW_STAR_in_markedSymbol506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_markedSymbol512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_markedSymbol518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterizedSort_in_nonMarkedSymbol559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_nonMarkedSymbol565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nonMarkedSymbol575 = new BitSet(new long[]{0x0000023840000000L});
    public static final BitSet FOLLOW_symbol_in_nonMarkedSymbol577 = new BitSet(new long[]{0x0000023840000000L});
    public static final BitSet FOLLOW_RPAREN_in_nonMarkedSymbol580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sort_in_parameterizedSort602 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_LDOUBLEBRACKET_in_parameterizedSort609 = new BitSet(new long[]{0x0000021840000000L});
    public static final BitSet FOLLOW_symbol_in_parameterizedSort611 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_COMMA_in_parameterizedSort614 = new BitSet(new long[]{0x0000021840000000L});
    public static final BitSet FOLLOW_symbol_in_parameterizedSort616 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_RDOUBLEBRACKET_in_parameterizedSort621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_sort659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_list680 = new BitSet(new long[]{0x0000021840000000L});
    public static final BitSet FOLLOW_symbol_in_list684 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_STRING_in_list688 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RBRACE_in_list690 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_PLUS_in_list695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_list701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attribute743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_symbol_in_production766 = new BitSet(new long[]{0x00000A1840000000L});
    public static final BitSet FOLLOW_IMP_in_production769 = new BitSet(new long[]{0x0000021840000000L});
    public static final BitSet FOLLOW_symbol_in_production773 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_LBRACE_in_production776 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_attribute_in_production780 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RBRACE_in_production782 = new BitSet(new long[]{0x0000000000000002L});

}