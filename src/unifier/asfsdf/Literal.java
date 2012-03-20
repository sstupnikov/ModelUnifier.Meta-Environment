package unifier.asfsdf;

import unifier.util.StringHelper;

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
public class Literal extends DeclarationElement {
    private String value = "";


    public boolean isSortName(){
        return false;
    }

    public boolean isLiteral(){
        return true;
    }

    public boolean isDerivedSort(){
        return false;
    }

    public Literal(){}

    public Literal(String value){
        this.value = value;
    }


    public String getValue(){
        return StringHelper.toQuotation(value);
    }

    public Literal setValue(String value){
        this.value = value;
        return this;
    }

    public String getVariableName() throws SdfDefinitionException {
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
