package unifier;

import javax.swing.JSplitPane;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import java.util.logging.Logger;
import meta.DB;
import meta.ExtensionRegDef;
import javax.swing.DefaultListModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import meta.ModelRegDef;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import unifier.util.ExtensionRegDefCellRenderer;

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
public class ExtensionExplorer extends CenteredFrame {
    public ExtensionExplorer() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        logger = Logger.getLogger("unifier.ExtensionExplorer");
    }


    public ExtensionExplorer(DB db) {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        logger = Logger.getLogger("unifier.ExtensionExplorer");
        this.db = db;

        center();

        showExistingExstensions();
    }

    private void jbInit() throws Exception {
        this.setJMenuBar(jMenuBarExtensionExplorer);
        this.setTitle("Extension Explorer");
        this.getContentPane().setLayout(gridBagLayoutEE);
        jButtonOpen.setToolTipText(
                "Open registration card of selected extension");
        jButtonOpen.setText("Open");
        jButtonOpen.addActionListener(new
                ExtensionExplorer_jButtonOpen_actionAdapter(this));
        jSplitPaneExtensionExplorer.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPaneExtensionExplorer.setPreferredSize(new Dimension(700, 500));
        jTextAreaExtensionDescription.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jTextAreaExtensionDescription.setEditable(false);
        jTextAreaExtensionDescription.setText("");
        jScrollPaneExtensionExplorer.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Extensions"));
        jScrollPaneExtensionDescription.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Description"));
        jButtonSubmitAsExtendedModel.setEnabled(false);
        jButtonSubmitAsExtendedModel.setActionCommand(
                "Submit as Extended Model");
        jButtonSubmitAsExtendedModel.setText("Submit as Extended Model");
        jButtonSubmitAsExtendedModel.addActionListener(new
                ExtensionExplorer_jButtonSubmitAsExtendingModel_actionAdapter(this));
        jListExtensionExplorer.addMouseListener(new
                ExtensionExplorer_jListExtensionExplorer_mouseAdapter(this));
        jButtonSubmitAsRefinedExtension.setEnabled(false);
        jButtonSubmitAsRefinedExtension.setText("Submit as Refined Extension");
        jButtonSubmitAsRefinedExtension.addActionListener(new
                ExtensionExplorer_jButtonSubmitAsRefinedExtension_actionAdapter(this));
        jMenuActions.setText("Actions");
        jMenuItemSubmitExtendedModel.setEnabled(false);
        jMenuItemSubmitExtendedModel.setActionCommand(
                "Submit as extended model ...");
        jMenuItemSubmitExtendedModel.setText("Submit as extended model ...");
        jMenuItemSubmitExtendedModel.addActionListener(new
                ExtensionExplorer_jMenuItemSubmitExtendingModel_actionAdapter(this));
        jMenuItemSubmitRefinedExtension.setEnabled(false);
        jMenuItemSubmitRefinedExtension.setText(
                "Submit as refined extension ...");
        jMenuItemSubmitRefinedExtension.addActionListener(new
                ExtensionExplorer_jMenuItemSubmitRefinedExtension_actionAdapter(this));
        jPanelExtensionExplorerButtons.add(jButtonOpen);
        jPanelExtensionExplorerButtons.add(jButtonSubmitAsExtendedModel);
        jPanelExtensionExplorerButtons.add(jButtonSubmitAsRefinedExtension);
        jSplitPaneExtensionExplorer.add(jScrollPaneExtensionExplorer, JSplitPane.LEFT);
        jScrollPaneExtensionExplorer.getViewport().add(jListExtensionExplorer);
        jSplitPaneExtensionExplorer.add(jScrollPaneExtensionDescription, JSplitPane.RIGHT);
        jScrollPaneExtensionDescription.getViewport().add(
                jTextAreaExtensionDescription);
        this.getContentPane().add(jSplitPaneExtensionExplorer,
                                  new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        this.getContentPane().add(jPanelExtensionExplorerButtons,
                                  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jMenuBarExtensionExplorer.add(jMenuActions);
        jMenuActions.add(jMenuItemSubmitExtendedModel);
        jMenuActions.add(jMenuItemSubmitRefinedExtension);
        // non-generated part
        jListExtensionExplorer.setCellRenderer(new ExtensionRegDefCellRenderer());
        jListExtensionExplorer.setModel(new DefaultListModel());
        setDividersLocations();
    }

    GridBagLayout gridBagLayoutEE = new GridBagLayout();
    JPanel jPanelExtensionExplorerButtons = new JPanel();
    JButton jButtonOpen = new JButton();
    JSplitPane jSplitPaneExtensionExplorer = new JSplitPane();
    JScrollPane jScrollPaneExtensionExplorer = new JScrollPane();
    JScrollPane jScrollPaneExtensionDescription = new JScrollPane();
    JList jListExtensionExplorer = new JList();
    JTextArea jTextAreaExtensionDescription = new JTextArea();
    JButton jButtonSubmitAsExtendedModel = new JButton();
    JButton jButtonSubmitAsRefinedExtension = new JButton();

    public void jListExtensionExplorer_mouseClicked(MouseEvent e) {
        if(!jListExtensionExplorer.isSelectionEmpty()){
            ExtensionRegDef selection = (ExtensionRegDef)jListExtensionExplorer.getSelectedValue();

            try {
                jTextAreaExtensionDescription.setText(selection.get_description());
            } catch (SQLException ex) {
                logger.info(ex.toString());
            }
        }
    }

    public void jButtonOpen_actionPerformed(ActionEvent e) {
        try {
            openExtension();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonSubmitAsExtendingModel_actionPerformed(ActionEvent e) {
        try {
            submitExtendedModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonSubmitAsRefinedExtension_actionPerformed(ActionEvent e) {
        try {
            submitRefinedModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jMenuItemSubmitExtendingModel_actionPerformed(ActionEvent e) {
        try {
            submitExtendedModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jMenuItemSubmitRefinedExtension_actionPerformed(ActionEvent e) {
        try {
            submitRefinedModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }


    // END GUI part

    // START GUI support part

    // set dividers locations
    private void setDividersLocations(){
        jSplitPaneExtensionExplorer.setDividerLocation(150);
    }

    // END GUI support part

    // START Implementation part

    // constructor used to search for extended model
    // initiated by ExtensionRegistrar
    public ExtensionExplorer(ExtensionRegistrar parent, ExtensionRegDef extension) {
        this((DB)extension._db());

        parentExtensionRegistrar = parent;
        extendingModel = extension;

        jButtonSubmitAsExtendedModel.setEnabled(true);
        jMenuItemSubmitExtendedModel.setEnabled(true);
    }

    // constructor used to search for extension to be target or source model
    // initiated by ModelRegistrar
    public ExtensionExplorer(ModelRegistrar parent, ModelRegDef model) throws  SQLException {
        this((DB)model._db());

        parentModelRegistrar = parent;
        refiningModel = model;

        if(refiningModel.get_target()){
           jButtonSubmitAsRefinedExtension.setText("Submit as source model");
           jMenuItemSubmitRefinedExtension.setText("Submit as source model ...");
        }
        else {
            jButtonSubmitAsRefinedExtension.setText("Submit as target model");
            jMenuItemSubmitRefinedExtension.setText("Submit as target model ...");
        }

        jButtonSubmitAsRefinedExtension.setEnabled(true);
        jMenuItemSubmitRefinedExtension.setEnabled(true);
    }


    // FIELDS
    private Logger logger;

    private DB db;

    // Extension registrar having created this extension explorer to search for
    // extended model
    private ExtensionRegistrar parentExtensionRegistrar;
    // Extension to find a model to extend
    private ExtensionRegDef extendingModel;

    // Model registrar having created this extension explorer to search for
    // refined model
    private ModelRegistrar parentModelRegistrar;
    // Model to find an extension to refine
    private ModelRegDef refiningModel;
    JMenuBar jMenuBarExtensionExplorer = new JMenuBar();
    JMenu jMenuActions = new JMenu();
    JMenuItem jMenuItemSubmitExtendedModel = new JMenuItem();
    JMenuItem jMenuItemSubmitRefinedExtension = new JMenuItem();

    // METHODS
    private void showExistingExstensions() {
        // add samples from model into samples list
        for (Object e : db.get_extensionReg()) {
            ExtensionRegDef ext = (ExtensionRegDef) e;
            ((DefaultListModel) jListExtensionExplorer.getModel()).addElement(ext);
        }

    }

    private void openExtension() throws SQLException {
        // Skip opening in the following situation:
        // an extension was found and opened using ExtensionExplorer,
        // after that the extension was deleted.
        // The condition prevents its second opening with the halp of the same
        // instance of ExtensionExplorer.
        if(((ExtensionRegDef)jListExtensionExplorer.getSelectedValue()).get_name() == null) return;

        if(!jListExtensionExplorer.isSelectionEmpty()){

            ExtensionRegistrar registrar;

            logger.info("Opening the extension ...");
            registrar = new ExtensionRegistrar((ExtensionRegDef)jListExtensionExplorer.getSelectedValue());

            jListExtensionExplorer.clearSelection();
        }
    }

    private void submitExtendedModel() throws SQLException {
        if(!jListExtensionExplorer.isSelectionEmpty() &&  extendingModel != null){
            extendingModel.set_extending((ExtensionRegDef)jListExtensionExplorer.getSelectedValue());

            parentExtensionRegistrar.showExtendedModel();
            this.dispose();
        }
    }

    private void submitRefinedModel() throws SQLException {
        if(!jListExtensionExplorer.isSelectionEmpty() &&  refiningModel != null){
            refiningModel.set_refinedExtension((ExtensionRegDef)jListExtensionExplorer.getSelectedValue());
            parentModelRegistrar.showRefinedExtensionName();
            this.dispose();
        }
    }

}


class ExtensionExplorer_jMenuItemSubmitExtendingModel_actionAdapter implements
        ActionListener {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jMenuItemSubmitExtendingModel_actionAdapter(
            ExtensionExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemSubmitExtendingModel_actionPerformed(e);
    }
}


class ExtensionExplorer_jMenuItemSubmitRefinedExtension_actionAdapter implements
        ActionListener {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jMenuItemSubmitRefinedExtension_actionAdapter(
            ExtensionExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemSubmitRefinedExtension_actionPerformed(e);
    }
}


class ExtensionExplorer_jButtonSubmitAsRefinedExtension_actionAdapter implements
        ActionListener {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jButtonSubmitAsRefinedExtension_actionAdapter(
            ExtensionExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSubmitAsRefinedExtension_actionPerformed(e);
    }
}


class ExtensionExplorer_jButtonSubmitAsExtendingModel_actionAdapter implements
        ActionListener {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jButtonSubmitAsExtendingModel_actionAdapter(
            ExtensionExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSubmitAsExtendingModel_actionPerformed(e);
    }
}


class ExtensionExplorer_jButtonOpen_actionAdapter implements ActionListener {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jButtonOpen_actionAdapter(ExtensionExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOpen_actionPerformed(e);
    }
}


class ExtensionExplorer_jListExtensionExplorer_mouseAdapter extends
        MouseAdapter {
    private ExtensionExplorer adaptee;
    ExtensionExplorer_jListExtensionExplorer_mouseAdapter(ExtensionExplorer
            adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListExtensionExplorer_mouseClicked(e);
    }
}
