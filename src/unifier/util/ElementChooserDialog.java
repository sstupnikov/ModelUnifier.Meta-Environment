package unifier.util;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;

import java.util.Set;
import meta.ElementDef;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

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
public class ElementChooserDialog extends JDialog {
    private ElementChooserDialog() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void jbInit() throws Exception {
        this.setModal(true);
        this.setTitle("Choose an element");
        this.getContentPane().setLayout(gridBagLayoutECD);
        jButtonOK.setToolTipText("Submit selected element");
        jButtonOK.addActionListener(new
                ElementChooserDialog_jButtonOK_actionAdapter(this));
        jListElements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneElements.setPreferredSize(new Dimension(200, 250));
        jButtonCancel.setToolTipText("Cancel translation");
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new
                ElementChooserDialog_jButtonCancel_actionAdapter(this));
        this.getContentPane().add(jScrollPaneElements,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneElements.getViewport().add(jListElements);
        jButtonOK.setText("OK");
        jPanelButtons.setLayout(flowLayoutButtons);
        this.getContentPane().add(jPanelButtons,
                                  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelButtons.add(jButtonOK);
        jPanelButtons.add(jButtonCancel);
        jListElements.setModel(new DefaultListModel());
        jListElements.setCellRenderer(new ElementDefCellRenderer());
    }

    GridBagLayout gridBagLayoutECD = new GridBagLayout();
    JScrollPane jScrollPaneElements = new JScrollPane();
    JPanel jPanelButtons = new JPanel();
    FlowLayout flowLayoutButtons = new FlowLayout();
    JButton jButtonOK = new JButton();
    JList jListElements = new JList();
    JButton jButtonCancel = new JButton();

    // END GUI part

    // selected element
    private ElementChooserDialog(Set elements){
        this();

        // dispose if input set is empty
        if(elements.size() == 0) this.dispose();

        for(Object elm: elements){
            if(elm instanceof ElementDef) ((DefaultListModel) jListElements.getModel()).addElement(elm);
        }

        jListElements.setSelectedIndex(0);
    }

    public static ElementDef showElementChooserDialog(String title, Set elements){
        ElementChooserDialog dialog = new ElementChooserDialog(elements);

        dialog.setTitle(title);

        dialog.pack();
        dialog.validate();
        dialog.setVisible(true);

        return dialog.getSelected();
    }





    private ElementDef selected;


    private ElementDef getSelected(){
        return selected;
    }

    public void jButtonOK_actionPerformed(ActionEvent e) {
        if(!jListElements.isSelectionEmpty()){
            selected = (ElementDef)jListElements.getSelectedValue();
            this.dispose();
        }
    }

    public void jButtonCancel_actionPerformed(ActionEvent e) {
        selected = null;
        this.dispose();
    }

}


// Action adapters

class ElementChooserDialog_jButtonCancel_actionAdapter implements
        ActionListener {
    private ElementChooserDialog adaptee;
    ElementChooserDialog_jButtonCancel_actionAdapter(ElementChooserDialog
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }
}

class ElementChooserDialog_jButtonOK_actionAdapter implements ActionListener {
    private ElementChooserDialog adaptee;
    ElementChooserDialog_jButtonOK_actionAdapter(ElementChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOK_actionPerformed(e);
    }
}
