package unifier.util;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import meta.ModelRegDef;
import java.awt.Component;
import java.sql.SQLException;
import meta.TypeDef;
import meta.ElementDef;

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


// Renderer to render model registration cards (ModelRegDef) as elements of JList
// override method getListCellRendererComponent() so that
// name of a registration card is shown as element of JList
public class ElementDefCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
        JList list,
        Object value,   // value to display
        int index,      // cell index
        boolean iss,    // is the cell selected
        boolean chf)    // the list and the cell have the focus
    {
        if (value instanceof ElementDef) {
            ElementDef elm = (ElementDef)value;
            String elmName = "";

            try {
                elmName = elm.get_name();
            } catch (SQLException ex) {
            }

            super.getListCellRendererComponent(list, elmName, index, iss, chf);
        }
        else {
            super.getListCellRendererComponent(list, value, index, iss, chf);
        }

        return this;
    }
}
