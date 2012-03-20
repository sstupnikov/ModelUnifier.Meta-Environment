package unifier.rsm;

import java.util.Set;
import java.util.HashSet;
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
public class Attribute extends Element {

    private Type type = null;
    private Set<AssociationMetaclass> categories = new HashSet<AssociationMetaclass>();


    public Attribute(){

    }

    public Attribute(String name, Type type, AssociationMetaclass category){
        this.name = name;
        this.type = type;
        this.addCategory(category);
    }


    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Set<AssociationMetaclass> getCategories(){
        Set<AssociationMetaclass> result = new HashSet<AssociationMetaclass>();
        result.addAll(categories);
        return result;
    }

    public void addCategory(AssociationMetaclass metaclass){
        categories.add(metaclass);
    }

    public void specification(FormattingWriter writer) {
        writer.write(getName() + ": " + type.getDesignator() + ";");
        if(categories.size() > 0){
            writer.ln(+1);
            writer.write("metaslot");
            writer.ln(+1);
            writer.write("in: ");
            int i=0;
            for(AssociationMetaclass am: categories){
                writer.write(am.getName());
                if(i < categories.size() - 1) writer.write(", ");
            }
            writer.write(";");
            writer.ln(-1);
            writer.write("end");
            writer.ln(-1);
        }
    }

}
