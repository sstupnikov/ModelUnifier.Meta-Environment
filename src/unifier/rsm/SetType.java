package unifier.rsm;

import unifier.util.FormattingWriter;

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
public class SetType extends BuiltInType {

    public SetType(){}

    public SetType(Type typeOfElement){
        this.typeOfElement = typeOfElement;
    }

    private Type typeOfElement = null;

    public Type getTypeOfElement(){
        return typeOfElement;
    }

    public void setTypeOfElement(Type type){
        typeOfElement = type;
    }

    public String getDesignator() {
        return "{ set; type_of_element: " + typeOfElement.getName() + "; }";
    }

    public void specification(FormattingWriter writer) {
        writer.write("{ "); writer.write(getName()); writer.write(";");  writer.ln(+1);
        writer.write(getDesignator());
        writer.ln(-1);
        writer.write("}");
    }

    public boolean isADT() {
        return false;
    }

}
