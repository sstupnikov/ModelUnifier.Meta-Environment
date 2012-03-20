package unifier.asfsdf;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

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
public class Module extends Named {
    private Set<String> startSymbols = new HashSet<String>();
    private Map<String, Sort> sorts = new HashMap<String, Sort>();
    private Set<String> imports = new HashSet<String>();
    private Map<String, Signature> signatures = new HashMap<String, Signature>();
    private Set<Variable> domainSortVariables = new HashSet<Variable>();
    private Set<Variable> rangeSortVariables = new HashSet<Variable>();
    private Set<Equation> equations = new HashSet<Equation>();

    public Module setName(String name){
        this.name = name;
        return this;
    }


    public Set<String> getStartSymbols(){
        return startSymbols;
    }

    public Module addStartSymbol(String s){
        startSymbols.add(s);
        return this;
    }

    public Set<String> getImports(){
        return imports;
    }

    public Module addImport(String s){
        imports.add(s);
        return this;
    }

    public boolean sortExists(String name){
        return sorts.containsKey(name);
    }

    public Set<String> getSortNames(){
        return sorts.keySet();
    }

    public Sort getSort(String name) throws SdfDefinitionException {
        return sorts.get(name);
    }

    public Module addSort(Sort sort) throws SdfDefinitionException {
        if(sort.getName().trim().compareTo("") == 0){
            throw new SdfDefinitionException("Sort with empty name has not been added to SDF module.");
        } else {
            sorts.put(sort.getName(), sort);
        }

        return this;
    }

    public Set<Variable> getDomainSortVariables(){
        return domainSortVariables;
    }

    public Module addDomainSortVariable(Variable var){
        domainSortVariables.add(var);
        //System.out.println("Domain var added: " + var.getName() + " DOMAIN " + domainSortVariables.size());
        return this;
    }

    public Set<Variable> getRangeSortVariables(){
        return rangeSortVariables;
    }

    public Module addRangeSortVariable(Variable var){
        rangeSortVariables.add(var);
        //System.out.println("Range var added: " + var.getName() + " RANGE " + rangeSortVariables.size());
        return this;
    }

    public Set<Equation> getEquations(){
        return equations;
    }

    public Module addEquation(Equation eq){
        equations.add(eq);
        return this;
    }


    public Signature getSignature(String sigName){
        return signatures.get(sigName);
    }

    public Module addSignature(Signature sig) throws SdfDefinitionException {
        if(sig.getName().trim().compareTo("") == 0){
            throw new SdfDefinitionException("Signature with empty name has not been added to SDF module.");
        } else {
            signatures.put(sig.getName(), sig);
        }
        return this;
    }

    public Module removeSignature(Signature sig) throws SdfDefinitionException {
        if(sig.getName().trim().compareTo("") == 0){
            throw new SdfDefinitionException("Signature with empty name has not been removed from SDF module.");
        } else {
            signatures.remove(sig.getName());
        }
        return this;
    }

    public Set<String> getSignatureNames(){
        return signatures.keySet();
    }


    // Definition of sort <domain> should contain
    // an entry of some list-sort with original sort <range>.
    // The method returns separator of this list-sort
    // (or empty string if separator could not be found).
    public String getSeparatorOfListSort(String domain, String range) throws  SdfDefinitionException {
        String result = "";
        Sort domainSort;

        domainSort = this.getSort(domain);

        if(!domainSort.isTerminal() && ((Nonterminal)domainSort).getDefinition().isDeclaration()){
            Declaration decl = (Declaration)((Nonterminal)domainSort).getDefinition();

            for(DeclarationElement elm: decl.getElements()){
                if(elm.isList() && elm.isSimple() && elm.getNameIfSimple().compareTo(range) == 0){
                    return ((List)elm).getSeparator();
                } else
                if (elm.isSortName()) {
                    String sortName = elm.getNameIfSimple();
                    Sort sort = this.getSort(sortName);

                    if(sort.isListSort()){
                        Declaration listDecl = (Declaration)((Nonterminal) sort).getDefinition();
                        List listElm = (List)listDecl.getElements().iterator().next();

                        if(listElm.isSimple() && listElm.getNameIfSimple().compareTo(range) == 0)
                            return listElm.getSeparator();
                    }
                }
            }
        }

        return result;
    }

}
