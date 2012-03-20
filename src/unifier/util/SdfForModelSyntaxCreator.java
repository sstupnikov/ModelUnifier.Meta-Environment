package unifier.util;

import unifier.asfsdf.Module;
import meta.AbstrModelRegDef;
import java.io.Reader;
import java.util.Set;
import java.io.StringReader;
import unifier.asfsdf.SdfCreator;
import javax.swing.DefaultListModel;
import meta.FileContainerDef;
import java.util.HashSet;
import java.sql.SQLException;
import java.io.IOException;
import unifier.asfsdf.SdfDefinitionException;
import org.antlr.runtime.RecognitionException;

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
public class SdfForModelSyntaxCreator {

    public static Module createSdf(AbstrModelRegDef model) throws SQLException,
            IOException, SdfDefinitionException, RecognitionException {
        Set<TextFile> grammar = new HashSet<TextFile>();

        SdfCreator sdfCreator = new SdfCreator();
        Module module = null;
        Set<String> terminals = null;


        for(Object fc: model.get_abstrSyntax()) {
            if(fc instanceof FileContainerDef){
                FileContainerDef fileCont = (FileContainerDef)fc;
                grammar.add(new TextFile(fileCont.get_name(), fileCont.get_file()));
            }
        }

        for(TextFile file: grammar){
            Reader reader;

            reader = new StringReader(file.getContent());
            terminals = StringChooserDialog.showStringChooserDialog("Select terminal sorts", (new SdfCreator()).getSortNames(reader));
            reader.close();

            reader = new StringReader(file.getContent());
            module = sdfCreator.processSdf(reader, terminals);
            reader.close();
        }

        return module;

    }
}
