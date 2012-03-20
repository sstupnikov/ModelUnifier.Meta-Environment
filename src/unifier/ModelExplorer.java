package unifier;

import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import meta.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import db.*;
import java.util.Set;
import java.util.Vector;
import javax.swing.ListModel;
import javax.swing.DefaultListCellRenderer;
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import unifier.util.ModelRegDefCellRenderer;
import java.awt.event.KeyEvent;

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
public class ModelExplorer extends CenteredFrame {

    public ModelExplorer(DB adb) {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        logger = Logger.getLogger("unifier.ModelExplorer");
        db = adb;
    }

    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Model Explorer");
        this.getContentPane().setLayout(gridBagLayoutMR);
        jPanelSearchCriteria.setLayout(gridBagLayoutSC);
        jPanelSearchResults.setLayout(gridBagLayoutMS);
        jLabelShortTitle.setText("Short Title:");
        jTextFieldShortTitle.setText("");
        jLabelFullTitle.setText("Full Title:");
        jTextFieldFullTitle.setText("");
        jPanelSearchCriteria.setBorder(null);
        jPanelSearchCriteria.setToolTipText("");
        jPanelSyntax.setBorder(new TitledBorder(BorderFactory.
                                                createEtchedBorder(Color.white,
                new Color(148, 145, 140)), "Concrete Syntax"));
        jPanelSyntax.setToolTipText("");
        jPanelSyntax.setLayout(gridBagLayoutRCS);
        jLabelSyntaxDocTitle.setText("Document title:");
        jLabelSyntaxLink.setText("Link:");
        jTextFieldSyntaxDocTitle.setText("");
        jTextFieldSyntaxLink.setText("");
        jPanelSemantics.setLayout(gridBagLayoutRCSem);
        jPanelSemantics.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Sematics"));
        jLabelSemanticsDocTitle.setText("Document title:");
        jTextFieldSemanticsDocTitle.setText("");
        jLabelSemanticsLink.setText("Link:");
        jTextFieldSemanticsLink.setText("");
        jButtonSearchModel.setText("Search");
        jButtonSearchModel.setMnemonic(KeyEvent.VK_S);
        jButtonSearchModel.addActionListener(new
                ModelExplorer_jButtonSearchModel_actionAdapter(this));
        jPanelSCButtons.setLayout(flowLayoutSCB);
        jPanelSCButtons.setBorder(BorderFactory.createEtchedBorder(Color.white,
                new Color(148, 145, 140)));
        jButtonOpenModel.setText("Open");
        jButtonOpenModel.setMnemonic(KeyEvent.VK_O);
        jButtonOpenModel.addActionListener(new
                ModelExplorer_jButtonOpenModel_actionAdapter(this));
        jTabbedPaneActivities.setMinimumSize(new Dimension(400, 500));
        jTabbedPaneActivities.setPreferredSize(new Dimension(400, 500));
        jPanelLegend.setBorder(new TitledBorder(BorderFactory.
                                                createEtchedBorder(Color.white,
                new Color(148, 145, 140)), "Mask Legend"));
        jPanelLegend.setLayout(gridBagLayout1);
        jLabelMask1.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
        jLabelMask1.setText("%");
        jLabelMask1Note.setText("  -  any sequence of symbols");
        jLabelMask2.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
        jLabelMask2.setText("_");
        jLabelMask2Note.setText(" -  any symbol");
        jListSearchResults.setCellRenderer(null);
        jListSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTabbedPaneActivities.add(jPanelSearchCriteria, "Search Criteria");
        jTabbedPaneActivities.add(jPanelSearchResults, "Search Results");
        jPanelSemantics.add(jLabelSemanticsDocTitle,
                            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jTextFieldSemanticsDocTitle,
                            new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jLabelSemanticsLink,
                            new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jTextFieldSemanticsLink,
                            new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSCButtons.add(jButtonSearchModel);
        jPanelSearchResults.add(jPanelModelSyntaxButtons,
                              new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelModelSyntaxButtons.add(jButtonOpenModel);
        jPanelSearchResults.add(jScrollPaneSearchResults,
                              new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneSearchResults.getViewport().add(jListSearchResults);
        this.getContentPane().add(jTabbedPaneActivities,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelLegend.add(jLabelMask2,
                         new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelLegend.add(jLabelMask1Note,
                         new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(0, 0, 0, 0), 0, 0));
        jPanelLegend.add(jLabelMask1,
                         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 0, 5), 0, 0));
        jPanelLegend.add(jLabelMask2Note,
                         new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jTextFieldFullTitle,
                                 new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jLabelShortTitle,
                                 new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jLabelFullTitle,
                                 new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jPanelSyntax,
                                 new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 0, 5), 0, 0));
        jPanelSearchCriteria.add(jPanelSemantics,
                                 new GridBagConstraints(0, 4, 3, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 0, 5), 0, 0));
        jPanelSearchCriteria.add(jPanelSCButtons,
                                 new GridBagConstraints(0, 6, 3, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jTextFieldShortTitle,
                                 new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jLabelSyntaxDocTitle,
                         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jLabelSyntaxLink,
                         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jTextFieldSyntaxDocTitle,
                         new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jTextFieldSyntaxLink,
                         new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSearchCriteria.add(jPanelLegend,
                                 new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jListSearchResults.setCellRenderer(new ModelRegDefCellRenderer());
    }

    GridBagLayout gridBagLayoutMR = new GridBagLayout();
    JTabbedPane jTabbedPaneActivities = new JTabbedPane();
    JPanel jPanelSearchCriteria = new JPanel();
    GridBagLayout gridBagLayoutSC = new GridBagLayout();
    JPanel jPanelSearchResults = new JPanel();
    GridBagLayout gridBagLayoutMS = new GridBagLayout();
    JLabel jLabelShortTitle = new JLabel();
    JTextField jTextFieldShortTitle = new JTextField();
    JLabel jLabelFullTitle = new JLabel();
    JTextField jTextFieldFullTitle = new JTextField();
    JPanel jPanelSyntax = new JPanel();
    GridBagLayout gridBagLayoutRCS = new GridBagLayout();
    JLabel jLabelSyntaxDocTitle = new JLabel();
    JLabel jLabelSyntaxLink = new JLabel();
    JTextField jTextFieldSyntaxDocTitle = new JTextField();
    JTextField jTextFieldSyntaxLink = new JTextField();
    JPanel jPanelSemantics = new JPanel();
    GridBagLayout gridBagLayoutRCSem = new GridBagLayout();
    JLabel jLabelSemanticsDocTitle = new JLabel();
    JTextField jTextFieldSemanticsDocTitle = new JTextField();
    JLabel jLabelSemanticsLink = new JLabel();
    JTextField jTextFieldSemanticsLink = new JTextField();
    JPanel jPanelSCButtons = new JPanel();
    JButton jButtonSearchModel = new JButton();
    FlowLayout flowLayoutSCB = new FlowLayout();
    JPanel jPanelModelSyntaxButtons = new JPanel();
    JScrollPane jScrollPaneSearchResults = new JScrollPane();
    JButton jButtonOpenModel = new JButton();
    JList jListSearchResults = new JList();
    JPanel jPanelLegend = new JPanel();
    JLabel jLabelMask1 = new JLabel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel jLabelMask1Note = new JLabel();
    JLabel jLabelMask2 = new JLabel();
    JLabel jLabelMask2Note = new JLabel();


    public void jButtonSearchModel_actionPerformed(ActionEvent e) {
        searchForModel();
    }

    public void jButtonOpenModel_actionPerformed(ActionEvent e) {
        try {
            openModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }


    // END GUI part


    // FIELDS
    private Logger logger;

    private DB db;


    // METHODS

    private void searchForModel(){
        Set result;
        Condition cond = null;

        // stub condition - any short title
        cond = new Like(ModelRegDef._name, "%");

        // create condition according to masks
        if(jTextFieldShortTitle.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._name, jTextFieldShortTitle.getText().trim()));
        }
        if(jTextFieldFullTitle.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._fullTitle, jTextFieldFullTitle.getText().trim()));
        }
        if(jTextFieldSyntaxDocTitle.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._syntDocTitle, jTextFieldSyntaxDocTitle.getText().trim()));
        }
        if(jTextFieldSyntaxLink.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._syntDocURI, jTextFieldSyntaxLink.getText().trim()));
        }
        if(jTextFieldSemanticsDocTitle.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._semDocTitle, jTextFieldSemanticsDocTitle.getText().trim()));
        }
        if(jTextFieldSemanticsLink.getText().trim().compareTo("") != 0){
            cond =  new And(cond, new Like(ModelRegDef._semDocURI, jTextFieldSemanticsLink.getText().trim()));
        }

        // create query and get result
        result = db.get_modelReg(new Query(cond));

        // update search result list
        DefaultListModel listModel = new DefaultListModel();
        for(Object m: result) {
            ModelRegDef model = (ModelRegDef)m;
            listModel.addElement(model);
        }
        jListSearchResults.setModel(listModel);
        jListSearchResults.clearSelection();

        // switch to search result tab
        jTabbedPaneActivities.setSelectedIndex(1);
    }

    private void openModel() throws SQLException {
        // Skip opening in the following situation:
        // a model was found and opened using ModelExplorer,
        // after that it was deleted.
        // The condition prevents its second opening with the halp of the same
        // instance of ModelExplorer.
        if(((ModelRegDef)jListSearchResults.getSelectedValue()).get_name() == null) return;

        if(!jListSearchResults.isSelectionEmpty()){

            ModelRegistrar registrar;

            logger.info("Opening the model ...");
            registrar = new ModelRegistrar((ModelRegDef)jListSearchResults.getSelectedValue());

            jListSearchResults.clearSelection();
        }
    }

}



// Action Adapters

class ModelExplorer_jButtonSearchModel_actionAdapter implements ActionListener {
    private ModelExplorer adaptee;
    ModelExplorer_jButtonSearchModel_actionAdapter(ModelExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSearchModel_actionPerformed(e);
    }
}

class ModelExplorer_jButtonOpenModel_actionAdapter implements ActionListener {
    private ModelExplorer adaptee;
    ModelExplorer_jButtonOpenModel_actionAdapter(ModelExplorer adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOpenModel_actionPerformed(e);
    }
}

