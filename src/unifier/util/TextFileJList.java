package unifier.util;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.Set;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import meta.FileContainerDef;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import meta.DB;
import meta.Def;
import java.io.Writer;
import java.io.FileWriter;
import javax.swing.text.JTextComponent;

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
public class TextFileJList extends JList {
    public TextFileJList() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JTextComponent textComponent;

    // constructor aimed to set
    // specific list model and cell renderer
    public TextFileJList (JTextComponent textComponent){
        super();

        this.setModel(new DefaultListModel());
        this.setCellRenderer(new TextFileCellRenderer());

        this.textComponent = textComponent;
    }

    // last selected file
    // used to update edited file content before changing selection
    TextFile lastSelection;

    // It is supposed list is connected with  <textComponent>.
    // When mouse is clicked on list (possibly selection is changed),
    // then this method is to be called.
    // It put possibly modified text from <textComponent> into this.lastSelection.content,
    // set this.lastSelection as this.getSelectedValue(),
    // set <textComponent> text as this.lastSelection.content
    public void updateLastSelectedContent(){
        if(textComponent == null || this.isSelectionEmpty()) return;

        if (lastSelection != null) lastSelection.setContent(textComponent.getText());
        lastSelection = ((TextFile)this.getSelectedValue());
        textComponent.setText(lastSelection.getContent());
    }


    // show browse file dialog,
    // get file with specific extension,
    // comment the extension by description,
    // create new TextFile,
    // add TextFile to list
    public TextFile addFile(String extension, String description) throws
            FileNotFoundException, IOException {

        FileFilter ff;
        JFileChooser chooser = new JFileChooser();
        int retValue;
        TextFile textFile  = new TextFile("", "");

        ff = new ExtensionChoosableFileFilter(extension, description);
        chooser.setCurrentDirectory(new File("."));
        chooser.addChoosableFileFilter(ff);
        chooser.setFileFilter(ff);
        retValue = chooser.showOpenDialog(this);

        if (retValue == JFileChooser.APPROVE_OPTION) {
            File theFile = chooser.getSelectedFile();
            if (theFile != null && !theFile.isDirectory()) {
                BufferedReader reader = new BufferedReader(new FileReader(theFile));
                String line;
                String content;

                textFile.setName(theFile.getName());

                // read the first line
                line = reader.readLine();
                content = line;
                // read another lines
                line = reader.readLine();
                while(line != null){
                    content += "\n" + line;
                    line = reader.readLine();
                }
                textFile.setContent(content);

                ((DefaultListModel)this.getModel()).addElement(textFile);
            }
        }

        return textFile;
    }

    public void addFile(TextFile file){
        ((DefaultListModel)this.getModel()).addElement(file);
    }


    // export selected file
    public void exportSelectedFile(String extension, String description) throws
            IOException {
        if(this.isSelectionEmpty()) return;

        TextFile selection = ((TextFile)this.getSelectedValue());
        FileFilter ff;
        JFileChooser chooser = new JFileChooser();
        int retValue;

        // update content from tetx component
        selection.setContent(textComponent.getText());

        ff = new ExtensionChoosableFileFilter(extension, description);
        chooser.setCurrentDirectory(new File("."));
        chooser.addChoosableFileFilter(ff);
        chooser.setFileFilter(ff);
        retValue = chooser.showSaveDialog(this);

        if (retValue == JFileChooser.APPROVE_OPTION) {
            File theFile = chooser.getSelectedFile();
            if (theFile != null && !theFile.isDirectory()) {
                Writer writer = new FileWriter(theFile);

                writer.write(selection.getContent());
                writer.flush();
                writer.close();
            }
        }

    }

    // remove selected file from list
    public void removeSelectedFile(){
        if(!this.isSelectionEmpty()) {
            TextFile selection = ((TextFile)this.getSelectedValue());

            ((DefaultListModel)this.getModel()).remove(this.getSelectedIndex());

            if(textComponent != null){
                textComponent.setText("");
            }
        }
    }

    // set <files> should contain FileContainer instances
    //
    // create TextFile instance for every FileContainer in the set,
    // add it to the list
    public void fillFromFileContainerSet(Set<FileContainerDef> files) throws SQLException {
        for(Object fc: files) {
            if(fc instanceof FileContainerDef){
                FileContainerDef fileCont = (FileContainerDef)fc;
                TextFile textFile = new TextFile(fileCont.get_name(), fileCont.get_file());

                ((DefaultListModel)this.getModel()).addElement(textFile);
            }
        }
    }

    // set <files> should contain TextFile instances
    //
    // add it to the list
    public void fillFromTextFileSet(Set<TextFile> files) throws SQLException {
        for(TextFile f: files) {
            ((DefaultListModel)this.getModel()).addElement(f);
        }
    }


    // load text files from list into repository <db>, set <set>:
    // delete all elements from <set> and create new ones
    // according to elements of list
    public void loadFilesToRepository(DB db, Set set) throws SQLException {
        DefaultListModel model = (DefaultListModel)this.getModel();

        for(Object fc: set){
            if (fc instanceof Def) {
                ((Def) fc).delete();
            }
        }

        for (int i = 0; i < model.getSize(); i++) {
            FileContainerDef fc = db.new_fileContainer();
            fc.set_name(((TextFile)model.get(i)).getName());
            fc.set_file(((TextFile)model.get(i)).getContent());
            set.add(fc);
        }
    }

    private void jbInit() throws Exception {
    }

}
