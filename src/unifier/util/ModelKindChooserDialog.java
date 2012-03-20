package unifier.util;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
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
import javax.swing.JRadioButton;
import java.awt.*;
import javax.swing.ButtonGroup;

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
public class ModelKindChooserDialog extends JDialog {
    private ModelKindChooserDialog() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void jbInit() throws Exception {
        this.setModal(true);
        this.setResizable(true);
        this.setTitle("Choose the kind of the model");
        this.getContentPane().setLayout(gridBagLayoutECD);
        jButtonOK.setToolTipText("Submit selected kind");
        jButtonOK.addActionListener(new
                ModelKindChooserDialog_jButtonOK_actionAdapter(this));
        jButtonOK.setText("OK");
        jPanelButtons.setLayout(flowLayoutButtons);
        jRadioButtonTarget.setToolTipText(
                "Target model is refined by some extension of the canonical model");
        jRadioButtonTarget.setActionCommand("jRadioButtonTarget");
        jRadioButtonTarget.setText("Target");
        jRadioButtonTarget.addActionListener(new
                ModelKindChooserDialog_jRadioButtonTarget_actionAdapter(this));
        jRadioButtonSource.setToolTipText(
                "Source model refines some extension of the canonical model");
        jRadioButtonSource.setActionCommand("Source");
        jRadioButtonSource.setSelected(true);
        jRadioButtonSource.setText("Source");
        jRadioButtonSource.addActionListener(new
                ModelKindChooserDialog_jRadioButtonSource_actionAdapter(this));
        jPanelButtons.setMinimumSize(new Dimension(250, 37));
        jPanelButtons.setPreferredSize(new Dimension(250, 37));
        jPanelButtons.add(jButtonOK);
        this.getContentPane().add(jRadioButtonTarget,
                                  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        this.getContentPane().add(jRadioButtonSource,
                                  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        this.getContentPane().add(jPanelButtons,
                                  new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
    }

    GridBagLayout gridBagLayoutECD = new GridBagLayout();
    JPanel jPanelButtons = new JPanel();
    FlowLayout flowLayoutButtons = new FlowLayout();
    JButton jButtonOK = new JButton();
    JRadioButton jRadioButtonTarget = new JRadioButton();
    JRadioButton jRadioButtonSource = new JRadioButton();

    // END GUI part


    public static boolean showModelKindChooserDialog(){
        ModelKindChooserDialog dialog = new ModelKindChooserDialog();

        dialog.pack();
        dialog.validate();
        dialog.setVisible(true);

        return dialog.isTarget;
    }


    private boolean isTarget = false;


    public void jButtonOK_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    public void jRadioButtonSource_actionPerformed(ActionEvent e) {
        jRadioButtonTarget.setSelected(false);
        isTarget = false;
    }

    public void jRadioButtonTarget_actionPerformed(ActionEvent e) {
        jRadioButtonSource.setSelected(false);
        isTarget = true;
    }

}


// Action adapters

class ModelKindChooserDialog_jRadioButtonTarget_actionAdapter implements
        ActionListener {
    private ModelKindChooserDialog adaptee;
    ModelKindChooserDialog_jRadioButtonTarget_actionAdapter(
            ModelKindChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jRadioButtonTarget_actionPerformed(e);
    }
}


class ModelKindChooserDialog_jRadioButtonSource_actionAdapter implements
        ActionListener {
    private ModelKindChooserDialog adaptee;
    ModelKindChooserDialog_jRadioButtonSource_actionAdapter(
            ModelKindChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jRadioButtonSource_actionPerformed(e);
    }
}


class ModelKindChooserDialog_jButtonOK_actionAdapter implements ActionListener {
    private ModelKindChooserDialog adaptee;
    ModelKindChooserDialog_jButtonOK_actionAdapter(ModelKindChooserDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOK_actionPerformed(e);
    }
}
