package unifier.asfsdf;

import org.antlr.runtime.tree.Tree;
import unifier.sdfparser.SdfLexer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.io.Reader;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRReaderStream;
import unifier.sdfparser.SdfParser;
import java.io.IOException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 * <p>Title: Unifier</p>
 *
 * <p>Description: Consumes a tree of type MOULE produced by Antlr 3.0 grammar Sdf.g</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: IPI RAS</p>
 *
 * @author Sergey A. Stupnikov
 * @version 1.0
 */
public class SdfCreator {

    Logger logger = Logger.getLogger("unifier.SdfCreator");

    Module resultModule;
    Set<String> terminalSortNames;

    public SdfCreator(){
        resultModule = new Module();
        terminalSortNames = new HashSet<String>();
    }

    // Parse input from reader, put it into tree, put tree into NEW module,
    // return sort names.
    public Set<String> getSortNames(Reader reader) throws IOException,
            RecognitionException, SdfDefinitionException {
        Set<String> result = new HashSet<String>();

        SdfLexer lex = new SdfLexer(new ANTLRReaderStream(reader));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        SdfParser g = new SdfParser(tokens);
        SdfParser.module_return r = g.module();
        CommonTree tree = (CommonTree)r.getTree();

        resultModule = new Module();
        module(tree);

        result.addAll(resultModule.getSortNames());

        return result;
    }


    public Module processSdf(Reader reader, Set<String> terminalSortNames)
            throws IOException, RecognitionException, SdfDefinitionException {
        SdfLexer lex = new SdfLexer(new ANTLRReaderStream(reader));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        SdfParser g = new SdfParser(tokens);
        SdfParser.module_return r = g.module();
        CommonTree tree = (CommonTree)r.getTree();

        // if(tree == null) System.out.println("Tree is null.");
        // logger.info(tree.toStringTree());

        if(terminalSortNames != null)
            this.terminalSortNames.addAll(terminalSortNames);

        module(tree);

        return resultModule;
    }


    private void module(Tree tree) throws
            SdfDefinitionException {
        if(tree.isNil()) return;

        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.MODULENAME:
                resultModule.setName(moduleName(child));
                break;
            case SdfLexer.IMPORTS:
                imports(child);
                break;
            case SdfLexer.EXPORTS:
                exports(child);
                break;
            }
        }
    }

    private String moduleName(Tree tree){
        String result = "";

        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.ID:
                if(i != tree.getChildCount()-1) result = result + child.getText() + "/";
               else result = result + child.getText();
                break;
            }
        }

        return result;
    }

    private void imports(Tree tree){
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.MODULENAME:
                resultModule.addImport(moduleName(child));
                break;
            }
        }
    }

    private void exports(Tree tree) throws SdfDefinitionException {
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.STARTSYMBOLS:
                startSymbols(child);
                break;
            case SdfLexer.SORTS:
                sorts(child);
                break;
            case SdfLexer.SYNTAX:
                syntax(child);
                break;
            }
        }
    }

    private void startSymbols(Tree tree) throws SdfDefinitionException {
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.SORT:
                resultModule.addStartSymbol(sort(child));
                break;
            }
        }
    }

    private String sort(Tree tree){
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.ID:
                return child.getText();
            }
        }

        return null;
    }

    private void sorts(Tree tree) throws SdfDefinitionException {
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.SORT:
                Sort sort = null;
                String sortName = sort(child);

                if(this.terminalSortNames.contains(sortName)) sort = (new Terminal()).setName(sortName);
                else sort = (new Nonterminal()).setName(sortName);

                resultModule.addSort(sort);
                break;
            }
        }
    }

    private void syntax(Tree tree) throws SdfDefinitionException {
        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.PRODUCTION:
                production(child);
                break;
            }
        }
    }

    private void production(Tree tree) throws SdfDefinitionException {
        Tree right;
        Tree left;
        Nonterminal sort;
        String sortName;
        String enumElm;
        AbstractSortDef definition;

        // Appropriate production have to have left and right part
        if (tree.getChildCount() < 2 ||
            tree.getChild(0).getType() != SdfLexer.LEFT ||
            tree.getChild(1).getType() != SdfLexer.RIGHT
           )
            return;

        left = tree.getChild(0);
        right = tree.getChild(1);

        // Right part of production have to be simple sort
        if(right.getChildCount() != 1 || right.getChild(0).getType() != SdfLexer.SORT) return;
        // Terminal sorts productions are not to be processed
        sortName = sort(right.getChild(0));
        if(terminalSortNames.contains(sortName)) return;

        //logger.info("Production of sort " + sortName);

        sort = (Nonterminal)resultModule.getSort(sortName);
        // Sort may not be defined in 'sorts' section but in some imported grammar.
        // In that case sort have to be created here.
        if(sort == null){
            sort = new Nonterminal();
            sort.setName(sortName);
            resultModule.addSort(sort);
        }

        definition = sort.getDefinition();
        enumElm = getEnumeration(left);

        // Possible alternatives:
        // I. <left> may be
        //    a) enum-like (sequence of strings)
        //    b) non-enum-like
        // II. <definition> may be
        //    a) null
        //    b) Enumeration
        //    c) Alternative
        //    d) Declaration

        // Ia IIa
        if (enumElm != null && definition == null) {
            definition = new Enumeration();
            sort.setDefinition(definition);
            ((Enumeration) definition).addElement(enumElm);
        } else
        // Ia IIb
        if (enumElm != null && definition.isEnumeration()) {
            ((Enumeration) definition).addElement(enumElm);
        } else
        // Ib IIa
        if(enumElm == null && definition == null){
            sort.setDefinition(symbols(left));
        } else
        // Ib IIb
        if(enumElm == null && definition.isEnumeration()){
            Alternative alternative = new Alternative();
            sort.setDefinition(alternative);

            alternative.addElement(symbols(left));
            // remove all elements of Enumeration to Alternative elements
            for(String s: ((Enumeration)definition).getElements()){
                alternative.addElement((new Declaration()).addElement(new Literal(s)));
            }
        } else
        // Iab II\u0441
        if(definition.isAlternative()){
            ((Alternative)definition).addElement(symbols(left));
        } else
        // Iab Iid
        if(definition.isDeclaration()){
            Alternative alternative = new Alternative();
            sort.setDefinition(alternative);

            alternative.addElement(definition);
            alternative.addElement(symbols(left));
        }

    }

    // If subtree contains only strings s1 ... sn, returns string s1_..._sn,
    // else returns null
    private String getEnumeration(Tree tree){
        String result = "";

        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.STRING:
                if(i != tree.getChildCount()-1) result = result + child.getText() + "_";
                else result = result + child.getText();
                break;
            default:
                return null;
            }
        }

        return result;
    }

    // Children of <tree> are symbols
    private Declaration symbols(Tree tree){
        Declaration result = new Declaration();

        for(int i = 0; i < tree.getChildCount(); i++){
            Tree child = tree.getChild(i);

            switch (child.getType()) {
            case SdfLexer.STRING:
                result.addElement(new Literal(child.getText()));
                break;
            case SdfLexer.SORT:
                result.addElement(new SortName(sort(child)));
                break;
            case SdfLexer.LIST:
                result.addElement(list(child));
                break;
            case SdfLexer.MARKEDSYMBOL:
                result.addElement(markedSymbol(child));
                break;
            case SdfLexer.COMPOUNDSYMBOL:
                result.addElements(symbols(child).getElements());
                break;
            }
        }

        return result;
    }

    private Declaration symbol(Tree tree){
        Declaration result = new Declaration();

        switch (tree.getType()) {
        case SdfLexer.STRING:
            result.addElement(new Literal(tree.getText()));
            break;
        case SdfLexer.SORT:
            result.addElement(new SortName(sort(tree)));
            break;
        case SdfLexer.LIST:
            result.addElement(list(tree));
            break;
        case SdfLexer.MARKEDSYMBOL:
            result.addElement(markedSymbol(tree));
            break;
        case SdfLexer.COMPOUNDSYMBOL:
            result.addElements(symbols(tree).getElements());
            break;
        }

        return result;
    }


    private List list(Tree tree){
        List result = new List();

        if(tree.getChildCount() != 3) return null;

        // First child of list is symbol (original sort)
        result.setOriginalSort(symbol(tree.getChild(0).getChild(0)));
        // Second child is separator
        result.setSeparator(tree.getChild(1).getChild(0).getText());
        // Third child is multiplicity
        switch(tree.getChild(2).getChild(0).getType()){
        case SdfLexer.PLUS:
            result.setMultiplicity(ListMultiplicity.AT_LEAST_ONE_TIME);
            break;
        case SdfLexer.STAR:
            result.setMultiplicity(ListMultiplicity.AT_LEAST_ZERO_TIME);
            break;
        }

        return result;
    }

    private DerivedSort markedSymbol(Tree tree){
        DerivedSort result = null;

        if(tree.getChildCount() != 2) return null;

        // Second child is mark (STAR, PLUS, QUESTION)
        switch(tree.getChild(1).getType()){
        case SdfLexer.PLUS:
            result = new List();
            ((List)result).setMultiplicity(ListMultiplicity.AT_LEAST_ONE_TIME);
            break;
        case SdfLexer.STAR:
            result = new List();
            ((List)result).setMultiplicity(ListMultiplicity.AT_LEAST_ZERO_TIME);
            break;
        case SdfLexer.QUESTION:
            result = new Optional();
            break;
        }

        result.setOriginalSort(symbol(tree.getChild(0)));

        return result;
    }

}
