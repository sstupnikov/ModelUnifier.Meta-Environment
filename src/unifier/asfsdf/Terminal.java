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
public class Terminal extends Sort {

    public Terminal(){}

    public Terminal(String name){
        this.name = name;
    }


    public boolean isTerminal() {
        return true;
    }

    public boolean isListSort() {
        return false;
    }

    public boolean isEnumerationSort() {
        return false;
    }


    public boolean isAlternativeSort() throws SdfDefinitionException {
        return false;
    }

    public boolean isDeclarationSort() throws SdfDefinitionException {
        return false;
    }


    public Terminal setName(String name){
        this.name = name;
        return this;
    }

    public String toTerm(){
        return this.name;
    }

    public Map<Variable, Integer> getVariableInstances(){
        Map<Variable,Integer> map = new HashMap<Variable,Integer>();
        map.put(new Variable(name, new Declaration(new SortName(name))), 1);

        return map;
    }


}
