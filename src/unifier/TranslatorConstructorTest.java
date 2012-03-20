package unifier;

import unifier.similarity.Similarity;
import java.util.Set;
import java.util.HashSet;
import unifier.similarity.Element;
import unifier.similarity.Association;
import java.io.Reader;
import unifier.asfsdf.Module;
import unifier.asfsdf.SdfCreator;
import java.io.FileReader;
import java.io.FileNotFoundException;
import unifier.asfsdf.SdfDefinitionException;
import org.antlr.runtime.RecognitionException;
import java.io.IOException;
import unifier.util.FormattingWriter;
import unifier.util.DefaultFormattingWriter;
import unifier.asfsdf.Variable;
import unifier.asfsdf.SortName;
import unifier.asfsdf.Declaration;
import unifier.asfsdf.AsfSsdfFormattingWriter;

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
public class TranslatorConstructorTest {
    public static void main(String[] args) throws FileNotFoundException,
            SdfDefinitionException, RecognitionException, IOException,
            TranslatorTemplateConstructionException {

        // Create sims
        Set<Similarity> sims = new HashSet<Similarity>();

        sims.add(new Similarity(new Element("OwlID"), new Element("Synthesis-Id")));

        sims.add(new Similarity(new Element("Ontology"), new Element("Module-Def")));

        sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Module-Def", "Type-Specification", -1)));
        sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Module-Def", "Class-Declarator", -1)));

        // assoc test 1
        sims.add(new Similarity(new Element("Ontology"), new Element("Type-Section")));
        sims.add(new Similarity(new Element("Directive"), new Element("Type-Specification")));
        sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Type-Section", "Type-Specification", -1)));
        //sims.add(new Similarity(new Element("NamespaceDef"), new Element("Type-Specification")));
        //sims.add(new Similarity(new Element("ClassAxiom"), new Element("Type-Specification")));

        // assoc test 2
        sims.add(new Similarity(new Element("Ontology"), new Element("IRS-Section")));
        sims.add(new Similarity(new Element("Directive"), new Element("Class-Declarator")));
        sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("IRS-Section", "Class-Declarator", -1)));
        //sims.add(new Similarity(new Element("NamespaceDef"), new Element("Type-Specification")));
        //sims.add(new Similarity(new Element("ClassAxiom"), new Element("Type-Specification")));




        sims.add(new Similarity(new Element("ClassAxiom"), new Element("Abstract-Type")));

        sims.add(new Similarity(new Association("ClassAxiom", "Description", -1), new Association("Abstract-Type", "Synthesis-Id", -1)));
        sims.add(new Similarity(new Association("ClassAxiom", "Description", -1), new Association("Abstract-Type", "Attribute-Specification", -1)));

        sims.add(new Similarity(new Element("Restriction"), new Element("Attribute-Specification")));
        sims.add(new Similarity(new Element("Description"), new Element("Attribute-Specification")));
        sims.add(new Similarity(new Element("ObjectPropertyAxiom"), new Element("Attribute-Specification")));
        sims.add(new Similarity(new Association("ObjectPropertyAxiom", "ObjectPropertyRange", -1), new Association("Attribute-Specification", "Attribute-Type", 1)));
        sims.add(new Similarity(new Association("Restriction", "RestrictionComponent", -1), new Association("Attribute-Specification", "Attribute-Metaslot", 1)));

        sims.add(new Similarity(new Element("Cardinality"), new Element("Metaframe-Slot")));
        sims.add(new Similarity(new Association("Cardinality", "Unsigned-Int", 1), new Association("Metaframe-Slot", "Slot-Value", -1)));

        sims.add(new Similarity(new Element("ObjectPropertyAxiom"), new Element("Association-Metaclass")));
        sims.add(new Similarity(new Association("ObjectPropertyAxiom", "SuperProperty", -1), new Association("Association-Metaclass", "Synthesis-Id", -1)));

        // Output sims
        for(Similarity s: sims) System.out.println(s);


        // Create sdf modules
        // creating sdf module
        Reader r1, r2, r3;
        Module source, target;
        SdfCreator c1 = new SdfCreator(), c2 = new SdfCreator();
        Set<String> terminals = new HashSet<String>();
        FormattingWriter w1, w2;
        TranslatorTemplateConstructor constructor;
        AsfSsdfFormattingWriter asfw1, asfw2;

        terminals.add("OwlID");
        terminals.add("DataLiteral");
        terminals.add("URIreference");
        terminals.add("TypedLiteral");
        terminals.add("PlainLiteral");
        terminals.add("LexicalForm");
        terminals.add("LanguageTag");
        terminals.add("NFCString");


        terminals.add("Synthesis-Id");
        terminals.add("Formula");
        terminals.add("Term");
        terminals.add("Typed-Variable");
        terminals.add("Type-Expression");
        terminals.add("Reduct");
        terminals.add("Arithmetic-Expression");

        r1 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\synthesis\\Synthesis-Syntax.sdf");
        r2 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\owl\\OWL-Syntax.sdf");
        r3 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\synthesis\\AssociationMetaclassExtension.sdf");

        source = c1.processSdf(r2, terminals);

        c2.processSdf(r1, terminals);
        target = c2.processSdf(r3, terminals);


        // viewing sdf module
        asfw1 = new AsfSsdfFormattingWriter(DefaultFormattingWriter.createSystemOutWriter());
        asfw2 = new AsfSsdfFormattingWriter(DefaultFormattingWriter.createSystemOutWriter());
        //asfw1.translatorHeader(source);
        //asfw2.translatorHeader(target);


        w1 = DefaultFormattingWriter.createFileWriter("template.sdf");
        w2 = DefaultFormattingWriter.createFileWriter("template.asf");

        constructor = new TranslatorTemplateConstructor(source, target, sims, w1, w2);
        constructor.writeTranslatorTemplate();

        w1.flush();
        w2.flush();

        //System.out.println((new Variable("var", new Declaration(new SortName("sort")))).equals(new Variable("var", new Declaration(new SortName("sort1")))));
        //Set<Variable> vars = new HashSet<Variable>();
        //Variable v1 = new Variable("var", new Declaration(new SortName("sort")));
        //Variable v2 = new Variable("var", new Declaration(new SortName("sort1")));
        //vars.add(v1);
        //info(""+vars.size()+ vars.contains(v2) + v1.equals(v2));
    }

    private static void info(String s){
        System.out.println(s);
    }
}
