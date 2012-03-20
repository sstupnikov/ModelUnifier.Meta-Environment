package unifier.util;

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
public class MutableInteger {
    private int value;

    public MutableInteger(int value){
        this.value = value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return "" + value;
    }

    public void inc(){
        value++;
    }


    public boolean equals(Object obj){
        boolean result = false;

        if(obj instanceof MutableInteger && ((MutableInteger)obj).getValue() == value )
            return true;

        return result;
    }

    public int hashCode(){
        return value;
    }
}
