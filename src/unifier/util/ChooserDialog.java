package unifier.util;

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
import javax.swing.JDialog;
import java.util.HashSet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

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
public class ChooserDialog<E> extends JDialog {
    private ChooserDialog() {
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
                ChooserDialog_jButtonOK_actionAdapter(this));
        jListElements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneElements.setPreferredSize(new Dimension(200, 250));
        jButtonCancel.setToolTipText("Cancel");
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new
                ChooserDialog_jButtonCancel_actionAdapter(this));
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
    private ChooserDialog(
            String title,
            String okTooltip,
            String cancelTooltip,
            int selectionMode,
            Set<E> elements,
            DefaultListCellRenderer renderer){

        this();

        this.setTitle(title);
        jButtonOK.setToolTipText(okTooltip);
        jButtonCancel.setToolTipText(cancelTooltip);
        jListElements.setSelectionMode(selectionMode);
        jListElements.setCellRenderer(renderer);


        // dispose if input set is empty
        if(elements.size() == 0) this.dispose();

        for(Object elm: elements){
            ((DefaultListModel) jListElements.getModel()).addElement(elm);
        }

        jListElements.setSelectedIndex(0);
    }

    public static <E> Set<E> showChooserDialog (
            String title,
            String okTooltip,
            String cancelTooltip,
            int selectionMode,
            Set<E> elements,
            DefaultListCellRenderer renderer
            ){

        ChooserDialog dialog = new ChooserDialog<E>(title, okTooltip, cancelTooltip, selectionMode, elements, renderer);

        dialog.setTitle(title);

        dialog.pack();
        dialog.validate();
        dialog.setVisible(true);

        return dialog.getSelected();
    }



    private Set<E> selected = new HashSet<E>();

    private Set<E> getSelected(){
        return selected;
    }

    public void jButtonOK_actionPerformed(ActionEvent e) {
        if(!jListElements.isSelectionEmpty()){
            E [] arr;

            arr = (E[])jListElements.getSelectedValues();

            selected.clear();
            for(int i = 0; i <= (arr.length - 1); i++) selected.add(arr[i]);

            this.dispose();
        }
    }

    public void jButtonCancel_actionPerformed(ActionEvent e) {
        selected.clear();
        this.dispose();
    }
}




// Action adapters

class ChooserDialog_jButtonCancel_actionAdapter implements
        ActionListener {
    private ChooserDialog adaptee;
    ChooserDialog_jButtonCancel_actionAdapter(ChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }
}

class ChooserDialog_jButtonOK_actionAdapter implements ActionListener {
    private ChooserDialog adaptee;
    ChooserDialog_jButtonOK_actionAdapter(ChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOK_actionPerformed(e);
    }

}
