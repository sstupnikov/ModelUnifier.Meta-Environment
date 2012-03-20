package unifier.asfsdf;

import unifier.util.FormattingWriter;
import unifier.util.StringHelper;
import unifier.asfsdf.SdfDefinitionException;
import java.util.logging.Logger;

/**
 * <p>Title: Unifier</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: IPI RAS</p>
 *
 * @author Sergey A. Stupnikov
 * @version 1.0
 */
public class AsfSsdfFormattingWriter {
    Logger logger = Logger.getLogger("unifier.AsfSsdfFormattingWriter");

    private FormattingWriter writer;

    public AsfSsdfFormattingWriter(FormattingWriter writer){
        this.writer = writer;
    }

    private void write(String text){
        writer.write(text);
    }

    private void ln(){
        writer.ln();
    }

    private void ln(int i){
        writer.ln(i);
    }

    private void indent(){
        writer.indent();
    }

    private void flush(){
        writer.flush();
    }

    public void translatorHeader(Module module) throws SdfDefinitionException {
        if (module == null){
            throw new SdfDefinitionException("SDF module is null.");
        }

        String moduleName = module.getName();

        if(!StringHelper.isNullEmptyOrBlank(moduleName)){
            write(module.getName()); ln(); ln();
        } else {
            throw new SdfDefinitionException("SDF module has null, empty or blank name.");
        }


        for(String s: module.getImports()){
            if(!StringHelper.isNullEmptyOrBlank(s)){
                write("imports " + s); ln();
            } else {
                throw new SdfDefinitionException("SDF module has null, empty or blank import element.");
            }
        }
        ln(); ln();

        write("exports"); ln(); ln();

        if(module.getStartSymbols().size() != 0){
            write("context-free start-symbols"); ln(+1);
            for(String s: module.getStartSymbols()){
                if(!StringHelper.isNullEmptyOrBlank(s)){
                    write(s); ln();
                } else {
                    throw new SdfDefinitionException("SDF module has null, empty or blank start-symbol.");
                }
            }
        }
        ln(-1);

        if(module.getSortNames().size() != 0){
            write("sorts"); ln(+1);
            for(String s: module.getSortNames()){
                if(!StringHelper.isNullEmptyOrBlank(s)){
                    write(s); ln();
                } else {
                    throw new SdfDefinitionException("SDF module has null, empty or blank sort.");
                }
            }
            ln(-1);
        }

        if(module.getSortNames().size() != 0){
            write("context-free syntax"); ln(+1); ln();
            for(String s: module.getSortNames()){
                if(!module.getSort(s).isTerminal()){
                    //logger.info("Definition of sort " + s);
                    abstractSortDef(((Nonterminal)module.getSort(s)).getDefinition());
                    write(" -> " + s); ln();
                }
            }
            ln(-1);
        }


        if(module.getSignatureNames().size() != 0){
            write("context-free syntax"); ln(+1); ln();
            for (String sigName: module.getSignatureNames()) {
                signature(module.getSignature(sigName)); ln();
            }
            ln(-1);
        }

        if(module.getDomainSortVariables().size() != 0){
            //logger.info("DOMAIN VARS " + module.getDomainSortVariables().size());
            write("variables"); ln(+1);
            for(Variable var: module.getDomainSortVariables()){
                variable(var);  ln();
            }
            ln(-1);
        }

        if(module.getRangeSortVariables().size() != 0){
            //logger.info("RANGE VARS " + module.getRangeSortVariables().size());
            write("variables"); ln(+1);
            for(Variable var: module.getRangeSortVariables()){
                variable(var);  ln();
            }
            ln(-1);
        }

        flush();
    }

    public void signature(Signature s) throws SdfDefinitionException {
        if(s == null){
            throw new SdfDefinitionException("Signature is null.");
        }

        int i = 0;
        int size = s.getParams().size();

        write(s.getName() + "(");
        for (Declaration d : s.getParams()) {
            declaration(d);
            i++;
            if (i < size) {
                write(", ");
            }
        }
        write(") -> ");
        declaration(s.getReturnSort());

        flush();
    }

    private void declaration(Declaration d) throws SdfDefinitionException {
        if(d == null){
          throw new SdfDefinitionException("Declaration is null.");
        }

        int i = 0;
        int size = d.getElements().size();
        for (DeclarationElement e: d.getElements()) {
            declarationElement(e);
            i++;
            if (i < size) {
                write(" ");
            }
        }
    }

    private void declarationElement(DeclarationElement e) throws  SdfDefinitionException {
        if(e == null){
            throw new SdfDefinitionException("DeclarationElement is null.");
        }

        if(e.isLiteral()){
            literal((Literal)e);
        }
        else if (e.isSortName()){
            sortName((SortName)e);
        }
        else if (e.isDerivedSort()){
            derivedSort((DerivedSort)e);
        }
    }

    private void literal(Literal lit) throws SdfDefinitionException {
        if(lit == null || StringHelper.isNullEmptyOrBlank(lit.getValue())){
            throw new SdfDefinitionException("Literal is null, empty or blank.");
        }

        write(lit.getValue());
    }

    private void sortName(SortName sort) throws SdfDefinitionException {
        if(sort == null || StringHelper.isNullEmptyOrBlank(sort.getValue())){
            throw new SdfDefinitionException("SortName is null, empty or blank.");
        }

        write(sort.getValue());
    }

    private void derivedSort(DerivedSort sort) throws SdfDefinitionException {
        if(sort == null){
            throw new SdfDefinitionException("DerivedSort is null.");
        }

        if (sort.getOriginalSort() == null){
            throw new SdfDefinitionException("Origin of derived sort is null.");
        }

        if(sort.isOptional()){
            optional((Optional)sort);
        }
        else if (sort.isList()){
            list((List)sort);
        }
    }

    private void optional(Optional opt) throws SdfDefinitionException {
        write("(");
        abstractSortDef(opt.getOriginalSort());
        write(")?");
    }

    private void list(List lst) throws SdfDefinitionException {
        if(lst.getMultiplicity() == null){
            throw new SdfDefinitionException("Multiplicity of list sort is null.");
        }

        if(!lst.separatorIsEmpty()){
            write("{");
            abstractSortDef(lst.getOriginalSort());
            write(" " + StringHelper.toQuotation(lst.getSeparator()));
            write("}");
        } else {
            write("(");
            abstractSortDef(lst.getOriginalSort());
            write(")");
        }

        write(lst.getMultiplicityToString());
    }

    private void abstractSortDef(AbstractSortDef sort) throws SdfDefinitionException {
        if(sort == null){
            throw new SdfDefinitionException("AbstractSortDef is null.");
        }

        if(sort.isAlternative()) alternative((Alternative)sort);
        else if(sort.isEnumeration()) enumeration((Enumeration)sort);
        else if(sort.isDeclaration()) declaration((Declaration)sort);
    }

    private void alternative(Alternative alt) throws SdfDefinitionException {
        if(alt == null){
            throw new SdfDefinitionException("Alternative is null.");
        }
        if(alt.getElements().size() == 0){
            throw new SdfDefinitionException("Alternative is empty.");
        }

        int i = 0;
        int size = alt.getElements().size();

        write("(");
        for (Object sort: alt.getElements()) {
            abstractSortDef((AbstractSortDef)sort);
            i++;
            if (i < size) {
                write(" | ");
            }
        }
        write(")");
    }

    private void enumeration(Enumeration enm) throws SdfDefinitionException {
        if(enm == null){
            throw new SdfDefinitionException("Enumeration is null.");
        }
        if(enm.getElements().size() == 0){
            throw new SdfDefinitionException("Enumeration is empty.");
        }

        int i = 0;
        int size = enm.getElements().size();

        write("(");
        for (Object s: enm.getElements()) {
            write(StringHelper.toQuotation((String)s));
            i++;
            if (i < size) {
                write(" | ");
            }
        }
        write(")");
    }

    private void variable(Variable var) throws SdfDefinitionException {
        if(var == null){
            throw new SdfDefinitionException("Variable is null.");
        }

        if(StringHelper.isNullEmptyOrBlank(var.getName())){
            throw new SdfDefinitionException("Variable name is null, blank or empty.");
        }

        write(StringHelper.toQuotation(var.getName()) + "[0-9]* -> ");
        declaration(var.getDeclaration());
    }



    public void equations(Module module) throws SdfDefinitionException {
        if (module == null){
            throw new SdfDefinitionException("SDF module is null.");
        }
        if (module.getEquations().size() == 0) return;


        write("equations"); ln(); ln();

        for(Equation eq: module.getEquations()){
            equation(eq); ln(); ln();
        }

        flush();
    }

    private void equation(Equation eq) throws SdfDefinitionException {
        if (eq == null){
            throw new SdfDefinitionException("Equation is null.");
        }
        if (StringHelper.isNullEmptyOrBlank(eq.getTag())){
            throw new SdfDefinitionException("Equation name is null, empty or blank.");
        }
        if (StringHelper.isNullEmptyOrBlank(eq.getDefinedFunctionName())){
            throw new SdfDefinitionException("Equation defined function name is null, empty or blank.");
        }

        int i = 0;
        int size = eq.getConditions().size();

        write("[" + eq.getTag() + "]"); ln();

        if(size != 0){
            for(Condition cond: eq.getConditions()){
                condition(cond);
                i++;
                if(i < size){
                    write(","); ln();
                } else {
                    ln();
                }
            }
            write("====>"); ln();
        }

        write(eq.getDefinedFunctionName() + "(" + eq.getParams() + ") ="); ln();
        write(eq.getBody());

    }

    private void condition(Condition cond) throws SdfDefinitionException {
        if (cond == null){
            throw new SdfDefinitionException("Condition is null.");
        }
        if (StringHelper.isNullEmptyOrBlank(cond.getLeft())){
            throw new SdfDefinitionException("Condition left part is null, empty or blank.");
        }
        if (StringHelper.isNullEmptyOrBlank(cond.getRight())){
            throw new SdfDefinitionException("Condition reght part is null, empty or blank.");
        }
        if (cond.getKind() == null){
            throw new SdfDefinitionException("Condition kind is null.");
        }

        write(cond.getLeft() + " " + cond.getKind().toString() + " " + cond.getRight());
    }
}



