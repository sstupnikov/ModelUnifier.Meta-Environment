package unifier.util;

import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.DefaultListModel;
import java.io.File;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;

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
public class FileJTextArea extends JTextArea {

    public void importTextFromFile(String extension, String description) throws FileNotFoundException, IOException {
        FileFilter ff;
        JFileChooser chooser = new JFileChooser();
        int retValue;

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

                // read the first line
                line = reader.readLine();
                content = line;
                // read another lines
                line = reader.readLine();
                while(line != null){
                    content += "\n" + line;
                    line = reader.readLine();
                }

                this.setText(content);
            }
        }

    }

    public void exportTextToFile(String extension, String description) throws IOException {
        FileFilter ff;
        JFileChooser chooser = new JFileChooser();
        int retValue;

        ff = new ExtensionChoosableFileFilter(extension, description);
        chooser.setCurrentDirectory(new File("."));
        chooser.addChoosableFileFilter(ff);
        chooser.setFileFilter(ff);
        retValue = chooser.showSaveDialog(this);

        if (retValue == JFileChooser.APPROVE_OPTION) {
            File theFile = chooser.getSelectedFile();
            if (theFile != null && !theFile.isDirectory()) {
                Writer writer = new FileWriter(theFile);

                writer.write(this.getText());
                writer.flush();
                writer.close();
            }
        }

    }
}
