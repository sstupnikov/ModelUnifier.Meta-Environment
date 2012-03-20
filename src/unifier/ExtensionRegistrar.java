package unifier;

import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.*;
import javax.swing.JFrame;
import java.util.logging.Logger;
import meta.DB;
import meta.ModelRegDef;
import java.sql.SQLException;
import meta.ExtensionRegDef;
import javax.swing.JOptionPane;
import java.io.StringReader;
import java.io.StringWriter;
import meta.ModuleDef;
import SynthesisLoaderClasses.SynthesisLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import unifier.util.TextFileJList;
import unifier.util.RepositoryCleaner;
import unifier.util.FormattingWriter;
import unifier.util.DefaultFormattingWriter;
import unifier.asfsdf.SdfDefinitionException;
import unifier.asfsdf.Module;
import unifier.rsm.ReferenceSchemaModelException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import db.Like;
import meta.SchemaDef;
import java.util.Set;
import unifier.util.ElementChooserDialog;
import db.Query;
import unifier.util.StringChooserDialog;
import unifier.util.TextFile;
import unifier.asfsdf.SdfCreator;
import org.antlr.runtime.RecognitionException;
import org.ipi.schemaeditor.SchemaEditorGui;

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
public class ExtensionRegistrar extends CenteredFrame {
    public ExtensionRegistrar(DB db) throws SQLException {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        logger = Logger.getLogger("unifier.ExtensionRegistrar");
        this.db = db;

    }


    public ExtensionRegistrar(ExtensionRegDef extension) throws SQLException {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        logger = Logger.getLogger("unifier.ExtensionRegistrar");

        db = (DB)extension._db();
        this.extension = extension;
        openExtension();
    }


    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Extension Registrar");
        this.addWindowListener(new ExtensionRegistrar_this_windowAdapter(this));
        this.getContentPane().setLayout(gridBagLayoutMR);
        jPanelRegCard.setLayout(gridBagLayoutRC);
        jPanelExtensionSyntax.setLayout(gridBagLayoutMS);
        jLabelTitle.setText("Title:");
        jTextFieldShortTitle.setText("");
        jPanelRegCard.setBorder(null);
        jPanelRegCard.setToolTipText("");
        jButtonUpdateRegCard.setToolTipText(
                "Update registartion card data in the model registry");
        jButtonUpdateRegCard.setText("Update");
        jButtonUpdateRegCard.addActionListener(new
                ExtensionRegistrar_jButtonUpdateRegCard_actionAdapter(this));
        jPanelRCButtons.setLayout(flowLayoutRCB);
        jButtonDeleteCard.setToolTipText(
                "Remove current registration card from registry");
        jButtonDeleteCard.setText("Delete");
        jButtonDeleteCard.addActionListener(new
                ExtensionRegistrar_jButtonDeleteCard_actionAdapter(this));
        jPanelRCButtons.setBorder(BorderFactory.createEtchedBorder(Color.white,
                new Color(148, 145, 140)));
        jButtonBrowseModelSyntaxFile.setToolTipText(
                "Browse a file with model abstract syntax (SDF)");
        jButtonBrowseModelSyntaxFile.setText("Browse");
        jButtonBrowseModelSyntaxFile.addActionListener(new
                ExtensionRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter(this));
        jButtonUpdateModelSyntax.setToolTipText(
                "Update syntax in the model registry");
        jButtonUpdateModelSyntax.setText("Update");
        jButtonUpdateModelSyntax.addActionListener(new
                ExtensionRegistrar_jButtonUpdateModelSyntax_actionAdapter(this));
        jTabbedPaneActivities.setMinimumSize(new Dimension(855, 520));
        jTabbedPaneActivities.setPreferredSize(new Dimension(960, 750));
        jPanelSemantics.setLayout(gridBagLayoutAMNS);
        jButtonUpdateAmnSemantics.setToolTipText(
                "Update semantics in the model registry");
        jButtonUpdateAmnSemantics.setText("Update");
        jButtonUpdateAmnSemantics.addActionListener(new
                ExtensionRegistrar_jButtonUpdateAmnSemantics_actionAdapter(this));
        jTextAreaVerbalSemantics.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaVerbalSemantics.setText("");
        jScrollPaneVerbalSemantics.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Verbal Semantics"));
        jPanelReferenceSchema.setLayout(gridBagLayoutVOD);
        jButtonCreateReferenceSchemaTemplate.setToolTipText(
                "Create reference schema template on the base of model abstract syntax");
        jButtonCreateReferenceSchemaTemplate.setActionCommand(
                "Create Reference Schema Template");
        jButtonCreateReferenceSchemaTemplate.setText("Create");
        jButtonCreateReferenceSchemaTemplate.addActionListener(new
                ExtensionRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter(this));
        jScrollPaneReferenceSchemaTemplate.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Reference Schema Template"));
        jTextAreaReferenceSchemaTemplate.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaReferenceSchemaTemplate.setEditable(true);
        jTextAreaReferenceSchemaTemplate.setText("");
        jButtonVODUpdate.setToolTipText(
                "Update reference schema specifications in model registry");
        jButtonVODUpdate.setText("Update");
        jButtonVODUpdate.addActionListener(new
                ExtensionRegistrar_jButtonVODUpdate_actionAdapter(this));
        jButtonVODEdit.setToolTipText("Open model reference schema in editor");
        jButtonVODEdit.setText("Edit");
        jButtonVODEdit.addActionListener(new
                ExtensionRegistrar_jButtonVODEdit_actionAdapter(this));
        jSplitPaneReferenceSchema.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPaneReferenceSchema.setOneTouchExpandable(true);
        jTextAreaModelSyntax.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaModelSyntax.setText("");
        jScrollPaneModelSyntaxFiles.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Files"));
        jScrollPaneModelSyntax.setBorder(BorderFactory.createEmptyBorder());
        jSplitPaneSemantics.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPaneSemantics.setOneTouchExpandable(true);
        jSplitPaneModelSyntax.setOneTouchExpandable(true);
        jPanelReferenceSchemaEditor.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Reference Schema  Editor"));
        jPanelReferenceSchemaEditor.setLayout(borderLayoutSchemaEditor);
        jTextAreaVerbalAmnSemantics.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaVerbalAmnSemantics.setToolTipText("");
        jScrollPaneVerbalAmnSemantics.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Verbal AMN Semantics"));
        jLabelTranslator2AmnVersion.setText("Translator into AMN version:");
        jTextFieldTranslator2AmnVersion.setText("");
        jScrollPaneExtensionDescription.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Description"));
        jButtonDeleteModelSyntaxFile.setToolTipText(
                "Delete a file with model abstract syntax");
        jButtonDeleteModelSyntaxFile.setText("Delete");
        jButtonDeleteModelSyntaxFile.addActionListener(new
                ExtensionRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter(this));
        jTextAreaExtensionDescription.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jLabelExtendedModel.setText("Extended Model:");
        jTextFieldExtendedModel.setEditable(false);
        jTextFieldExtendedModel.setText("");
        jButtonExploreExtension.setToolTipText(
                "Explore extensions to look for appropriate extended model");
        jButtonExploreExtension.setText("Explore");
        jButtonExploreExtension.addActionListener(new
                ExtensionRegistrar_jButtonExploreExtension_actionAdapter(this));
        jButtonExportSyntaxFile.setToolTipText(
                "Export a file with model abstract syntax");
        jButtonExportSyntaxFile.setText("Export");
        jButtonExportSyntaxFile.addActionListener(new
                ExtensionRegistrar_jButtonExportSyntaxFile_actionAdapter(this));
        jListModelSyntaxFiles.addMouseListener(new
                ExtensionRegistrar_jListModelSyntaxFiles_mouseAdapter(this));
        jButtonBrowseReferenceSchema.setToolTipText(
                "Browse module to be reference schema in repository");
        jButtonBrowseReferenceSchema.setText("Browse");
        jButtonBrowseReferenceSchema.addActionListener(new
                ExtensionRegistrar_jButtonBrowseReferenceSchema_actionAdapter(this));
        jPanelRCButtons.add(jButtonUpdateRegCard);
        jPanelRCButtons.add(jButtonDeleteCard);
        jScrollPaneExtensionDescription.getViewport().add(
                jTextAreaExtensionDescription);
        jTabbedPaneActivities.add(jPanelRegCard, "Registration Card");
        jTabbedPaneActivities.add(jPanelExtensionSyntax, "Syntax");
        jTabbedPaneActivities.add(jPanelSemantics, "Semantics");
        jPanelModelSyntaxButtons.add(jButtonBrowseModelSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonDeleteModelSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonExportSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonUpdateModelSyntax);
        jPanelVODButtons.add(jButtonCreateReferenceSchemaTemplate);
        jPanelVODButtons.add(jButtonVODEdit);
        jPanelVODButtons.add(jButtonBrowseReferenceSchema);
        jPanelVODButtons.add(jButtonVODUpdate);
        jPanelReferenceSchema.add(jSplitPaneReferenceSchema, new
                                        GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 5, 5, 5), 0, 0));
        jSplitPaneReferenceSchema.add(jScrollPaneReferenceSchemaTemplate, JSplitPane.LEFT);
        jSplitPaneReferenceSchema.add(jPanelReferenceSchemaEditor,
                                           JSplitPane.RIGHT);
        jScrollPaneReferenceSchemaTemplate.getViewport().add(jTextAreaReferenceSchemaTemplate);

        jSplitPaneModelSyntax.add(jScrollPaneModelSyntaxFiles, JSplitPane.LEFT);
        jSplitPaneModelSyntax.add(jScrollPaneModelSyntax, JSplitPane.RIGHT);
        jPanelExtensionSyntax.add(jPanelModelSyntaxButtons,
                                  new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneModelSyntax.getViewport().add(jTextAreaModelSyntax);
        jScrollPaneModelSyntaxFiles.getViewport().add(jListModelSyntaxFiles);

        jPanelExtensionSyntax.add(jSplitPaneModelSyntax,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jSplitPaneSemantics.add(jScrollPaneVerbalSemantics, JSplitPane.TOP);
        jSplitPaneSemantics.add(jScrollPaneVerbalAmnSemantics,
                                JSplitPane.BOTTOM);
        jPanelSemantics.add(jLabelTranslator2AmnVersion,
                            new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jTextFieldTranslator2AmnVersion,
                            new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneVerbalAmnSemantics.getViewport().add(
                jTextAreaVerbalAmnSemantics);
        jScrollPaneVerbalSemantics.getViewport().add(jTextAreaVerbalSemantics);

        jPanelSemantics.add(jPanelSemanticsButtons,
                            new GridBagConstraints(0, 3, 2, 1, 1.0, 0.0
                , GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemanticsButtons.add(jButtonUpdateAmnSemantics);
        jPanelSemantics.add(jSplitPaneSemantics,
                            new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 0, 5), 0, 0));
        jPanelReferenceSchema.add(jPanelVODButtons,
                                        new
                                        GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(jTabbedPaneActivities,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.SOUTHWEST, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0)); // disable all tabs except registration card
        jPanelRegCard.add(jLabelExtendedModel,
                          new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.EAST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jScrollPaneExtensionDescription,
                          new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.BOTH,
                                                 new Insets(0, 5, 0, 5), 0, 0));
        jPanelRegCard.add(jLabelTitle,
                          new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.EAST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 0, 5), 0, 0));
        jPanelRegCard.add(jPanelRCButtons,
                          new GridBagConstraints(0, 4, 3, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(0, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jTextFieldShortTitle,
                          new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jTextFieldExtendedModel,
                          new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jButtonExploreExtension,
                          new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jTabbedPaneActivities.add(jPanelReferenceSchema,
                                  "Reference Schema");
        for (int i = 1; i <= 3; i++){
            jTabbedPaneActivities.setEnabledAt(i, false);
        }
        // set dividers locations
        setDividersLocations();
    }

    GridBagLayout gridBagLayoutMR = new GridBagLayout();
    JTabbedPane jTabbedPaneActivities = new JTabbedPane();
    JPanel jPanelRegCard = new JPanel();
    GridBagLayout gridBagLayoutRC = new GridBagLayout();
    JPanel jPanelExtensionSyntax = new JPanel();
    GridBagLayout gridBagLayoutMS = new GridBagLayout();
    JLabel jLabelTitle = new JLabel();
    JTextField jTextFieldShortTitle = new JTextField();
    JPanel jPanelRCButtons = new JPanel();
    JButton jButtonUpdateRegCard = new JButton();
    FlowLayout flowLayoutRCB = new FlowLayout();
    JButton jButtonDeleteCard = new JButton();
    TitledBorder titledBorder3 = new TitledBorder("");
    JPanel jPanelModelSyntaxButtons = new JPanel();
    JButton jButtonBrowseModelSyntaxFile = new JButton();
    JButton jButtonUpdateModelSyntax = new JButton();
    JPanel jPanelSemantics = new JPanel();
    GridBagLayout gridBagLayoutAMNS = new GridBagLayout();
    JPanel jPanelSemanticsButtons = new JPanel();
    JButton jButtonUpdateAmnSemantics = new JButton();
    JSplitPane jSplitPaneSemantics = new JSplitPane();
    JScrollPane jScrollPaneVerbalSemantics = new JScrollPane();
    JTextArea jTextAreaVerbalSemantics = new JTextArea();
    JPanel jPanelReferenceSchema = new JPanel();
    GridBagLayout gridBagLayoutVOD = new GridBagLayout();
    JScrollPane jScrollPaneReferenceSchemaTemplate = new JScrollPane();
    JPanel jPanelVODButtons = new JPanel();
    JButton jButtonCreateReferenceSchemaTemplate = new JButton();
    JTextArea jTextAreaReferenceSchemaTemplate = new JTextArea();
    JButton jButtonVODUpdate = new JButton();
    JButton jButtonVODEdit = new JButton();
    JSplitPane jSplitPaneReferenceSchema = new JSplitPane();
    JPanel jPanelReferenceSchemaEditor = new JPanel();
    JSplitPane jSplitPaneModelSyntax = new JSplitPane();
    JScrollPane jScrollPaneModelSyntaxFiles = new JScrollPane();
    JScrollPane jScrollPaneModelSyntax = new JScrollPane();
    JTextArea jTextAreaModelSyntax = new JTextArea();
    TextFileJList jListModelSyntaxFiles = new TextFileJList(jTextAreaModelSyntax);
    JScrollPane jScrollPaneVerbalAmnSemantics = new JScrollPane();
    JTextArea jTextAreaVerbalAmnSemantics = new JTextArea();
    JLabel jLabelTranslator2AmnVersion = new JLabel();
    JTextField jTextFieldTranslator2AmnVersion = new JTextField();
    JScrollPane jScrollPaneExtensionDescription = new JScrollPane();
    JTextArea jTextAreaExtensionDescription = new JTextArea();
    JButton jButtonDeleteModelSyntaxFile = new JButton();
    JLabel jLabelExtendedModel = new JLabel();
    JTextField jTextFieldExtendedModel = new JTextField();
    JButton jButtonExploreExtension = new JButton();
    JButton jButtonExportSyntaxFile = new JButton();
    JButton jButtonBrowseReferenceSchema = new JButton();
    BorderLayout borderLayoutSchemaEditor = new BorderLayout();


    public void this_windowClosing(WindowEvent e) {

    }


    public void jButtonExploreExtension_actionPerformed(ActionEvent e) {
        exploreExtension();
    }

    public void jButtonUpdateRegCard_actionPerformed(ActionEvent e) {
        try {
            this.updateExtensionRegCard();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteCard_actionPerformed(ActionEvent e) {
        try {
            this.removeExtension();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonBrowseModelSyntaxFile_actionPerformed(ActionEvent e) {
        try {
            jListModelSyntaxFiles.addFile("%sdf%", "SDF syntax files (*.sdf)");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteModelSyntaxFile_actionPerformed(ActionEvent e) {
        jListModelSyntaxFiles.removeSelectedFile();
    }

    public void jButtonUpdateModelSyntax_actionPerformed(ActionEvent e) {
        try {
            updateAbstractSyntax();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonUpdateAmnSemantics_actionPerformed(ActionEvent e) {
        try {
            updateSemantics();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonCreateReferenceSchemaTemplate_actionPerformed(ActionEvent e) {
        try {
            createReferenceSchemaTemplate();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        } catch (SdfDefinitionException ex) {
            logger.info(ex.toString());
        } catch (SDF2RSException ex) {
            logger.info(ex.toString());
        } catch (ReferenceSchemaModelException ex) {
            logger.info(ex.toString());
        } catch (RecognitionException ex) {
            logger.info(ex.toString());
        } catch (java.io.IOException ex) {
            logger.info(ex.toString());
        }

    }

    public void jButtonVODEdit_actionPerformed(ActionEvent e) {
        try {
            editReferenceSchema();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonBrowseReferenceSchema_actionPerformed(ActionEvent e) {
        try {
            browseReferenceSchema();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }


    public void jButtonVODUpdate_actionPerformed(ActionEvent e) {
        try {
            updateReferenceSchema();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonExportSyntaxFile_actionPerformed(ActionEvent e) {
        try {
            jListModelSyntaxFiles.exportSelectedFile("%sdf%", "SDF syntax files (*.sdf)");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jListModelSyntaxFiles_mouseClicked(MouseEvent e) {
        jListModelSyntaxFiles.updateLastSelectedContent();
    }


    // END GUI part

    // START GUI support part

    // set dividers locations
    private void setDividersLocations(){
        jSplitPaneModelSyntax.setDividerLocation(150);
        jSplitPaneSemantics.setDividerLocation(200);
        jSplitPaneReferenceSchema.setDividerLocation(300);
    }


    // START Implementation part

    private Logger logger;

    private DB db;
    private ExtensionRegDef extension;


    private SchemaEditorGui schemaEditor = null;

    private void createNewExtension() throws SQLException {
        extension = db.new_extensionReg();
    }

    private void openExtension() throws SQLException {
        // show registartion card data
        jTextFieldShortTitle.setText(extension.get_name());
        jTextAreaExtensionDescription.setText(extension.get_description());
        jListModelSyntaxFiles.fillFromFileContainerSet(extension.get_abstrSyntax());
        jTextAreaVerbalSemantics.setText(extension.get_verbSem());
        jTextAreaVerbalAmnSemantics.setText(extension.get_verbAmnSem());
        jTextFieldTranslator2AmnVersion.setText(extension.get_transToAmn());
        jTextAreaReferenceSchemaTemplate.setText(extension.get_refSchTmpl());

        if(extension.get_extending() != null){
            jTextFieldExtendedModel.setText(extension.get_extending().get_name());
        }

        // enable Model Syntax tab
        jTabbedPaneActivities.setEnabledAt(1, true);

        // If abstract syntax is presented
        // enable Semantics, Reference Schema tabs
        if(extension.get_abstrSyntax().size() != 0){
            jTabbedPaneActivities.setEnabledAt(2, true);
            jTabbedPaneActivities.setEnabledAt(3, true);
        }

        if(extension.get_refSchema() != null){
            showReferenceSchemaEditor();
        }

    }

    private void updateExtensionRegCard() throws SQLException {
        // if an extension has not been created yet, create it;
        // enable Syntax tab
        if (jTextFieldShortTitle.getText().trim().compareTo("") != 0) {
            if (extension == null) createNewExtension();
            jTabbedPaneActivities.setEnabledAt(1, true);
        } else {
            JOptionPane.showMessageDialog(null, "Title have to be present.");
            return;
        }

        extension.set_name(jTextFieldShortTitle.getText());
        extension.set_description(jTextAreaExtensionDescription.getText());

        // commit changes in repository
        // db.commit();
    }

    private void removeExtension() throws SQLException {
        int answer = JOptionPane.showConfirmDialog(
                null,
                "Remove extension " + jTextFieldShortTitle.getText() + " from the Model Registry?",
                "Remove extension",
                JOptionPane.YES_NO_OPTION);

        if (answer == 0) {
            RepositoryCleaner.delete(extension);
            // db.commit();

            this.dispose();
        }
    }

    private void updateAbstractSyntax() throws SQLException {
        jListModelSyntaxFiles.loadFilesToRepository(db, extension.get_abstrSyntax());

        // commit changes in repository
        // db.commit();

        // if syntax is presented
        // enable Semantics, Reference Schema tabs
        if(extension.get_abstrSyntax().size() != 0){
            jTabbedPaneActivities.setEnabledAt(2, true);
            jTabbedPaneActivities.setEnabledAt(3, true);
        }
    }

    private void updateSemantics() throws SQLException {
        extension.set_verbSem(jTextAreaVerbalSemantics.getText());
        extension.set_verbAmnSem(jTextAreaVerbalAmnSemantics.getText());
        extension.set_transToAmn(jTextFieldTranslator2AmnVersion.getText());

        // commit changes in repository
        // db.commit();
    }

    private void createReferenceSchemaTemplate() throws SQLException, SdfDefinitionException,
            SDF2RSException, ReferenceSchemaModelException, java.io.IOException,
            RecognitionException {
        SdfCreator sdfCreator = new SdfCreator();
        Module module = null;
        Set<String> terminals = null;

        for(int i = 0; i < jListModelSyntaxFiles.getModel().getSize(); i++){
            TextFile file = (TextFile)jListModelSyntaxFiles.getModel().getElementAt(i);
            Reader reader;

            reader = new StringReader(file.getContent());
            terminals = StringChooserDialog.showStringChooserDialog("Select terminal sorts", (new SdfCreator()).getSortNames(reader));
            reader.close();

            reader = new StringReader(file.getContent());
            module = sdfCreator.processSdf(reader, terminals);
            reader.close();
        }


        FormattingWriter writer = new DefaultFormattingWriter(new StringWriter(), 1, "  ", "\n");
        SDF2RS sdf2so = new SDF2RS(module, writer);

        sdf2so.generateSchema();
        jTextAreaReferenceSchemaTemplate.setText(writer.toString());

    }

    private void editReferenceSchema() throws SQLException {
        StringReader reader = new StringReader(jTextAreaReferenceSchemaTemplate.getText());
        ModuleDef module;

        closeReferenceSchemaEditor();

        if(extension.get_refSchema() != null){
            RepositoryCleaner.delete(extension.get_refSchema());
        }
        module = (new SynthesisLoader()).loadFile(db, reader, false);
        extension.set_refSchema(module);

        updateReferenceSchemaImport();
        showReferenceSchemaEditor();
    }

    private void browseReferenceSchema() throws SQLException {
        SchemaDef unifierSchemaDef = null;
        ModuleDef module = null;
        Set schemas;

        // Find 'Unifier' schema in repository.
        schemas = db.get_schema(new Query(new Like(SchemaDef._name, "Unifier")));
        for(Object obj: schemas){
            unifierSchemaDef = (SchemaDef)obj;
        }
        if(unifierSchemaDef == null)
            logger.info("Unifier schema no found.");
        else{
            module = (ModuleDef)ElementChooserDialog.showElementChooserDialog("Select a module to be reference schema", unifierSchemaDef.get_modules());
            if(module != null)
                extension.set_refSchema(module);
        }

        updateReferenceSchemaImport();
        showReferenceSchemaEditor();
    }

    private void closeReferenceSchemaEditor(){
        if(schemaEditor != null){
            schemaEditor.end();
            jPanelReferenceSchemaEditor.remove(schemaEditor);
        }
        logger.info("Schema editor closed.");
    }

    private void showReferenceSchemaEditor() throws SQLException {
        SchemaEditorGui gui = null;
        ModuleDef refSchema = null;

        refSchema = extension.get_refSchema();

        if(refSchema != null){
            closeReferenceSchemaEditor();
            gui = new SchemaEditorGui();
            schemaEditor = gui;
            jPanelReferenceSchemaEditor.add("Center", gui);
            gui.start(refSchema);
            validate();
            repaint();
        }
    }

    private void updateReferenceSchema() throws SQLException {
        extension.set_refSchTmpl(jTextAreaReferenceSchemaTemplate.getText());
        // db.commit();
    }

    public void showExtendedModel() throws SQLException {
        if(extension != null && extension.get_extending() != null){
            jTextFieldExtendedModel.setText(extension.get_extending().get_name());
            updateReferenceSchemaImport();
        }
    }

    private void updateReferenceSchemaImport() throws SQLException {
        ModuleDef extendingRefSchema = null, extendedRefSchema = null;

        if(extension != null && extension.get_extending() != null){
            extendingRefSchema = extension.get_refSchema();
            extendedRefSchema = extension.get_extending().get_refSchema();

            if(extendingRefSchema != null && extendedRefSchema != null){
                extendingRefSchema.get_imports().add(extendedRefSchema);
            }
        }
    }

    private void exploreExtension(){
        ExtensionExplorer explorer;


        if(extension == null){
            JOptionPane.showMessageDialog(null, "Update extension.");
            return;
        }

        logger.info("Initiating search for an extended model ...");
        explorer = new ExtensionExplorer(this, extension);
    }

}

// Action Adapters

class ExtensionRegistrar_jButtonBrowseReferenceSchema_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonBrowseReferenceSchema_actionAdapter(
            ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseReferenceSchema_actionPerformed(e);
    }
}


class ExtensionRegistrar_this_windowAdapter extends WindowAdapter {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_this_windowAdapter(ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}


class ExtensionRegistrar_jListModelSyntaxFiles_mouseAdapter extends
        MouseAdapter {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jListModelSyntaxFiles_mouseAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListModelSyntaxFiles_mouseClicked(e);
    }
}


class ExtensionRegistrar_jButtonExportSyntaxFile_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonExportSyntaxFile_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExportSyntaxFile_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonVODUpdate_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonVODUpdate_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonVODUpdate_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonVODEdit_actionAdapter implements ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonVODEdit_actionAdapter(ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonVODEdit_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCreateReferenceSchemaTemplate_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonUpdateAmnSemantics_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonUpdateAmnSemantics_actionAdapter(
            ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateAmnSemantics_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonUpdateModelSyntax_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonUpdateModelSyntax_actionAdapter(
            ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateModelSyntax_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter(
            ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteModelSyntaxFile_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter(
            ExtensionRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseModelSyntaxFile_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonDeleteCard_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonDeleteCard_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteCard_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonUpdateRegCard_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonUpdateRegCard_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateRegCard_actionPerformed(e);
    }
}


class ExtensionRegistrar_jButtonExploreExtension_actionAdapter implements
        ActionListener {
    private ExtensionRegistrar adaptee;
    ExtensionRegistrar_jButtonExploreExtension_actionAdapter(ExtensionRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExploreExtension_actionPerformed(e);
    }
}
