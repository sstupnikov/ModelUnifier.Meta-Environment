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
public class Similarity {
    private Element of;
    private Element to;

    public Similarity(Element of, Element to){
        this.of = of;
        this.to = to;
    }

    public Element getOf(){
        return of;
    }

    public Element getTo(){
        return to;
    }

    public String toString(){
        return of + " ~ " + to;
    }
}
