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
public abstract class DerivedSort extends DeclarationElement {
    protected AbstractSortDef originalSort;

    public AbstractSortDef getOriginalSort(){
        return originalSort;
    }

    public abstract DerivedSort setOriginalSort(AbstractSortDef sort);

    public boolean isSortName(){
        return false;
    }

    public boolean isLiteral(){
        return false;
    }

    public boolean isDerivedSort(){
        return true;
    }


    public abstract boolean isOptional();
    public abstract boolean isList();


    public boolean originalSortIsDeclaration(){
        if(originalSort.isDeclaration()) return true;
        else return false;
    }
}
