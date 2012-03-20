package unifier.asfsdf;

import java.util.List;
import java.util.ArrayList;

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
public class Alternative extends AbstractSortDef {

    private List<AbstractSortDef> elements = new ArrayList<AbstractSortDef>();

    public List getElements() {
        return elements;
    }

    public Alternative addElement(AbstractSortDef sort){
        elements.add(sort);
        return this;
    }

    public boolean isDeclaration(){
        return false;
    }

    public boolean isAlternative(){
        return true;
    }

    public boolean isEnumeration(){
        return false;
    }

    public boolean isList() {
        return false;
    }

}
