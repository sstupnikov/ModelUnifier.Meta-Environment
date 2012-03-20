package unifier.asfsdf;

import java.util.List;
import unifier.util.StringHelper;
import java.util.ArrayList;
import java.util.Map;
import unifier.TranslatorTemplateConstructionException;

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
public class Signature extends Named {
    private List<Declaration> params = new ArrayList<Declaration>();
    private Declaration returnSort;

    public Signature setName(String name){
        this.name = name;
        return this;
    }

    public List<Declaration> getParams(){
        return params;
    }

    public Signature addParam(Declaration decl){
        params.add(decl);
        return this;
    }

    public Declaration getReturnSort(){
        return returnSort;
    }

    public Signature setReturnSort(Declaration sort){
        returnSort = sort;
        return this;
    }

}
