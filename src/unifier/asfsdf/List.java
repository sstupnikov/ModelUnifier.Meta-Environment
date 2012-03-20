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
public class List extends DerivedSort {
    public static final String EMPTY_SEPARATOR = "";

    private String separator = "";
    private ListMultiplicity multiplicity = ListMultiplicity.AT_LEAST_ZERO_TIME;

    public boolean isList(){
        return true;
    }

    public boolean isOptional(){
        return false;
    }

    public List(){}

    public List(String sort){
        originalSort = new Declaration(new SortName(sort));
    }

    public List(AbstractSortDef sort){
        originalSort = sort;
    }

    public List(String sort, String separator){
        originalSort = new Declaration(new SortName(sort));
        this.separator = separator;
    }

    public List setOriginalSort(AbstractSortDef sort){
        originalSort = sort;
        return this;
    }

    public String getSeparator(){
        return separator;
    }

    public List setSeparator(String separator){
        this.separator = separator;
        return this;
    }

    public String getSeparatorVerbalForm(){
        if(separator.compareTo(",") == 0)
            return "Comma";
        else if(separator.compareTo(";") == 0)
            return "Semicolon";
        else
            return "";
    }

    public boolean separatorIsEmpty(){
        return (separator.compareTo(EMPTY_SEPARATOR) == 0);
    }

    public ListMultiplicity getMultiplicity(){
        return multiplicity;
    }

    public String getMultiplicityToString(){
        return multiplicity.toString();
    }

    public List setMultiplicity(ListMultiplicity multiplicity){
        this.multiplicity = multiplicity;
        return this;
    }


    // The following lists are simple:
    //
    // SORT_NAME+
    // SORT_NAME*
    // {SORT_NAME sep}+
    // {SORT_NAME sep}*
    public boolean isSimple(){
        if(this.originalSort.isDeclaration() && ((Declaration)this.originalSort).consistsOfOneSortName())
            return true;
        else return false;
    }

    public String getNameIfSimple() throws SdfDefinitionException {
        if(this.isSimple()){
            return ((Declaration)this.originalSort).getElements().iterator().next().getNameIfSimple();
        } else {
            throw new SdfDefinitionException("Tried to get name of non-simple list.");
        }
    }

    public String getSignatureNameIfSimple() throws SdfDefinitionException {
        if(this.isSimple()){
            return "create-" + getVariableName();
        } else {
            throw new SdfDefinitionException("Tried to get name of non-simple list.");
        }
    }

    public String getVariableName() throws SdfDefinitionException {
        String result = "";

        if(this.isSimple()){
            result = result + ((Declaration)originalSort).getVariableName();
            if(!separatorIsEmpty()) result = result + "-" + this.getSeparatorVerbalForm();
            result = result + "-" + this.multiplicity.getVerbalForm();
            result = result + "-List";
        } else
            throw new SdfDefinitionException("Variable name can not be provided for non-simple lists.");

        return result;
    }

    public boolean isOptionalLiteral() {
        return false;
    }
}
