package unifier.asfsdf;

import java.util.List;

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
public abstract class AbstractSortDef {

    public abstract List getElements();

    public abstract boolean isDeclaration();
    public abstract boolean isAlternative();
    public abstract boolean isEnumeration();
    public abstract boolean isList();


}
