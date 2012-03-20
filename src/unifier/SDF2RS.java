package unifier;

import unifier.util.FormattingWriter;
import unifier.asfsdf.Module;
import java.util.Set;
import javax.swing.ListSelectionModel;
import unifier.util.ChooserDialog;
import javax.swing.DefaultListCellRenderer;
import unifier.util.StringHelper;
import java.util.logging.Logger;
import unifier.asfsdf.Sort;
import unifier.asfsdf.SdfDefinitionException;
import unifier.asfsdf.Terminal;
import unifier.SDF2RSException;
import unifier.asfsdf.Alternative;
import unifier.asfsdf.Nonterminal;
import unifier.asfsdf.AbstractSortDef;
import unifier.asfsdf.Declaration;
import unifier.rsm.Type;
import unifier.rsm.ADT;
import unifier.rsm.ReferenceSchemaModelException;
import unifier.asfsdf.Enumeration;
import unifier.util.MutableInteger;
import unifier.asfsdf.DeclarationElement;
import unifier.asfsdf.Optional;
import unifier.rsm.AssociationMetaclass;
import unifier.rsm.Attribute;
import unifier.asfsdf.List;
import unifier.asfsdf.ListMultiplicity;
import unifier.rsm.SetType;

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
public class SDF2RS {

    public SDF2RS(Module syntax, FormattingWriter writer) {
        this.syntax = syntax;
        this.writer = writer;

        module = new unifier.rsm.Module();
    }


    private Logger logger = Logger.getLogger("unifier.SDF2RS");

    private FormattingWriter writer;
    private unifier.rsm.Module module;

    private Module syntax;




    public void generateSchema() throws SdfDefinitionException, SDF2RSException, ReferenceSchemaModelException {
        logger.info("Reference Schema creation for: " + syntax.getName());

        module.setName(StringHelper.getLastComponentOfPath(syntax.getName()) + "ReferenceSchema");
        generateTypes();

        module.specification(writer);

        logger.info("Finished.");
    }

    private void generateTypes() throws SdfDefinitionException, SDF2RSException,
            ReferenceSchemaModelException {
        for(String sortName: syntax.getSortNames()){
            Sort sort = syntax.getSort(sortName);
            generateType(sort);
        }
    }

    private Type generateType(Sort sort) throws SdfDefinitionException, SDF2RSException, ReferenceSchemaModelException {
        Type result;

        if(sort == null) return null;

        logger.info("Generate type for sort: " + sort.getName());

        result = module.getType(sort.getName());
        if(result != null) return result;

        if (sort.isTerminal()) {
            Type type = new ADT();
            type.setName(sort.getName());
            module.addType(type);
            result = type;
            logger.info("ADT is created for terminal sort: " + type.getName());
        } else
        if (sort.isEnumerationSort()) {
            result = generateEnum(sort);
        } else
        if (sort.isAlternativeSort()) {
            result = analizeAlternativeSort(sort);
        } else
        if (!sort.isListSort()) {
            result = analizeDeclarationSort(sort);
        }

        return result;
    }


    private unifier.rsm.Enum generateEnum(Sort sort) throws SDF2RSException, SdfDefinitionException, ReferenceSchemaModelException {
        if(!sort.isEnumerationSort())
            throw new SDF2RSException("Non-enumeration sort is tried to be analized as alternative.");

        unifier.rsm.Enum result = new unifier.rsm.Enum();
        Enumeration sortDef = (Enumeration)((Nonterminal)sort).getDefinition();

        result.setName(sort.getName());
        for(String value: sortDef.getElements()) result.addValue(value);

        module.addType(result);

        logger.info("Enum is created for sort: " + result.getName());

        return result;
    }

    private ADT analizeAlternativeSort(Sort sort) throws SDF2RSException, SdfDefinitionException, ReferenceSchemaModelException {
        if(!sort.isAlternativeSort())
            throw new SDF2RSException("Non-alternative sort is tried to be analized as alternative.");

        Alternative alt = (Alternative)((Nonterminal)sort).getDefinition();
        ADT type = new ADT();

        type.setName(sort.getName());
        module.addType(type);

        logger.info("Adt is created for alternative sort: " + type.getName());

        for(Object obj: alt.getElements()){
            AbstractSortDef sortDef = (AbstractSortDef)obj;

            if(sortDef.isDeclaration() && ((Declaration)sortDef).consistsOfOneSortName()){
                String subsortName = ((Declaration)sortDef).getNameIfSimple();
                Sort subsort = syntax.getSort(subsortName);
                Type subtype = generateType(subsort);

                if(subtype != null && subtype.isADT())
                    ((ADT)subtype).addSupertype(type);

            } else {
                logger.info("Non-supported alternative element.");
            }

        }

        return type;
    }

    private ADT analizeDeclarationSort(Sort sort) throws SDF2RSException, SdfDefinitionException, ReferenceSchemaModelException {
        if(!sort.isDeclarationSort())
            throw new SDF2RSException("Non-declaration sort is tried to be analized as declaration.");

        Declaration decl = (Declaration)((Nonterminal)sort).getDefinition();
        ADT type = new ADT();
        MutableInteger assocNumber = new MutableInteger(0);

        type.setName(sort.getName());
        module.addType(type);

        logger.info("ADT is created for declaration sort: " + type.getName());

        analizeDeclaration(decl, type, false, assocNumber);

        return type;
    }

    private void analizeDeclaration(Declaration decl, ADT type, boolean optional, MutableInteger assocNumber) throws
            SdfDefinitionException, ReferenceSchemaModelException, SDF2RSException {

        logger.info("Analize declaration for type: " + type.getName());
        if(decl.isSimple()) logger.info(decl.getNameIfSimple());



        String assocName = "";
        int minCard = 0;
        int maxCard = 0;
        AssociationMetaclass category;
        Attribute assoc;
        Type attributeType = null;

        // If declaration consists of one sort name,
        // just establish subtype relation istead of creation of association.
        // An exception is optional declaration.
        if(decl.consistsOfOneSortName() && !optional){
            String subsortName = decl.getNameIfSimple();
            logger.info("Subsort name: " + subsortName);
            Sort subsort = syntax.getSort(subsortName);
            Type subtype = generateType(subsort);

            if(subtype != null && subtype.isADT())
                ((ADT)subtype).addSupertype(type);

            return;
        }

        // Set minCard of potential association.
        if(optional) minCard = 0; else minCard = 1;

        // Process declaration elements.
        for(DeclarationElement elm: decl.getElements()){
            logger.info("Declaration element of type: " + type.getName());
            // Element is literal.
            if(elm.isLiteral()){
                assocName = assocName + elm.getNameIfSimple();
            } else
            // Element is optional.
            if (elm.isOptional() && ((Optional)elm).originalSortIsDeclaration()) {
               Declaration inner = (Declaration)((Optional)elm).getOriginalSort();
               analizeDeclaration(inner, type, true, assocNumber);
            } else
            // Element is a declaration of list or name of sort.
            // Association should be generated.
            if(elm.isList() || elm.isSortName()){
                // Only simple lists are processed.
                if(!elm.isSimple()) break;

                if(syntax.getSort(elm.getNameIfSimple()) == null){
                    logger.info("Sort " + elm.getNameIfSimple() + " is undeclared.");
                    break;
                }

                // Create association name.
                if (assocName.compareTo("") == 0 || !StringHelper.isIdentifier(assocName)) {
                    assocNumber.inc();
                    assocName = type.getName() + "Assoc" + assocNumber;
                }

                // Element is declaration of sort.
                if(elm.isList()){
                    maxCard = -1;
                    attributeType = generateType(syntax.getSort(elm.getNameIfSimple()));
                    if(((List)elm).getMultiplicity().equals(ListMultiplicity.AT_LEAST_ZERO_TIME)) minCard = 0;
                } else
                // Element is a name of list-sort.
                if (elm.isSortName() && syntax.getSort(elm.getNameIfSimple()).isListSort()) {
                    maxCard = -1;

                    Nonterminal sort = (Nonterminal) syntax.getSort(elm.getNameIfSimple());
                    Declaration inner = (Declaration) sort.getDefinition();
                    List list = (List) inner.getElements().iterator().next();

                    attributeType = generateType(syntax.getSort(list.getNameIfSimple()));
                    if(list.getMultiplicity().equals(ListMultiplicity.AT_LEAST_ZERO_TIME)) minCard = 0;
                } else
                // Element is a name of non-list-sort.
                if (elm.isSortName() && !syntax.getSort(elm.getNameIfSimple()).isListSort()) {
                    maxCard = 1;
                    attributeType = generateType(syntax.getSort(elm.getNameIfSimple()));
                }

                // If max cardinality of association is inf,
                // change attribute type to set type.
                if(maxCard == -1)
                    attributeType = new SetType(attributeType);

                // Create attribute category for the association
                category = new AssociationMetaclass(assocName + "Category", minCard, maxCard, 1, 1);
                module.addClass(category);
                // Create association attribute.
                assoc = new Attribute(assocName, attributeType, category);
                // Add attribute to ADT.
                type.addAttribute(assoc);
                // Reset association name
                assocName = "";
            }
            else logger.info("Unsupported declaration element.");
        }
    }

}
