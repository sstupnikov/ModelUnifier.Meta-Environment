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
public class SortName extends DeclarationElement {
    private String value = "";

    public SortName(String value){
        this.value = value;
    }

    public boolean isSortName(){
        return true;
    }

    public boolean isLiteral(){
        return false;
    }

    public boolean isDerivedSort(){
        return false;
    }

    public SortName setValue(String value){
        this.value = value;
        return this;
    }

    public String getValue(){
        return value;
    }

    public String getVariableName()  {
        return value;
    }

    public boolean isSimple(){
        return true;
    }

    public String getNameIfSimple() {
        return value;
    }

    public boolean isList() {
        return false;
    }

    public boolean isOptionalLiteral() {
        return false;
    }

    public boolean isOptional() {
        return false;
    }
}
