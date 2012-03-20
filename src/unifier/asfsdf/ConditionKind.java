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
public enum ConditionKind {
    MATCHING (":="),
    NEGATIVE_MATCHING (":!="),
    POSITIVE ("=="),
    NEGATIVE ("!=");

    private String stringValue = "";

    ConditionKind(String value){
        stringValue = value;
    }

    public String toString(){
        return stringValue;
    }

}
