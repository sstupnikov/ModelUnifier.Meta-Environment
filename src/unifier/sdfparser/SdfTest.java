package unifier.sdfparser;

import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;


import unifier.sdfparser.*;
import org.antlr.runtime.tree.CommonTree;
import antlr.debug.misc.ASTFrame;
import unifier.asfsdf.SdfCreator;
import unifier.asfsdf.Module;
import unifier.asfsdf.AsfSsdfFormattingWriter;
import unifier.util.FormattingWriter;
import unifier.util.DefaultFormattingWriter;
import java.util.HashSet;
import java.util.Set;
import unifier.SDF2RS;
import unifier.util.StringHelper;
import unifier.util.StringChooserDialog;


public class SdfTest {

    public static void main(String args[]) throws Exception {

        Reader r1, r2, r3;

        try {

            r1 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\synthesis\\Synthesis-Syntax.sdf");
            r2 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\owl\\OWL-Syntax.sdf");

            // creating sdf module
            Set<String> terminals = new HashSet<String>();

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


            //System.out.println("SORTS: ");
            //for(String s: (new SdfCreator()).getSortNames(r1))
            //    System.out.print(s +" ");

            //System.out.println();

            //System.out.println("SORTS: ");
            //for(String s: (new SdfCreator()).getSortNames(r2))
            //    System.out.print(s +" ");

            //Set<String> chosen = StringChooserDialog.showStringChooserDialog("Choose terminal sorts", (new SdfCreator()).getSortNames(r1));

            //System.out.println("Chosen sorts: ");
            //for(String s: chosen)
            //    System.out.print(s +" ");
            //System.out.println();

            r1.close();
            r2.close();

            r1 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\synthesis\\Synthesis-Syntax.sdf");
            r2 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\owl\\OWL-Syntax.sdf");
            r3 = new FileReader("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\unifier\\synthesis\\AssociationMetaclassExtension.sdf");

            SdfCreator sdfCreator = new SdfCreator();
            //sdfCreator.processSdf(r1, terminals);
            Module module = sdfCreator.processSdf(r3, terminals);


            // viewing sdf module
            AsfSsdfFormattingWriter w = new AsfSsdfFormattingWriter(DefaultFormattingWriter.createSystemOutWriter());
            w.translatorHeader(module);

            // Generate Reference Schema
            SDF2RS sdf2rs = new SDF2RS(module, DefaultFormattingWriter.createFileWriter("D:\\Documents\\Serge\\Research\\rfbr\\06-07-08072-\u043E\u0444\u0438\\\u041E\u0442\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u0438\u0435 OWL\\AssociationMetaclassExtensionReferenceSchemaTemplate.snt"));
            sdf2rs.generateSchema();

        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
