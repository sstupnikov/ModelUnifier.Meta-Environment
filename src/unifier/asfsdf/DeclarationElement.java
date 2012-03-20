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
public abstract class DeclarationElement {
    public abstract boolean isDerivedSort();
    public abstract boolean isLiteral();
    public abstract boolean isSortName();
    public abstract boolean isList();

    public abstract boolean isOptionalLiteral();
    public abstract boolean isOptional();

    public abstract boolean isSimple();
    public abstract String getNameIfSimple() throws SdfDefinitionException;
    public abstract String getVariableName() throws SdfDefinitionException;

}
