package unifier.util;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

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

public class ExtensionChoosableFileFilter extends javax.swing.filechooser.FileFilter {
    private String description = null;
    private String exts = null;

    public ExtensionChoosableFileFilter(String aext, String desc) {
        exts = aext;
        description = desc;
    }

    public boolean accept(File f) {
        if (f == null)  return false;
        if (f.isDirectory()) return true;
        if (exts.compareTo("") == 0)  return true;

        String extension = getExtension(f);

        if (extension == null) return false;

        return (exts.indexOf("%" + extension + "%") != -1);
    }

    public String getExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1)
                return filename.substring(i + 1).toLowerCase();
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
