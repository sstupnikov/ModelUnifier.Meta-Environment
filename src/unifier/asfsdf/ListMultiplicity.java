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
public enum ListMultiplicity {
    AT_LEAST_ZERO_TIME ("*", "MayBeEmpty"),
    AT_LEAST_ONE_TIME ("+", "Nonempty");

    private final String stringValue;
    private final String verbalForm;

    ListMultiplicity(String value, String verbal){
        stringValue = value;
        verbalForm = verbal;
    }

    public String toString(){
        return stringValue;
    }

    public String getVerbalForm(){
        return verbalForm;
    }
}
