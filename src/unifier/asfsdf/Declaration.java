package unifier.asfsdf;

import java.util.List;
import java.util.ArrayList;
import unifier.util.StringHelper;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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

public class Declaration extends AbstractSortDef{
    private List<DeclarationElement> elements = new ArrayList<DeclarationElement>();

    public Declaration(){};

    public Declaration(DeclarationElement elm){
        elements.add(elm);
    }

    public List<DeclarationElement> getElements(){
        return elements;
    }

    public Declaration addElement(DeclarationElement elm){
        elements.add(elm);
        return this;
    }

    public Declaration addElements(List<DeclarationElement> elms){
        elements.addAll(elms);
        return this;
    }


    public boolean isDeclaration(){
        return true;
    }

    public boolean isAlternative(){
        return false;
    }

    public boolean isEnumeration(){
        return false;
    }

    // The following declarations are simple:
    //
    // SORT
    // LITERAL
    // SORT_NAME*
    // SORT_NAME+
    // {SORT_NAME sep}*
    // {SORT_NAME sep}+
    // SORT_NAME?
    // LITERAL?
    public boolean isSimple(){
        if(elements.size() == 1 && elements.iterator().next().isSimple())  return true;
        else return false;
    }

    public String getNameIfSimple() throws SdfDefinitionException {
        if(this.isSimple()){
            return elements.iterator().next().getNameIfSimple();
        } else{
            throw new SdfDefinitionException("Tried to get name of non-simple declaration.");
        }
    }

    // SORT
    public boolean consistsOfOneSortName(){
        if(elements.size() == 1 && elements.iterator().next().isSortName())  return true;
        else return false;
    }

    // LITERAL
    public boolean consistsOfOneLiteral(){
        if(elements.size() == 1 && elements.iterator().next().isLiteral())  return true;
        else return false;
    }

    public boolean consistsOfOneSimpleOptional(){
        DeclarationElement elm = elements.iterator().next();

        if(elements.size() == 1 && elm.isDerivedSort() &&
            ((DerivedSort)elm).isOptional() &&
            ((Optional)elm).originalSortDeclConsistsOfOneSortName()
          )
            return true;
        else
            return false;
    }

    public boolean isList(){
        if(elements.size() == 1 && elements.iterator().next().isList())
            return true;
        else return false;

    }

    public String getSignatureName() throws SdfDefinitionException {
        if(isSimple()){
            return "create-" + this.getVariableName();
        } else
            throw new SdfDefinitionException("Signature name can not be provided for non-simple declarations.");
    }


    public String getVariableName() throws SdfDefinitionException {
        if(isSimple()){
            return elements.iterator().next().getVariableName();
        } else
            throw new SdfDefinitionException("Variable name can not be provided for non-simple declarations.");
    }

    // Part related to term representation of Declaration
    // for using in ASF-equations

    boolean termRepresentationGenerated = false;
    private String termRepr = "";
    // Variable is related with number of occurences of its sort name
    // in the declaration (for every occurence variable instance is created)
    private Map<Variable, Integer> variableInstances;


    public String toTerm() throws SdfDefinitionException {
        if(!termRepresentationGenerated){
            variableInstances = new HashMap<Variable, Integer>();
            generateTermRepresentation();
        }
        return termRepr;
    }

    public Map<Variable, Integer> getVariableInstances() throws SdfDefinitionException {
        if(!termRepresentationGenerated) generateTermRepresentation();
        return variableInstances;
    }

    private void generateTermRepresentation() throws SdfDefinitionException {
        generateTermRepresentation(elements);
    }

    private void generateTermRepresentation(List<DeclarationElement> elms) throws SdfDefinitionException {

        for (DeclarationElement elm : elms) {
            if(elm.isSimple()){
                Variable var;
                int n;

                termRepr = termRepr + elm.getVariableName();
                if(!elm.isLiteral()){
                    var = new Variable(new Declaration(elm));
                    addVariableInstance(var);
                    n = variableInstances.get(var).intValue();
                    if(n > 0) termRepr = termRepr + n;
                }
                termRepr = termRepr + " ";

            } else
            if(elm.isDerivedSort() &&
               ((DerivedSort)elm).isOptional() &&
               ((DerivedSort)elm).originalSortIsDeclaration()){
                generateTermRepresentation(((DerivedSort)elm).getOriginalSort().getElements());
            } else
                throw new SdfDefinitionException("String representation generation for that kind of declaration is not supported.");
        }

        termRepresentationGenerated = true;
    }

    private void addVariableInstance(Variable var){
        int numberOfInstances;

        if (variableInstances.containsKey(var)) {
            numberOfInstances = variableInstances.get(var).intValue();
            numberOfInstances++;

            variableInstances.remove(var);
            variableInstances.put(var, new Integer(numberOfInstances));
        } else {
            variableInstances.put(var, new Integer(0));
        }

    }
}
