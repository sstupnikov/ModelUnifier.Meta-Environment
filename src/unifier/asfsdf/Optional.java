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
public class Optional extends DerivedSort {
    public boolean isOptional(){
        return true;
    }

    public boolean isList(){
        return false;
    }

    public Optional(){}

    public Optional(String sort){
        originalSort = new Declaration(new SortName(sort));
    }

    public Optional(AbstractSortDef sort){
        originalSort = sort;
    }

    public Optional setOriginalSort(AbstractSortDef sort){
        originalSort = sort;
        return this;
    }

    // The following optionals are simple:
    //
    // SORT_NAME?
    // LITERAL?
    public boolean isSimple(){
        if(this.originalSort.isDeclaration() &&
           (((Declaration)this.originalSort).consistsOfOneLiteral()
           | ((Declaration)this.originalSort).consistsOfOneSortName())
          )
            return true;
        else return false;
    }


    public String getNameIfSimple() throws SdfDefinitionException {
        if(this.isSimple()){
            return ((Declaration)this.originalSort).getElements().iterator().next().getNameIfSimple();
        } else{
            throw new SdfDefinitionException("Tried to get name of non-simple optional.");
        }
    }


    public boolean originalSortDeclConsistsOfOneSortName(){
        if(this.originalSort.isDeclaration() &&
           ((Declaration)this.originalSort).consistsOfOneSortName()
          )
            return true;
        else return false;
    }

    public String getVariableName() throws SdfDefinitionException {
        String result = "";

        if(this.isSimple()){
            result = result + ((Declaration)originalSort).getVariableName() + "?";
        } else
            throw new SdfDefinitionException("Variable name can not be provided for non-simple lists.");

        return result;
    }

    public boolean isOptionalLiteral() {
        if(this.originalSort.isDeclaration() &&
           ((Declaration)this.originalSort).consistsOfOneLiteral()
          )
            return true;
        else return false;
    }


}
