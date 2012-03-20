package unifier.rsm;

import unifier.util.FormattingWriter;

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
public class AssociationMetaclass extends Class {

    private int minCard = 0;
    private int maxCard = 0;
    private int minInverseCard = 0;
    private int maxInverseCard = 0;

    public AssociationMetaclass(){
    }

    public AssociationMetaclass(String name, int minCard, int maxCard, int minInverseCard, int maxInverseCard){
        this.name = name;
        this.minCard = minCard;
        this.maxCard = maxCard;
        this.minInverseCard = minInverseCard;
        this.maxInverseCard = maxInverseCard;
    }

    public int getMinCard(){
        return minCard;
    }

    public void setMinCard(int card){
        minCard = card;
    }

    public int getMaxCard(){
        return maxCard;
    }

    public void setMaxCard(int card){
        maxCard = card;
    }

    public int getMinInverseCard(){
        return minInverseCard;
    }

    public void setMinInverseCard(int card){
        minInverseCard = card;
    }

    public int getMaxInverseCard(){
        return maxInverseCard;
    }

    public void setMaxInverseCard(int card){
        maxInverseCard = card;
    }

    public void specification(FormattingWriter writer) {
        writer.write("{ "); writer.write(getName()); writer.write(";");
        writer.write(" in: association, metaclass;"); writer.ln(+1);

        writer.write("instance_section: {"); writer.ln(+1);
        writer.write("association_type: { ");
        writer.write("{ ");

        if(minCard == -1) writer.write("inf"); else writer.write("" + minCard);
        writer.write(", ");
        if(maxCard == -1) writer.write("inf"); else writer.write("" + maxCard);
        writer.write(" }, { ");
        if(minInverseCard == -1) writer.write("inf"); else writer.write("" + minInverseCard);
        writer.write(", ");
        if(maxInverseCard == -1) writer.write("inf"); else writer.write("" + maxInverseCard);
        writer.write(" }");

        writer.write( " };");
        writer.ln(-1);
        writer.write("};");

        writer.ln(-1);
        writer.write("}");
    }


}
