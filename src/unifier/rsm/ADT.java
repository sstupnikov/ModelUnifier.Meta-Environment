package unifier.rsm;

import java.util.Set;
import unifier.util.StringHelper;
import java.util.Map;
import java.util.HashMap;
import unifier.util.FormattingWriter;
import java.util.HashSet;

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
public class ADT extends Type {

    private Map<String, Attribute> attributes = new HashMap<String, Attribute>();
    private Set<ADT> supertypes = new HashSet<ADT>();

    public void addAttribute(Attribute attribute) throws ReferenceSchemaModelException {
        if(StringHelper.isNullEmptyOrBlank(attribute.getName())){
            throw new ReferenceSchemaModelException("Attribute with empty name can not be added to ADT.");
        } else{
            attributes.put(attribute.getName(), attribute);
        }
    }

    public Set<String> getTypesNames(){
        return attributes.keySet();
    }

    public Attribute getAttribute(String attributeName){
        return attributes.get(attributeName);
    }

    public void addSupertype(ADT supertype){
        supertypes.add(supertype);
    }

    public Set<ADT> getSupertypes(){
        Set<ADT> result = new HashSet<ADT>();
        result.addAll(supertypes);

        return result;
    }


    public void specification(FormattingWriter writer) {
        writer.write("{ "); writer.write(getName()); writer.write(";");
        writer.write(" in: type;"); writer.ln(+1);

        if(supertypes.size() > 0){
            writer.write("supertype: ");
            int i = 0;
            for(ADT sup: supertypes){
                writer.write(sup.getName());
                if(i < supertypes.size() - 1) writer.write(", ");
                i++;
            }
            writer.write(";");
            writer.ln(); writer.ln();
        }

        for(String attName: attributes.keySet()){
            attributes.get(attName).specification(writer);
            writer.ln();
        }

        writer.ln(-1);
        writer.write("}");
    }

    public boolean isADT() {
        return true;
    }

}
