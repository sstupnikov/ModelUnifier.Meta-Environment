package unifier.asfsdf;

import java.util.Map;

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
public abstract class Sort extends Named{

    public abstract boolean isTerminal();
    public abstract boolean isListSort() throws SdfDefinitionException;
    public abstract boolean isEnumerationSort() throws SdfDefinitionException;
    public abstract boolean isAlternativeSort() throws SdfDefinitionException;
    public abstract boolean isDeclarationSort() throws SdfDefinitionException;

    public abstract String toTerm() throws SdfDefinitionException;
    public abstract Map<Variable, Integer> getVariableInstances() throws SdfDefinitionException;

    public String getSignatureName(){
        return "create-" + name;
    }
}
