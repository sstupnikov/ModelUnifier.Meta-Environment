package unifier;

import unifier.util.FormattingWriter;
import java.util.Set;
import unifier.similarity.Similarity;
import unifier.util.DefaultFormattingWriter;
import java.util.logging.Logger;
import unifier.util.StringHelper;
import unifier.asfsdf.*;
import java.util.HashSet;
import unifier.similarity.Element;
import java.util.HashMap;
import java.util.Map;
import unifier.similarity.Association;

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
public class TranslatorTemplateConstructor {

    TranslatorTemplateConstructor(FormattingWriter signatureWriter, FormattingWriter equationsWriter) {
        this.signatureWriter = signatureWriter;
        this.equationsWriter = equationsWriter;
    }

    TranslatorTemplateConstructor(
            Module source,
            Module target,
            Set<Similarity> sims,
            FormattingWriter signatureWriter,
            FormattingWriter equationsWriter) {

        this.source = source;
        this.target = target;
        this.sims = sims;
        this.signatureWriter = signatureWriter;
        this.equationsWriter = equationsWriter;
    }

    private Logger logger = Logger.getLogger("unifier.TranslatorTemplateConstructor");

    private Module source;
    private Module target;
    // For every S in sims S.of is the element of source, S.to is the element of target
    private Set<Similarity> sims;
    private FormattingWriter signatureWriter;
    private FormattingWriter equationsWriter;
    private Module translator = new Module();


    // Names of the sorts which has not been processed yet.
    private Set<String> sortNamesToProcess = new HashSet<String>();
    // Name of the sort which is processed right now.
    private String processedSortName = "";


    public void writeTranslatorTemplate() throws
            TranslatorTemplateConstructionException, SdfDefinitionException {

        generateTranslatorTemplate();
        writeSignatures();
        writeEquations();
    }

    private void writeSignatures() throws SdfDefinitionException {
        AsfSsdfFormattingWriter writer = new AsfSsdfFormattingWriter(signatureWriter);
        writer.translatorHeader(translator);
    }

    private void writeEquations() throws SdfDefinitionException {
        AsfSsdfFormattingWriter writer = new AsfSsdfFormattingWriter(equationsWriter);
        writer.equations(translator);
    }

    private void generateTranslatorTemplate() throws  TranslatorTemplateConstructionException, SdfDefinitionException {
        generateTranslatorName();
        generateImports();
        translator.getStartSymbols().addAll(target.getStartSymbols());
        generateVariables();
        generateSignaturesAndEquations();
    }

    private void generateTranslatorName() throws  TranslatorTemplateConstructionException {
        if(StringHelper.isNullEmptyOrBlank(source.getName())){
            throw new TranslatorTemplateConstructionException("Source model name is null, empty or blank.");
        }
        if(StringHelper.isNullEmptyOrBlank(target.getName())){
            throw new TranslatorTemplateConstructionException("Target model name is null, empty or blank.");
        }

        translator.setName("unifier/" +
                           StringHelper.getLastComponentOfPath(source.getName()) +
                           "2" +
                           StringHelper.getLastComponentOfPath(target.getName()) +
                           "/" +
                           StringHelper.getLastComponentOfPath(source.getName()) +
                           "2" +
                           StringHelper.getLastComponentOfPath(target.getName()) +
                           "Translator");
    }

    private void generateImports(){
        translator.addImport(source.getName());
        translator.addImport(target.getName());
    }

    private void generateVariables(){
        for(String name: source.getSortNames()){
            translator.addDomainSortVariable(new Variable(name, new Declaration(new SortName(name))));
        }
        for(String name: target.getSortNames()){
            translator.addRangeSortVariable(new Variable(name, new Declaration(new SortName(name))));
        }
    }




    private void generateSignaturesAndEquations() throws SdfDefinitionException, TranslatorTemplateConstructionException {
        sortNamesToProcess.clear();
        sortNamesToProcess.addAll(target.getSortNames());

        while(sortNamesToProcess.size() > 0){
            String s = sortNamesToProcess.iterator().next();
            Sort sort = target.getSort(s);
            sortNamesToProcess.remove(s);
            //logger.info("SORTS SIZE " + sortNamesToProcess.size());
            processSort(sort);
        }
    }


    private void processSort(Sort sort) throws TranslatorTemplateConstructionException, SdfDefinitionException {
        Signature signature;
        String previousProcessedSortName;

        //logger.info("Process sort: " + sort.getName());

        previousProcessedSortName = processedSortName;
        processedSortName = sort.getName();

        signature = createSignature(sort);

        // Terminal sort
        if(sort.isTerminal()){
            // nothing
        }
        // Nonterminal sort
        else {
            Nonterminal nonterm = (Nonterminal)sort;
            AbstractSortDef sortDef = nonterm.getDefinition();

            if(sortDef == null){
                throw new TranslatorTemplateConstructionException("No definition for nonterminal sort " +
                        sort.getName() + " is provided.");
            } else
            // Sort definition is Alternative of Enumeration
            if(sortDef.isAlternative() || sortDef.isEnumeration()){
                // nothing
            } else
            // Sort definition is Declaration
            if(sortDef.isDeclaration()){
                processDeclaration((Declaration)sortDef, signature);
            }

        }

        processedSortName = previousProcessedSortName;
    }

    private Signature createSignature(Sort sort) throws SdfDefinitionException {
        String sortName = sort.getName();
        Signature signature = new Signature();

        signature.setName(sort.getSignatureName());
        signature.setReturnSort(new Declaration(new SortName(sortName)));

        Set<String> params = getTopmostSourceSupersorts(getRelevantElements(sortName));

        for(String par: params){
            signature.addParam(new Declaration(new SortName(par)));
        }

        translator.addSignature(signature);

        return signature;
    }

    // Get all source elements similar to target element tarElm
    // result = { E | exists S.(in(S, sims) & S.to = name & S.of = E ) }
    private Set<String> getRelevantElements(String targetElm){
        Set<String> result = new HashSet<String>();

        if(sims == null) return result;

        for(Similarity s: sims){
            if(s.getTo().getName().compareTo(targetElm) == 0){
                Element sourceElm = s.getOf();
                if(!sourceElm.isAssociation()){
                    result.add(sourceElm.getName());
                }
            }
        }

        return result;
    }

    // The algorithm is as follows:
    //
    // result = sortNames
    // for all s in sortNames
    //   if s is a subsort (alternative) of any sort in sortNames
    //     result.remove(s)
    private Set<String> getTopmostSourceSupersorts(Set<String> sortNames) throws
            SdfDefinitionException {
        //String names = "";
        //for(String s: sortNames) names = names + " " + s;
        //if(sortNames.size() != 0)
        //    logger.info("Topmost source supersorts for: " + names);


        Set<String> result = new HashSet<String>();
        result.addAll(sortNames);

        for(String sub: sortNames){
            for(String sup: sortNames){
                if(sub.compareTo(sup) != 0 && isSubsortInSource(sup, sub)){
                    result.remove(sub);
                }
            }
        }

        return result;
    }

    // Check whether subsortCandidateName is a subsort of superSortName
    private boolean isSubsortInSource(String superSortName, String subsortCandidateName) throws
            SdfDefinitionException {

        //logger.info("SubsortInSource for: " + superSortName + " " + subsortCandidateName);

        boolean result = false;
        Sort superSort = source.getSort(superSortName);

        if(!superSort.isTerminal()){
            Nonterminal nonterm = (Nonterminal)superSort;
            AbstractSortDef sortDef = nonterm.getDefinition();

            if (sortDef.isAlternative()) {
                //logger.info("# alternatives: " + sortDef.getElements().size());
                for (Object alternative : sortDef.getElements()){
                    //logger.info(alternative.toString());
                    if (((AbstractSortDef) alternative).isDeclaration()) {
                        //logger.info("Alternative is declaration");
                        if (((Declaration) alternative).getElements().size() ==  1) {
                            DeclarationElement elm = ((Declaration) alternative).getElements().iterator().next();
                            if (elm.isSortName()) {
                                String elmSortName = ((SortName) elm).getValue();
                                //logger.info("ELEMENT sort name: " + elmSortName);
                                if (elmSortName.compareTo(subsortCandidateName) == 0) return true;
                                else if(isSubsortInSource(elmSortName, subsortCandidateName))
                                    result = true;
                            }
                        }
                    }
                }
            }
        }

        //logger.info("" + result);
        return result;
    }

    // Create equation for the declaration <decl> of a sort of target model.
    // Equation defines function with signature <signature>.
    private void processDeclaration(Declaration decl, Signature signature) throws
            SdfDefinitionException, TranslatorTemplateConstructionException {

        String declStringRepr = "";
        Map<Variable, Integer> variableInstances;
        Equation equation = new Equation();
        String equationParams;
        Set<Condition> conditions;

        //logger.info("Process declaration: " + signature.getName());

        // generate declaration string representation and extract variables
        declStringRepr = decl.toTerm();
        variableInstances = decl.getVariableInstances();
        //logger.info("Var instances: " + variableInstances.size());

        // add variables extracted from decl,
        // these are variables of target model
        for(Variable var: variableInstances.keySet())
            translator.addRangeSortVariable(var);

        // generate equation conditions
        conditions = generateEquationConditions(variableInstances);
        //logger.info("Conditions: " + conditions.size());

        // generate equation params
        equationParams = signatureParamsToTermList(signature, ParameterTermSeparator.NEWLINE);

        // generate equation
        equation.setTag(signature.getName());
        equation.setDefinedFunctionName(signature.getName());
        equation.setParams(equationParams);
        equation.setBody(declStringRepr);
        equation.getConditions().addAll(conditions);

        // add equation to translator module
        translator.addEquation(equation);
    }

    // Generate signature representation as term list,
    // extract variables and add them to translator module.
    private String signatureParamsToTermList(Signature signature, ParameterTermSeparator sep) throws
            TranslatorTemplateConstructionException, SdfDefinitionException {

        Map<Variable, Integer> variableInstances = null;
        String term = "";
        String result = "";
        int i = 1;
        int paramNumber;

        paramNumber = signature.getParams().size();
        if(paramNumber != 0) result += sep.toString();

        for(Declaration paramDecl: signature.getParams()){
            if(paramDecl.consistsOfOneSortName()){
                String paramSortName = ((SortName) paramDecl.getElements().iterator().next()).getValue();
                Sort paramSort = source.getSort(paramSortName);

                term = paramSort.toTerm();
                variableInstances = paramSort.getVariableInstances();
            } else{
                term = paramDecl.toTerm();
                variableInstances = paramDecl.getVariableInstances();
            }

            result += term;
            if(i != paramNumber) result += ",";
            result += sep.toString();

            // add variables extracted from parameter sort declaration,
            // these are variables of source model
            for (Variable var : variableInstances.keySet())
                translator.addDomainSortVariable(var);

            i++;
        }

        return result;
    }

    private String signatureToTerm(Signature signature, ParameterTermSeparator sep) throws
            SdfDefinitionException, TranslatorTemplateConstructionException {
        String result;

        result = signature.getName() + "(";
        result += signatureParamsToTermList(signature, sep);
        result += ")";

        return result;
    }

    private Set<Condition> generateEquationConditions(Map<Variable, Integer> variableInstances) throws
            TranslatorTemplateConstructionException, SdfDefinitionException {

        Set<Condition> result = new HashSet<Condition>();

        for(Variable var: variableInstances.keySet()){
            Declaration decl = var.getDeclaration();
            String originalSortName = "";
            Sort originalSort;
            Signature sig = null;

            logger.info("Var: " + var.getName());

            if(!decl.isSimple())
                throw new TranslatorTemplateConstructionException("Tried to generate equation condition for variable with non-simple declaration.");

            originalSortName = decl.getNameIfSimple();
            originalSort = target.getSort(originalSortName);

            if(originalSort == null) continue;

            // If sort <originalSortName> has not been processed yet, process it.
            if (sortNamesToProcess.contains(originalSortName)){
                sortNamesToProcess.remove(originalSortName);
                processSort(originalSort);
            }

            // If declaration <decl> is
            // SORT_NAME or (SORT_NAME)?
            // and
            // SORT_NAME is not list-sort (its definition is not list)
            if ((decl.consistsOfOneSortName() || decl.consistsOfOneSimpleOptional()) && !originalSort.isListSort()) {
                sig = translator.getSignature(originalSort.getSignatureName());
            } else
            // FIRST CASE:
            // If declaration <decl> is
            // SORT_NAME+
            // SORT_NAME*
            // {SORT_NAME sep}+
            // {SORT_NAME sep}*
            //
            // or
            //
            // SECOND CASE:
            // If declaration <decl> is
            // SORT_NAME
            // and
            // SORT_NAME is list-sort
            if(decl.isList() || (decl.consistsOfOneSortName() && originalSort.isListSort())){
                Set<Association> relAssoc;
                Set<String> additionalRelevSorts;

                // For the second case <originalSortName>, <originalSort> and <decl> should be extracted
                // from definition of SORT_NAME
                if(decl.consistsOfOneSortName() && originalSort.isListSort()){
                    decl = (Declaration)((Nonterminal)originalSort).getDefinition();
                    originalSortName = decl.getNameIfSimple();
                    originalSort = target.getSort(originalSortName);
                    logger.info("SECOND CASE: " + originalSortName);
                } else
                    logger.info("FIRST CASE: " + originalSortName);

                relAssoc = getConsistentRelevantAssociations(processedSortName, originalSortName);
                logger.info("# relevant associations: " + relAssoc.size());

                // Get all source elements relevant to target element <listSortOriginalName> such that
                // exists no association a in <relAssoc> such that a.range = listSortOriginalName
                additionalRelevSorts = getTopmostSourceSupersorts(getRelevantElements(originalSortName));
                for(Association assoc: relAssoc)
                    additionalRelevSorts.remove(assoc.getRange());

                // Get signature for list-sort
                sig = translator.getSignature(decl.getSignatureName());
                // If signature exists and association in current case exists, overwrite signature
                if(sig != null && relAssoc.size() > 0){
                    target.removeSignature(sig);
                    sig = null;
                }
                // Create signature.
                if(sig == null){
                    sig = new Signature();

                    sig.setName(decl.getSignatureName());
                    sig.setReturnSort(decl);

                    // Add parameters of list-sorts related to
                    // relevant associations
                    for(Association assoc: relAssoc){
                        String sep = source.getSeparatorOfListSort(assoc.getDomain(), assoc.getRange());
                        sig.addParam(new Declaration(new List(assoc.getRange(), sep)));
                    }
                    // Add parameters related to additional relevant sorts
                    for(String sortName: additionalRelevSorts)
                        sig.addParam(new Declaration(new SortName(sortName)));

                    translator.addSignature(sig);

                    logger.info("SIGNATURE: " + this.signatureToTerm(sig, ParameterTermSeparator.SPACE));
                    //AsfSsdfFormattingWriter w = new AsfSsdfFormattingWriter(DefaultFormattingWriter.createSystemOutWriter());
                    //w.signature(sig);

                    // If there is only one relevant association, create recursive function
                    // of list processing
                    if(relAssoc.size() == 1)
                        generateListSortTranslationEquations(sig, (List)decl.getElements().iterator().next());
                }
            }

            // Create conditions.
            for(int i = 0; i <= variableInstances.get(var).intValue(); i++){
                Condition cond = new Condition();
                cond.setKind(ConditionKind.MATCHING);
                if(i > 0) cond.setLeft(var.getName() + i);
                else cond.setLeft(var.getName());
                cond.setRight(signatureToTerm(sig, ParameterTermSeparator.SPACE));
                result.add(cond);
            }
        }

        return result;
    }

    // Get all associations d->r of source schema relavant to
    // association <domain> -> <range> of target schema.
    // Consistency means d is relevent to <domain>,
    // r is relevant to <range>.
    private Set<Association> getConsistentRelevantAssociations(String domain, String range){
        Set<Association> result = new HashSet<Association>();

        if(sims == null) return result;

        for(Similarity s: sims){
            Element sourceElm = s.getOf();
            Element targetElm = s.getTo();
            if(sourceElm.isAssociation() && targetElm.isAssociation()){
                Association sourceAssoc = (Association)sourceElm;
                Association targetAssoc = (Association)targetElm;

                // Check consistency.
                if (targetAssoc.getDomain().compareTo(domain) == 0 &&
                    targetAssoc.getRange().compareTo(range) == 0 &&
                    getRelevantElements(domain).contains(sourceAssoc.getDomain()) &&
                    getRelevantElements(range).contains(sourceAssoc.getRange())
                    ) {
                    result.add(sourceAssoc);
                }
            }
        }

        return result;
    }

    // Generate two equations defining recursive translation function,
    // translating source list into target list element by element.
    //
    // <targetList> is a declaration of target list.
    // First param of <listTranslationSignature> have to be List - it is source list.
    private void generateListSortTranslationEquations(Signature listTranslationSignature, List targetList)
            throws SdfDefinitionException, TranslatorTemplateConstructionException {
        Equation emptyListProcessingEq = new Equation();
        Equation nonEmptyListProcessingEq = new Equation();

        String targetSortName = "";
        String targetSortSigName = "";
        String targetListVarName = "";
        String targetSeparator = targetList.getSeparator();
        Condition headCondition = new Condition();
        Condition taleCondition = new Condition();
        Signature listElementTranslationSignature = null;
        List sourceList = null;
        Sort targetSort = null;

        logger.info("Generate list sort equations.");

        if(targetList.isSimple()){
            targetSortSigName = targetList.getSignatureNameIfSimple();
            targetSortName = targetList.getNameIfSimple();
            targetListVarName = targetList.getVariableName();

            targetSort = target.getSort(targetSortName);
        }
        else
            throw new TranslatorTemplateConstructionException("Target list is not simple.");

        if(translator.getSignatureNames().contains(targetSort.getSignatureName()))
            listElementTranslationSignature = translator.getSignature(targetSort.getSignatureName());
        else
            throw new TranslatorTemplateConstructionException("Target list element translation signature does not exists.");

        if(listTranslationSignature.getParams().size() == 0)
            throw new TranslatorTemplateConstructionException("List element translation signature contains no params.");
        else
        if(!listTranslationSignature.getParams().get(0).isList())
            throw new TranslatorTemplateConstructionException("List element translation signature firts param is not List.");
        else
            sourceList = (List)listTranslationSignature.getParams().get(0).getElements().iterator().next();

        if(!sourceList.isSimple())
            throw new TranslatorTemplateConstructionException("List element translation signature firts param is not simple list.");

        // Empty list equation
        emptyListProcessingEq.setTag(listTranslationSignature.getName() + "---Empty");
        emptyListProcessingEq.setDefinedFunctionName(listTranslationSignature.getName());
        translator.addEquation(emptyListProcessingEq);

        // Non-empty list equation
        nonEmptyListProcessingEq.setTag(listTranslationSignature.getName());
        nonEmptyListProcessingEq.setDefinedFunctionName(listTranslationSignature.getName());
        nonEmptyListProcessingEq.setBody(targetSortName + " " + targetSeparator + " " + targetListVarName);

        // Generate params
        nonEmptyListProcessingEq.setParams(sourceList.getNameIfSimple() + " " + sourceList.getSeparator() + " " +
           signatureParamsToTermList(listTranslationSignature, ParameterTermSeparator.SPACE));

        // Generate head condition
        headCondition.setKind(ConditionKind.MATCHING);
        headCondition.setLeft(targetSortName);
        headCondition.setRight(signatureToTerm(listElementTranslationSignature, ParameterTermSeparator.SPACE));

        // Generate tale condition
        taleCondition.setKind(ConditionKind.MATCHING);
        taleCondition.setLeft(targetListVarName);
        taleCondition.setRight(signatureToTerm(listTranslationSignature, ParameterTermSeparator.SPACE));

        nonEmptyListProcessingEq.addCondition(headCondition);
        nonEmptyListProcessingEq.addCondition(taleCondition);
        translator.addEquation(nonEmptyListProcessingEq);
    }
}

enum ParameterTermSeparator{
    SPACE (" "),
    NEWLINE ("\n");

    private String stringValue = "";

    ParameterTermSeparator(String value){
        stringValue = value;
    }

    public String toString(){
        return stringValue;
    }
}
