package unifier.asfsdf;

import java.util.Map;
import java.util.HashMap;

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
public class Nonterminal extends Sort {
    private AbstractSortDef definition = null;

    public boolean isTerminal() {
        return false;
    }

    public boolean isListSort() throws SdfDefinitionException {
        if(definition != null){
            return definition.isList();
        } else{
            throw new SdfDefinitionException("Definition of nonterminal sort " + this.name + " is null.");
        }
    }

    public boolean isEnumerationSort() throws SdfDefinitionException {
        if(definition != null){
            return definition.isEnumeration();
        } else{
            throw new SdfDefinitionException("Definition of nonterminal sort " + this.name + " is null.");
        }
    }

    public boolean isAlternativeSort() throws SdfDefinitionException {
        if(definition != null){
            return definition.isAlternative();
        } else{
            throw new SdfDefinitionException("Definition of nonterminal sort " + this.name + " is null.");
        }
    }


    public boolean isDeclarationSort() throws SdfDefinitionException {
        if(definition != null){
            return definition.isDeclaration();
        } else{
            throw new SdfDefinitionException("Definition of nonterminal sort " + this.name + " is null.");
        }
    }


    public Nonterminal setName(String name){
        this.name = name;
        return this;
    }


    public AbstractSortDef getDefinition() {
        return definition;
    }

    public Nonterminal setDefinition(AbstractSortDef def) {
        definition = def;
        return this;
    }

    public String toTerm() throws SdfDefinitionException {
        String result = "";

        if(definition != null){
            if(definition.isAlternative() || definition.isEnumeration()){
                result = name;
            } else
            if(definition.isDeclaration()){
                result = ((Declaration)definition).toTerm();
            }
        }

        return result;
    }

    public Map<Variable, Integer> getVariableInstances() throws SdfDefinitionException {
        Map<Variable,Integer> map = null;


        if(definition != null){
            if(definition.isAlternative() || definition.isEnumeration()){
                map = new HashMap<Variable,Integer>();
                map.put(new Variable(name, new Declaration(new SortName(name))), 1);
            } else
            if(definition.isDeclaration()){
                 map = ((Declaration)definition).getVariableInstances();
            }
        }

        return map;
    }



}
