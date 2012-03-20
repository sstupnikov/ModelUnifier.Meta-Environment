package unifier.asfsdf;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

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
public class Enumeration extends AbstractSortDef {
    private List<String> elements = new ArrayList<String>();

    public List<String> getElements() {
        return elements;
    }

    public Enumeration addElement(String elm){
        elements.add(elm);
        return this;
    }

    public Enumeration addElements(Set<String> elms){
        elements.addAll(elms);
        return this;
    }

    public boolean isAlternative() {
        return false;
    }

    public boolean isDeclaration() {
        return false;
    }

    public boolean isEnumeration() {
        return true;
    }

    public boolean isList() {
        return false;
    }

}
