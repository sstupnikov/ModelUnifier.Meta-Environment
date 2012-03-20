package unifier.asfsdf;

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
public class Variable extends Named {
    private Declaration sort;

    public Variable (String name, Declaration sort){
        this.name = name;
        this.sort = sort;
    }

    public Variable (Declaration sort) throws SdfDefinitionException {
        this.sort = sort;

        if(sort.isSimple()) this.name = sort.getVariableName();
        else throw new SdfDefinitionException("Variable can not be created: sort declaration is not simple.");
    }

    public Declaration getDeclaration(){
        return sort;
    }

    public Variable setDeclaration(Declaration sort){
        this.sort = sort;
        return this;
    }

    public Variable setName(String name){
        this.name = name;
        return this;
    }

    // equality of variables is considered as equality of their names
    public boolean equals(Object obj){
        if(!(obj instanceof Variable))
            return false;
        else
            return (this.name.compareTo(((Variable)obj).getName()) == 0);
    }

    public int hashCode(){
        return name.hashCode();
    }
}
