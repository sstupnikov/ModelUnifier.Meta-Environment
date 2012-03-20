package unifier.util;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import meta.ModelRegDef;
import java.awt.Component;
import java.sql.SQLException;
import meta.FileContainerDef;

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

// Renderer to render file containers (FileContainerDef) as elements of JList
// override method getListCellRendererComponent() so that
// name of a file container is shown as element of JList
public class TextFileCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
        JList list,
        Object value,   // value to display
        int index,      // cell index
        boolean iss,    // is the cell selected
        boolean chf)    // the list and the cell have the focus
    {
        if (value instanceof TextFile) {
            TextFile file = (TextFile)value;
            String fileName = "";

            fileName = file.getName();

            super.getListCellRendererComponent(list, fileName, index, iss, chf);
        }
        else {
            super.getListCellRendererComponent(list, value, index, iss, chf);
        }

        return this;
    }
}
