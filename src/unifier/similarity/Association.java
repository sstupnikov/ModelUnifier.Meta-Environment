package unifier.similarity;

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
public class Association extends Element {
    private String domain = "";
    private String range = "";
    private int maxCard = 0;

    public Association(String domain, String range, int maxCard){
        this.domain = domain;
        this.range = range;
        this.maxCard = maxCard;
    }

    public boolean isAssociation(){
        return true;
    }

    public String getDomain(){
        return domain;
    }

    public String getRange(){
        return range;
    }

    public int getMaxCard(){
        return maxCard;
    }

    public String toString(){
        return getName() + "(" + domain + " -> " + range + ")";
    }
}
