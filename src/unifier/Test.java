package unifier;

import unifier.asfsdf.*;
import unifier.util.DefaultFormattingWriter;
import java.io.*;
import unifier.util.StringHelper;
import javax.swing.DefaultListCellRenderer;
import unifier.util.ChooserDialog;
import javax.swing.ListSelectionModel;
import java.util.Set;
import java.util.HashSet;
import unifier.rsm.ReferenceSchemaModelException;

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
public class Test {
    public static void main(String[] args) throws IOException,
            SdfDefinitionException, SDF2RSException {

        Module module = new Module();

        createModule(module);

        //writerTest(module);
        //chooserDialogTest();
        //sdf2rsTest(module);

    }

    private static void createModule(Module module) throws SdfDefinitionException {
        module.setName("unifier/owl2synthesis/owlTranslator").
                addImport("unifier/owl/OWL-Syntax").
                addImport("unifier/synthesis/Synthesis-Syntax").
                addStartSymbol("Module-Def").
                addStartSymbol("Type-Def");

        Signature s1 = new Signature();
        Signature s2 = new Signature();
        Signature s3 = new Signature();
        Signature s4 = new Signature();

        s1.setName("t-Module-Name").
                addParam(new Declaration(new SortName("OwlSpecification"))).
                addParam(new Declaration(new Optional("OntologyID"))).
                setReturnSort(new Declaration(new SortName("Synthesis-Id")));

        s2.setName("t-Type-Specification-List").
                addParam(new Declaration(new List("Directive"))).
                setReturnSort(new Declaration(new List("Type-Specification",
                ",")));

        s3.setName("t-Type-Supertype-Section").
                addParam(new Declaration(new List("Description"))).
                setReturnSort(new Declaration(new Optional((new Declaration()).
                addElement(new Literal("supertype")).
                addElement(new Literal("\";\"")).
                addElement(new SortName("Supertype-List")).
                addElement(new Literal(";")))));

        s4.setName("t-Attribute-Specification-List").
                addParam(new Declaration(new List("Description"))).
                addParam(new Declaration(new List("Directive"))).
                setReturnSort(new Declaration(new List(
                "Attribute-Specification")));

        module.addSignature(s1).addSignature(s2).addSignature(s3).addSignature(s4);


        module.addDomainSortVariable(new Variable("OwlID",
                new Declaration(new SortName("OwlID"))));
        module.addDomainSortVariable(new Variable("NamespaceDef*",
                new Declaration(new List("NamespaceDef"))));
        module.addDomainSortVariable(new Variable("OntologyID?",
                new Declaration(new Optional("OntologyID"))));

        module.addRangeSortVariable(new Variable("Synthesis-Id",
                new Declaration(new SortName("Synthesis-Id"))));

        module.addEquation((new Equation("Module-Name---Empty",
                                         "t-Module-Name")).setBody(
                "OwlOntology"));

        module.addEquation(
                (new Equation("Module-Def", "t-Module-Def")).
                setBody("{ Synthesis-Id; in: module, ontology; }").
                setParams("NamespaceDef* Ontology( OntologyID? Directive*)").
                addCondition(new Condition("Type-Specification*",
                "t-Type-Specification-List(Directive*, Directive*)",
                                           ConditionKind.MATCHING)).
                addCondition(new Condition("Class-Declarator*",
                "t-Class-Declarator-List( Directive* )",
                                           ConditionKind.NEGATIVE_MATCHING)));

    }

    private static void writerTest(Module module) throws IOException,
            SdfDefinitionException {
        AsfSsdfFormattingWriter writer1 = new AsfSsdfFormattingWriter(
                DefaultFormattingWriter.createFileWriter("writerTest.sdf"));
        writer1.translatorHeader(module);

        AsfSsdfFormattingWriter writer2 = new AsfSsdfFormattingWriter(
                DefaultFormattingWriter.createFileWriter("writerTest.asf"));
        writer2.equations(module);

        System.out.println(StringHelper.getLastComponentOfPath(
                "unifier/owl2synthesis/owl-translator"));
    }

    private static  void chooserDialogTest(){
        // ChooserDialog Test
        Set<String> testNames = new HashSet<String>();
        testNames.add("Schema-Def");
        testNames.add("Module-Def");
        testNames.add("Type-Def");

        testNames = ChooserDialog.showChooserDialog(
                "Select terminal sorts",
                "Submit terminal sorts",
                "Cancel",
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
                testNames,
                new DefaultListCellRenderer()
                    );

        for (String s : testNames) System.out.println(s);
    }

    private static void sdf2rsTest(Module module) throws SDF2RSException,
            SdfDefinitionException, IOException, ReferenceSchemaModelException {
        // SDF2RS test
        SDF2RS sdf2rs;
        sdf2rs = new SDF2RS(module,  DefaultFormattingWriter.createFileWriter("refSchemaTest.snt"));
        sdf2rs.generateSchema();
    }
}
