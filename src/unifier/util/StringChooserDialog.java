package unifier.util;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import meta.ElementDef;
import java.awt.Insets;
import java.util.Set;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.DefaultListCellRenderer;
import java.util.HashSet;

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
public class StringChooserDialog extends JDialog {
    private StringChooserDialog() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void jbInit() throws Exception {
        this.setModal(true);
        this.setTitle("Choose element");
        this.getContentPane().setLayout(gridBagLayoutECD);
        jButtonOK.setToolTipText("Submit selected elements");
        jButtonOK.addActionListener(new
                StringChooserDialog_jButtonOK_actionAdapter(this));
        jListElements.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPaneElements.setPreferredSize(new Dimension(200, 250));
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new
                StringChooserDialog_jButtonCancel_actionAdapter(this));
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
        jListElements.setCellRenderer(new DefaultListCellRenderer());
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
    private StringChooserDialog(Set<String> elements){
        this();

        // dispose if input set is empty
        if(elements.size() == 0) this.dispose();

        for(String elm: elements){
            ((DefaultListModel) jListElements.getModel()).addElement(elm);
        }

    }

    public static Set<String> showStringChooserDialog(String title, Set<String> elements){
        StringChooserDialog dialog = new StringChooserDialog(elements);

        dialog.setTitle(title);

        dialog.pack();
        dialog.validate();
        dialog.setVisible(true);

        return dialog.getSelected();
    }


    private Set<String> selected = new HashSet<String>();


    private Set<String> getSelected(){
        return selected;
    }

    public void jButtonOK_actionPerformed(ActionEvent e) {
        if(!jListElements.isSelectionEmpty()){

            for(Object obj: jListElements.getSelectedValues())
                selected.add((String)obj);

            this.dispose();
        }
    }

    public void jButtonCancel_actionPerformed(ActionEvent e) {
        selected = null;
        this.dispose();
    }

}


// Action adapters

class StringChooserDialog_jButtonCancel_actionAdapter implements
        ActionListener {
    private StringChooserDialog adaptee;
    StringChooserDialog_jButtonCancel_actionAdapter(StringChooserDialog
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }
}

class StringChooserDialog_jButtonOK_actionAdapter implements ActionListener {
    private StringChooserDialog adaptee;
    StringChooserDialog_jButtonOK_actionAdapter(StringChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOK_actionPerformed(e);
    }
}
