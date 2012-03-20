package unifier.rsm;

import java.util.Map;
import java.util.HashMap;
import unifier.util.StringHelper;
import java.util.Set;
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
public class Module extends Element {
    private Map<String, Type> types = new HashMap<String, Type>();
    private Map<String, Class> classes = new HashMap<String, Class>();

    public void addType(Type type) throws ReferenceSchemaModelException {
        if(StringHelper.isNullEmptyOrBlank(type.getName())){
            throw new ReferenceSchemaModelException("Type with empty name can not be added to module.");
        } else{
            types.put(type.getName(), type);
        }
    }

    public Set<String> getTypesNames(){
        return types.keySet();
    }

    public Type getType(String typeName){
        return types.get(typeName);
    }

    public void addClass(Class _class) throws ReferenceSchemaModelException {
        if(StringHelper.isNullEmptyOrBlank(_class.getName())){
            throw new ReferenceSchemaModelException("Class with empty name can not be added to module.");
        } else{
            classes.put(_class.getName(), _class);
        }
    }

    public Set<String> getClassesNames(){
        return classes.keySet();
    }

    public Class getClass(String typeName){
        return classes.get(typeName);
    }



    public void specification(FormattingWriter writer){
        int i;

        writer.write("{ "); writer.write(getName()); writer.write(";");
        writer.write(" in: module, structure;"); writer.ln(); writer.ln();

        if(types.size() != 0){
            writer.write("type:"); writer.ln(); writer.ln();
        }
        i = 1;
        for(String typeName: types.keySet()){
            types.get(typeName).specification(writer);
            if(i < types.size()) writer.write(","); else writer.write(";");
            writer.ln(); writer.ln();
            i++;
        }
        if(types.size() != 0){
            writer.ln(); writer.ln();
        }

        if(classes.size() != 0){
            writer.write("class_specification:"); writer.ln(); writer.ln();
        }
        i = 1;
        for(String className: classes.keySet()){
            classes.get(className).specification(writer);
            if(i < classes.size()) writer.write(","); else writer.write(";");
            writer.ln();
            i++;
        }
        if(classes.size() != 0){
            writer.ln(); writer.ln();
        }

        writer.write("}");

        writer.flush();
    }
}
