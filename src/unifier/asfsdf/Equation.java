package unifier.asfsdf;

import java.util.Set;
import java.util.HashSet;

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
public class Equation {
    private String tag = "";
    private Set<Condition> conditions = new HashSet<Condition>();
    private String definedFunctionName = "";
    private String params = "";
    private String body = "";


    public Equation(){}

    public Equation(String tag, String definedFunctionName){
        this.tag = tag;
        this.definedFunctionName = definedFunctionName;
    }

    public String getTag() {
        return tag;
    }

    public Equation setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getDefinedFunctionName() {
        return definedFunctionName;
    }

    public Equation setDefinedFunctionName(String definedFunctionName) {
        this.definedFunctionName = definedFunctionName;
        return this;
    }

    public String getParams() {
        return params;
    }

    public Equation setParams(String params) {
        this.params = params;
        return this;
    }


    public String getBody() {
        return body;
    }

    public Equation setBody(String body) {
        this.body = body;
        return this;
    }

    public Set<Condition> getConditions(){
        return conditions;
    }

    public Equation addCondition(Condition cond){
        conditions.add(cond);
        return this;
    }

}
