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
public class Enum extends BuiltInType {
    private Set<String> values = new HashSet<String>();

    public Set<String> getValues(){
        Set<String> result = new HashSet<String>();
        result.addAll(values);

        return result;
    }

    public void addValue(String value){
        values.add(value);
    }

    public void addValues(Set<String> values){
        this.values.addAll(values);
    }

    public void specification(FormattingWriter writer) {
        int i;

        writer.write("{ "); writer.write(getName()); writer.write(";");  writer.ln(+1);
        writer.write("{ enum; enum_list: { ");
        i = 1;
        for(String value: values){
            writer.write(value);
            if(i < values.size()) writer.write("; ");
            i++;
        }
        writer.write(" }; }");
        writer.ln(-1);
        writer.write("}");
    }

    public boolean isADT() {
        return false;
    }
}
