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
public class Condition {
    // left hand part of condition
    private String left = "";
    // right hand part of condition
    private String right = "";

    private ConditionKind kind;



    public Condition(){}

    public Condition(String lhp, String rhp, ConditionKind kind){
        this.left = lhp;
        this.right = rhp;
        this.kind = kind;
    }

    public String getLeft(){
        return left;
    }

    public Condition setLeft(String left){
        this.left = left;
        return this;
    }

    public String getRight(){
        return right;
    }

    public Condition setRight(String right){
        this.right = right;
        return this;
    }

    public ConditionKind getKind(){
        return kind;
    }

    public Condition setKind(ConditionKind kind){
        this.kind = kind;
        return this;
    }

}
