package unifier;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import meta.DB;
import java.util.logging.Logger;
import meta.*;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.text.JTextComponent;
import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.CharArrayReader;
import java.io.StringReader;
import SynthesisLoaderClasses.SynthesisLoader;
import unifier.util.TextFileJList;
import unifier.util.FileJTextArea;
import unifier.util.DateHelper;
import unifier.util.SampleRegDefCellRenderer;
import unifier.util.BrowserControl;
import unifier.util.TextFile;
import unifier.util.RepositoryCleaner;
import unifier.util.FormattingWriter;
import unifier.util.DefaultFormattingWriter;
import java.util.Set;
import unifier.util.ElementDefCellRenderer;
import synthesis2b.Module2BTranslator;
import unifier.util.*;
import unifier.asfsdf.*;
import unifier.rsm.ReferenceSchemaModelException;
import java.io.Reader;
import org.antlr.runtime.RecognitionException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import db.Query;
import db.Like;
import unifier.similarity.TestSimilarityCreator;
import unifier.similarity.Similarity;
import org.ipi.schemaeditor.SchemaEditorGui;
import java.awt.event.KeyEvent;
import org.ipi.schemamapping.SchemaMappingGui;
import unifier.similarity.SimilarityCreator;

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
public class ModelRegistrar extends CenteredFrame {

    public ModelRegistrar(DB db) throws SQLException {
        logger = Logger.getLogger("unifier.ModelRegistrar");
        this.db = db;

        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        createNewModel();
    }


    public ModelRegistrar(ModelRegDef amodel) throws SQLException {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        center();

        logger = Logger.getLogger("unifier.ModelRegistrar");

        db = (DB)amodel._db();
        model = amodel;
        openModel();
    }


    // START GUI part

    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Model Registrar");
        this.addWindowListener(new ModelRegistrar_this_windowAdapter(this));
        this.getContentPane().setLayout(borderLayoutMR);
        jPanelRegCard.setLayout(gridBagLayoutRC);
        jPanelModelSyntax.setLayout(gridBagLayoutMS);
        jLabelShortTitle.setText("Short Title:");
        jTextFieldShortTitle.setText("");
        jLabelFullTitle.setText("Full Title:");
        jTextFieldFullTitle.setText("");
        jPanelRegCard.setBorder(null);
        jPanelRegCard.setToolTipText("");
        jPanelSyntax.setBorder(new TitledBorder(BorderFactory.
                                                createEtchedBorder(Color.white,
                new Color(148, 145, 140)), "Concrete Syntax"));
        jPanelSyntax.setToolTipText("");
        jPanelSyntax.setLayout(gridBagLayoutRCS);
        jLabelSyntaxDocTitle.setText("Document title:");
        jLabelSyntaxDate.setText("Date:");
        jLabelSyntaxLink.setText("Link:");
        jTextFieldSyntaxDocTitle.setText("");
        jTextFieldSyntaxLink.setText("http://");
        jButtonBrowseSyntaxLink.setText("Browse");
        jButtonBrowseSyntaxLink.addActionListener(new
                ModelRegistrar_jButtonBrowseSyntaxLink_actionAdapter(this));
        jPanelSemantics.setLayout(gridBagLayoutRCSem);
        jPanelSemantics.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Semantics"));
        jLabelSemanticsDocTitle.setText("Document title:");
        jTextFieldSemanticsDocTitle.setText("");
        jLabelSemanticsDate.setText("Date:");
        jLabelSemanticsLink.setText("Link:");
        jTextFieldSemanticsLink.setText("http://");
        jButtonBrowseSemantics.setText("Browse");
        jButtonBrowseSemantics.addActionListener(new
                ModelRegistrar_jButtonBrowseSemantics_actionAdapter(this));
        jButtonUpdateRegCard.setToolTipText(
                "Update registartion card data in the model registry");
        jButtonUpdateRegCard.setText("Update");
        jButtonUpdateRegCard.setMnemonic(KeyEvent.VK_U);
        jButtonUpdateRegCard.addActionListener(new
                ModelRegistrar_jButtonUpdateRegCard_actionAdapter(this));
        jPanelRCButtons.setLayout(flowLayoutRCB);
        jButtonDeleteCard.setToolTipText(
                "Remove current registration card from registry");
        jButtonDeleteCard.setText("Delete");
        jButtonDeleteCard.addActionListener(new
                ModelRegistrar_jButtonDeleteCard_actionAdapter(this));
        jPanelRCButtons.setBorder(BorderFactory.createEtchedBorder(Color.white,
                new Color(148, 145, 140)));
        jButtonBrowseModelSyntaxFile.setToolTipText(
                "Browse a file with model abstract syntax (SDF)");
        jButtonBrowseModelSyntaxFile.setText("Browse");
        jButtonBrowseModelSyntaxFile.addActionListener(new
                ModelRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter(this));
        jButtonUpdateModelSyntax.setToolTipText(
                "Update syntax in the model registry");
        jButtonUpdateModelSyntax.setText("Update");
        jButtonUpdateModelSyntax.addActionListener(new
                ModelRegistrar_jButtonUpdateModelSyntax_actionAdapter(this));
        jTabbedPaneActivities.setMinimumSize(new Dimension(855, 520));
        jTabbedPaneActivities.setPreferredSize(new Dimension(960, 750));
        jPanelAmnSemantics.setLayout(gridBagLayoutAMNS);
        jButtonUpdateAmnSemantics.setToolTipText(
                "Update semantics in the model registry");
        jButtonUpdateAmnSemantics.setText("Update");
        jButtonUpdateAmnSemantics.addActionListener(new
                ModelRegistrar_jButtonUpdateAmnSemantics_actionAdapter(this));
        jButtonBrowseAmnTranslatorFile.setToolTipText(
                "Browse file with translator specification (ASF)");
        jButtonBrowseAmnTranslatorFile.setText("Browse");
        jButtonBrowseAmnTranslatorFile.addActionListener(new
                ModelRegistrar_jButtonBrowseAmnTranslatorFile_actionAdapter(this));
        jTextAreaVerbalAmnSemantics.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaVerbalAmnSemantics.setText("");
        jScrollPaneVerbalAmnSemantics.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Verbal AMN Semantics"));
        jPanelReferenceSchema.setLayout(gridBagLayoutVOD);
        jButtonCreateReferenceSchemaTemplate.setToolTipText(
                "Create reference schema template on the base of model abstract syntax");
        jButtonCreateReferenceSchemaTemplate.setText("Create");
        jButtonCreateReferenceSchemaTemplate.addActionListener(new
                ModelRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter(this));
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
                ModelRegistrar_jButtonVODUpdate_actionAdapter(this));
        jButtonVODEdit.setToolTipText("Open model reference schema template in editor");
        jButtonVODEdit.setText("Edit");
        jButtonVODEdit.addActionListener(new
                ModelRegistrar_jButtonVODEdit_actionAdapter(this));
        jSplitPaneReferenceSchema.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPaneReferenceSchema.setOneTouchExpandable(true);
        jPanelReferenceSchemaIntegration.setLayout(gridBagLayoutOI);
        jPanelTranslator.setLayout(gridBagLayoutTr);
        jButtonBrowseTranslator.setToolTipText(
                "Browse a file with translator specification (ASF)");
        jButtonBrowseTranslator.setText("Browse");
        jButtonBrowseTranslator.addActionListener(new
                ModelRegistrar_jButtonBrowseTranslator_actionAdapter(this));
        jButtonGenerateTemplate.setToolTipText("Generate translator template");
        jButtonGenerateTemplate.setText("Generate");
        jButtonGenerateTemplate.addActionListener(new
                ModelRegistrar_jButtonGenerateTemplate_actionAdapter(this));
        jButtonExportTranslator.setToolTipText(
                "Export file with translator specification (ASF)");
        jButtonExportTranslator.setText("Export");
        jButtonExportTranslator.addActionListener(new
                ModelRegistrar_jButtonExportTranslator_actionAdapter(this));
        jButtonUpdateTranslator.setToolTipText(
                "Update translator specifications in model registry");
        jButtonUpdateTranslator.setText("Update");
        jButtonUpdateTranslator.addActionListener(new
                ModelRegistrar_jButtonUpdateTranslator_actionAdapter(this));
        jTextAreaVerbalRules.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaVerbalRules.setText("");
        jScrollPaneVerbalRules.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Verbal Rules of Translation"));
        jPanelSamples.setLayout(gridBagLayoutSam);
        jTabbedPaneSample.setTabPlacement(JTabbedPane.BOTTOM);
        jPanelSampleRegCard.setLayout(gridBagLayoutSRC);
        jButtonNewSample.setToolTipText("Create new sample");
        jButtonNewSample.setText("New");
        jButtonNewSample.addActionListener(new
                ModelRegistrar_jButtonNewSample_actionAdapter(this));
        jButtonDeleteSample.setToolTipText("Delete sample");
        jButtonDeleteSample.setText("Delete");
        jButtonDeleteSample.addActionListener(new
                ModelRegistrar_jButtonDeleteSample_actionAdapter(this));
        jLabelSampleTitle.setText("Title:");
        jTextFieldSampleTitle.setText("");
        jPanelSourceSampleSpec.setLayout(gridBagLayoutSS);
        jPanelSourceSampleSpec.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Sample Source Specification"));
        jButtonExportSourceSampleSpec.setToolTipText("Export source sample specification");
        jButtonExportSourceSampleSpec.setText("Export");
        jButtonExportSourceSampleSpec.addActionListener(new
                ModelRegistrar_jButtonExportSampleSpec_actionAdapter(this));
        jButtonBrowseSourceSampleSpec.setToolTipText("Browse source sample specification");
        jButtonBrowseSourceSampleSpec.setText("Browse");
        jButtonBrowseSourceSampleSpec.addActionListener(new
                ModelRegistrar_jButtonBrowseSampleSpec_actionAdapter(this));
        jPanelSampleTargetSpec.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Sample Target Specification"));
        jPanelSampleTargetSpec.setLayout(gridBagLayoutSCS);
        jButtonBrowseSampleTargetSpec.setToolTipText(
                "Browse sample target specification");
        jButtonBrowseSampleTargetSpec.setText("Browse");
        jButtonBrowseSampleTargetSpec.addActionListener(new
                ModelRegistrar_jButtonBrowseSampleTargetSpec_actionAdapter(this));
        jCheckBoxSampleVerification.setBorder(null);
        jCheckBoxSampleVerification.setText("Sample is verified (refinement is proved)");
        jCheckBoxSampleVerification.addMouseListener(new
                ModelRegistrar_jCheckBoxSampleVerification_mouseAdapter(this));
        jButtonTranslateSampleTargetSpec2Amn.setToolTipText(
                "Translate sample target specification into AMN");
        jButtonTranslateSampleTargetSpec2Amn.setText("Translate into AMN");
        jButtonTranslateSampleTargetSpec2Amn.addActionListener(new
                ModelRegistrar_jButtonTranslateSampleTargetSpec2Amn_actionAdapter(this));
        jPanelSampleVerification.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Sample Verification"));
        jTextAreaModelSyntax.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaModelSyntax.setText("");
        jScrollPaneModelSyntaxFiles.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Files"));
        jTextAreaTranslator2Amn.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaTranslator2Amn.setText("");
        jSplitPaneTranslator2Amn.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "SDF Translator into AMN"));
        jSplitPaneTranslator2Amn.setOneTouchExpandable(true);
        jScrollPaneTranslator2AmnFiles.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(EtchedBorder.RAISED, Color.white,
                                   new Color(148, 145, 140)), "Files"));
        jScrollPaneModelSyntax.setBorder(BorderFactory.createEmptyBorder());
        jScrollPaneTranslator2Amn.setBorder(BorderFactory.createEmptyBorder());
        jSplitPaneAmnSemantics.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPaneAmnSemantics.setOneTouchExpandable(true);
        jSplitPaneModelSyntax.setOneTouchExpandable(true);
        jTextAreaTranslator2TM.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaTranslator2TM.setText("");
        jSplitPaneTranslator2TM.setBorder(border2);
        jSplitPaneTranslator2TM.setOneTouchExpandable(true);
        jSplitPaneTranslator.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPaneTranslator.setOneTouchExpandable(true);
        jScrollPaneTranslator2TMFiles.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(EtchedBorder.RAISED, Color.white,
                                   new Color(148, 145, 140)), "Files"));
        jTextAreaSampleSpec.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaSampleSpec.setText("");
        jListExistingSamples.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListExistingSamples.addMouseListener(new
                ModelRegistrar_jListExistingSamples_mouseAdapter(this));
        jScrollPaneExistingSamples.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Existing Samples"));
        jTextAreaSampleTargetSpec.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaSampleTargetSpec.setText("");
        jSplitPaneSamples.setOneTouchExpandable(true);
        jPanelSourceSampleAmnSemantics.setLayout(gridBagLayoutSAMNS);
        jSplitPaneSourceSampleAmnSemantics.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPaneSourceSampleAmnSemantics.setOneTouchExpandable(true);
        jPanelSourceSampleAmnSemanticsNonRec.setLayout(gridBagLayoutSAMNSNR);
        jButtonSourceSampleAmnSemanticsNonRecBrowse.setToolTipText(
                "Browse initial AMN semantics file");
        jButtonSourceSampleAmnSemanticsNonRecBrowse.setText("Browse");
        jButtonSourceSampleAmnSemanticsNonRecBrowse.addActionListener(new
                ModelRegistrar_jButtonSampleAmnSemanticsNonRecBrowse_actionAdapter(this));
        jTextAreaSourceSampleAmnSemanticsNonRec.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jTextAreaSourceSampleAmnSemanticsNonRec.setEditable(false);
        jTextAreaSourceSampleAmnSemanticsNonRec.setText("");
        jPanelSourceSampleAmnSemanticsNonRec.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Initial AMN Semantics"));
        jScrollPaneSourceSampleAmnSemanticsNonRecFiles.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                                                 Color.white,
                                                 new Color(148, 145, 140)),
                "Files"));
        jPanelSourceSampleAmnSemanticsRec.setLayout(gridBagLayoutSAMNSR);
        jTextAreaSourceSampleAmnSemanticsRec.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jTextAreaSourceSampleAmnSemanticsRec.setText("");
        jButtonSourceSampleAmnSemanticsRecDelete.setToolTipText(
                "Delete reconciled source sample AMN semantics file");
        jButtonSourceSampleAmnSemanticsRecDelete.setText("Delete");
        jButtonSourceSampleAmnSemanticsRecDelete.addActionListener(new
                ModelRegistrar_jButtonSampleAmnSemanticsRecDelete_actionAdapter(this));
        jButtonSourceSampleAmnSemanticsRecExport.setToolTipText(
                "Export reconciled source sample AMN semantics file");
        jButtonSourceSampleAmnSemanticsRecExport.setText("Export");
        jButtonSourceSampleAmnSemanticsRecExport.addActionListener(new
                ModelRegistrar_jButtonSampleAmnSemanticsRecExport_actionAdapter(this));
        jButtonSourceSampleAmnSemanticsRecBrowse.setToolTipText(
                "Browse reconciled source sample AMN semantics file");
        jButtonSourceSampleAmnSemanticsRecBrowse.setText("Browse");
        jButtonSourceSampleAmnSemanticsRecBrowse.addActionListener(new
                ModelRegistrar_jButtonSampleAmnSemanticsRecBrowse_actionAdapter(this));
        jButtonSourceSampleAmnSemanticsNonRecDelete.setToolTipText(
                "Delete initial AMN semantics file");
        jButtonSourceSampleAmnSemanticsNonRecDelete.setText("Delete");
        jButtonSourceSampleAmnSemanticsNonRecDelete.addActionListener(new
                ModelRegistrar_jButtonSampleAmnSemanticsNonRecDelete_actionAdapter(this));
        jSplitPaneSourceSampleAmnSemanticsRec.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Reconciled AMN Semantics"));
        jSplitPaneSourceSampleAmnSemanticsRec.setOneTouchExpandable(true);
        jScrollPaneSourceSampleAmnSemanticsRecFiles.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                                                 Color.white,
                                                 new Color(148, 145, 140)),
                "Files"));
        jSplitPaneTargetSampleAmnSemantics.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPaneTargetSampleAmnSemantics.setOneTouchExpandable(true);
        jPanelTargetSampleAmnSemantics.setLayout(gridBagLayoutCSAMNS);
        jPanelTargetSampleAmnSemanticsNonRec.setLayout(gridBagLayoutCSAMNSNR);
        jTextAreaTargetSampleAmnSemanticsNonRec.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jTextAreaTargetSampleAmnSemanticsNonRec.setEditable(false);
        jTextAreaTargetSampleAmnSemanticsNonRec.setText("");
        jPanelTargetSampleAmnSemanticsRec.setLayout(gridBagLayoutCSAMNSR);
        jButtonTargetSampleAmnSemanticsRecDelete.setToolTipText(
                "Delete target sample AMN semantics file");
        jButtonTargetSampleAmnSemanticsRecDelete.setText("Delete");
        jButtonTargetSampleAmnSemanticsRecDelete.addActionListener(new
                ModelRegistrar_jButtonTargetSampleAmnSemanticsRecDelete_actionAdapter(this));
        jButtonTargetSampleAmnSemanticsRecExport.setToolTipText(
                "Export target sample AMN semantics file");
        jButtonTargetSampleAmnSemanticsRecExport.setText("Export");
        jButtonTargetSampleAmnSemanticsRecExport.addActionListener(new
                ModelRegistrar_jButtonTargetSampleAmnSemanticsRecExport_actionAdapter(this));
        jButtonTargetSampleAmnSemanticsRecBrowse.setToolTipText(
                "Browse target sample AMN semantics file");
        jButtonTargetSampleAmnSemanticsRecBrowse.setText("Browse");
        jButtonTargetSampleAmnSemanticsRecBrowse.addActionListener(new
                ModelRegistrar_jButtonTargetSampleAmnSemanticsRecBrowse_actionAdapter(this));
        jTextAreaTargetSampleAmnSemanticsRec.setFont(new java.awt.Font(
                "Times New Roman", Font.PLAIN, 16));
        jTextAreaTargetSampleAmnSemanticsRec.setText("");
        jPanelTargetSampleAmnSemanticsNonRec.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Initial Target AMN Semantics"));
        jSplitPaneTargetSampleAmnSemanticsNonRec.setOneTouchExpandable(true);
        jSplitPaneTargetSampleAmnSemanticsRec.setOneTouchExpandable(true);
        jPanelTargetSampleAmnSemanticsRec.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Reconciled Target AMN Semantics"));
        jScrollPaneTargetSampleAmnSemanticsNonRecFiles.setBorder(new
                TitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.
                RAISED, Color.white, new Color(148, 145, 140)), "Files"));
        jPanelRefinementProof.setLayout(gridBagLayoutSRP);
        jButtonRefinementProofBrowse.setText("Browse");
        jButtonRefinementProofBrowse.addActionListener(new
                ModelRegistrar_jButtonRefinementProofBrowse_actionAdapter(this));
        jTextAreaRefinementProof.setFont(new java.awt.Font("Times New Roman",
                Font.PLAIN, 16));
        jTextAreaRefinementProof.setText("");
        jScrollPaneTargetSampleAmnSemanticsRecFiles.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white,
                                                 new Color(148, 145, 140)),
                "Files"));
        jPanelReferenceSchemaEditor.setBorder(new TitledBorder(BorderFactory.
                createEtchedBorder(Color.white, new Color(148, 145, 140)),
                "Reference Schema Editor"));
        jFormattedTextFieldSyntaxDate.setPreferredSize(new Dimension(70, 22));
        jFormattedTextFieldSyntaxDate.setText("");
        jFormattedTextFieldSemanticsDate.setPreferredSize(new Dimension(70, 24));
        jFormattedTextFieldSemanticsDate.setText("");
        jButtonDeleteModelSyntaxFile.setToolTipText(
                "Delete a file with model abstract syntax");
        jButtonDeleteModelSyntaxFile.setText("Delete");
        jButtonDeleteModelSyntaxFile.addActionListener(new
                ModelRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter(this));
        jButtonDeleteAmnTranslatorFile.setToolTipText(
                "Delete file with translator specification");
        jButtonDeleteAmnTranslatorFile.setText("Delete");
        jButtonDeleteAmnTranslatorFile.addActionListener(new
                ModelRegistrar_jButtonDeleteAmnTranslatorFile_actionAdapter(this));
        jButtonDeleteTranslator.setToolTipText(
                "Delete a file with translator specification (ASF)");
        jButtonDeleteTranslator.setText("Delete");
        jButtonDeleteTranslator.addActionListener(new
                ModelRegistrar_jButtonDeleteTranslator_actionAdapter(this));
        jListModelSyntaxFiles.addMouseListener(new
                ModelRegistrar_jListModelSyntaxFiles_mouseAdapter(this));
        jListModelSyntaxFiles.setSelectionMode(ListSelectionModel.
                                               SINGLE_SELECTION);
        jSplitPaneSampleAmnSemanticsNonRec.setOneTouchExpandable(true);
        jListTranslator2AmnFiles.addMouseListener(new
                ModelRegistrar_jListTranslator2AmnFiles_mouseAdapter(this));
        jButtonExportModelSyntaxFile.setToolTipText("Export model syntax file");
        jButtonExportModelSyntaxFile.setText("Export");
        jButtonExportModelSyntaxFile.addActionListener(new
                ModelRegistrar_jButtonExportModelSyntaxFile_actionAdapter(this));
        jButtonExportAmnTranslatorFile.setToolTipText(
                "Export AMN translator file");
        jButtonExportAmnTranslatorFile.setText("Export");
        jButtonExportAmnTranslatorFile.addActionListener(new
                ModelRegistrar_jButtonExportAmnTranslatorFile_actionAdapter(this));
        jListTranslator2TMFiles.addMouseListener(new
                ModelRegistrar_jListTranslator2CMFiles_mouseAdapter(this));
        jListSourceSampleAmnSemanticsNonRecFiles.addMouseListener(new
                ModelRegistrar_jListSampleAmnSemanticsNonRecFiles_mouseAdapter(this));
        jListSourceSampleAmnSemanticsRecFiles.addMouseListener(new
                ModelRegistrar_jListSampleAmnSemanticsRecFiles_mouseAdapter(this));
        jListTargetSampleAmnSemanticsNonRecFiles.addMouseListener(new
                ModelRegistrar_jListTargetSampleAmnSemanticsNonRecFiles_mouseAdapter(this));
        jListTargetSampleAmnSemanticsRecFiles.addMouseListener(new
                ModelRegistrar_jListTargetSampleAmnSemanticsRecFiles_mouseAdapter(this));
        jButtonUpdateSampleRegCard.setToolTipText(
                "Update sample data in model registry");
        jButtonUpdateSampleRegCard.setText("Update");
        jButtonUpdateSampleRegCard.addActionListener(new
                ModelRegistrar_jButtonUpdateSampleRegCard_actionAdapter(this));
        jLabelRefinedModel.setText("Refined model:");
        jTextFieldRefinedModel.setEditable(false);
        jTextFieldRefinedModel.setText("");
        jButtonExploreExtension.setToolTipText(
                "Explore extensions to search for extension to refine by the model");
        jButtonExploreExtension.setText("Explore");
        jButtonExploreExtension.addActionListener(new
                ModelRegistrar_jButtonExploreExtension_actionAdapter(this));
        jButtonTranslate2AmnSampleSourceSpec.setText("Translate into AMN");
        jButtonTranslate2AmnSampleSourceSpec.addActionListener(new
                ModelRegistrar_jButtonTranslate2AmnSampleSourceSpec_actionAdapter(this));
        jButtonBrowseSchemaModule.setToolTipText(
                "Browse in repository for module to be reference schema");
        jButtonBrowseSchemaModule.setActionCommand("Browse");
        jButtonBrowseSchemaModule.setText("Browse");
        jButtonBrowseSchemaModule.addActionListener(new
                ModelRegistrar_jButtonBrowseSchemaModule_actionAdapter(this));
        jTabbedPaneActivities.add(jPanelRegCard, "Registration Card");
        jTabbedPaneActivities.add(jPanelModelSyntax, "Model Syntax");
        jPanelRCButtons.add(jButtonUpdateRegCard);
        jPanelRCButtons.add(jButtonDeleteCard);
        jPanelModelSyntaxButtons.add(jButtonBrowseModelSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonDeleteModelSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonExportModelSyntaxFile);
        jPanelModelSyntaxButtons.add(jButtonUpdateModelSyntax);
        jPanelAmnSemanticsButtons.add(jButtonBrowseAmnTranslatorFile);
        jPanelAmnSemanticsButtons.add(jButtonDeleteAmnTranslatorFile);
        jPanelAmnSemanticsButtons.add(jButtonExportAmnTranslatorFile);
        jPanelAmnSemanticsButtons.add(jButtonUpdateAmnSemantics);
        jTabbedPaneActivities.add(jPanelAmnSemantics, "AMN Semantics");
        jPanelAmnSemantics.add(jPanelAmnSemanticsButtons,
                               new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelAmnSemantics.add(jSplitPaneAmnSemantics,
                               new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneVerbalAmnSemantics.getViewport().add(
                jTextAreaVerbalAmnSemantics);
        jTabbedPaneActivities.add(jPanelReferenceSchema,
                                  "Reference Schema");
        jPanelVODButtons.add(jButtonCreateReferenceSchemaTemplate);
        jPanelVODButtons.add(jButtonVODEdit);
        jPanelVODButtons.add(jButtonBrowseSchemaModule);
        jPanelVODButtons.add(jButtonVODUpdate);
        jPanelReferenceSchema.add(jPanelVODButtons,
                                        new
                                        GridBagConstraints(0, 2, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        jSplitPaneReferenceSchema.add(jScrollPaneReferenceSchemaTemplate, JSplitPane.LEFT);
        jSplitPaneReferenceSchema.add(jPanelReferenceSchemaEditor, JSplitPane.RIGHT);
        jScrollPaneReferenceSchemaTemplate.getViewport().add(jTextAreaReferenceSchemaTemplate);
        jPanelReferenceSchema.add(jSplitPaneReferenceSchema,
                                        new GridBagConstraints(0, 1, 2, 1, 1.0,
                1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jTabbedPaneActivities.add(jPanelReferenceSchemaIntegration,
                                  "Reference Schema Integration");
        jTabbedPaneActivities.add(jPanelTranslator,
                                  "Translator of Source into Target Model");
        jPanelTTMButtons.add(jButtonGenerateTemplate);
        jPanelTTMButtons.add(jButtonBrowseTranslator);
        jPanelTTMButtons.add(jButtonDeleteTranslator);
        jPanelTTMButtons.add(jButtonExportTranslator);
        jPanelTTMButtons.add(jButtonUpdateTranslator);
        jScrollPaneVerbalRules.getViewport().add(jTextAreaVerbalRules);
        jPanelTranslator.add(jSplitPaneTranslator,
                             new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelTranslator.add(jPanelTTMButtons,
                             new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jTabbedPaneActivities.add(jPanelSamples, "Samples");
        jPanelReferenceSchemaIntegration.add(jPanelReferenceSchemaIntegrationStub,
                                      new
                                      GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSamples.add(jSplitPaneSamples,
                          new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.BOTH,
                                                 new Insets(5, 5, 0, 5), 0, 0));
        jPanelSampleButtons.add(jButtonNewSample);
        jPanelSampleButtons.add(jButtonDeleteSample);
        jPanelSampleButtons.add(jButtonUpdateSampleRegCard);
        jPanelSamples.add(jPanelSampleButtons, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleSpecButtons.add(jButtonBrowseSourceSampleSpec);
        jPanelSampleSpecButtons.add(jButtonExportSourceSampleSpec);
        jPanelSampleSpecButtons.add(jButtonTranslate2AmnSampleSourceSpec);
        jPanelSampleTargetSpecButtons.add(jButtonBrowseSampleTargetSpec);
        jPanelSampleTargetSpecButtons.add(jButtonTranslateSampleTargetSpec2Amn);
        jTabbedPaneSample.add(jPanelSampleRegCard, "Sample Registration Card");
        jPanelSampleRegCard.add(jLabelSampleTitle,
                                new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleRegCard.add(jTextFieldSampleTitle,
                                new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleVerification.add(jCheckBoxSampleVerification);
        jPanelSampleRegCard.add(jPanelSampleRegCardButtons,
                                new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleRegCard.add(jPanelSourceSampleSpec,
                                new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleRegCard.add(jPanelSampleTargetSpec,
                                new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleRegCard.add(jPanelSampleVerification,
                                new GridBagConstraints(0, 3, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelModelSyntax.add(jPanelModelSyntaxButtons,
                              new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jSplitPaneModelSyntax.add(jScrollPaneModelSyntaxFiles, JSplitPane.LEFT);
        jScrollPaneModelSyntaxFiles.getViewport().add(jListModelSyntaxFiles);
        jSplitPaneModelSyntax.add(jScrollPaneModelSyntax, JSplitPane.RIGHT);
        jScrollPaneModelSyntax.getViewport().add(jTextAreaModelSyntax);
        jPanelModelSyntax.add(jSplitPaneModelSyntax,
                              new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jSplitPaneAmnSemantics.add(jScrollPaneVerbalAmnSemantics, JSplitPane.TOP);
        jSplitPaneAmnSemantics.add(jSplitPaneTranslator2Amn, JSplitPane.BOTTOM);
        jSplitPaneTranslator2Amn.add(jScrollPaneTranslator2AmnFiles, JSplitPane.LEFT);
        jScrollPaneTranslator2AmnFiles.getViewport().add(
                jListTranslator2AmnFiles);
        jSplitPaneTranslator2Amn.add(jScrollPaneTranslator2Amn, JSplitPane.RIGHT);
        jScrollPaneTranslator2Amn.getViewport().add(jTextAreaTranslator2Amn);
        jSplitPaneTranslator.add(jScrollPaneVerbalRules, JSplitPane.TOP);
        jSplitPaneTranslator.add(jSplitPaneTranslator2TM, JSplitPane.BOTTOM);
        jSplitPaneTranslator2TM.add(jScrollPaneTranslator2TMFiles, JSplitPane.LEFT);
        jScrollPaneTranslator2TMFiles.getViewport().add(jListTranslator2TMFiles);
        jSplitPaneTranslator2TM.add(jScrollPaneTranslator2CM, JSplitPane.RIGHT);
        jScrollPaneTranslator2CM.getViewport().add(jTextAreaTranslator2TM);
        jPanelSourceSampleSpec.add(jPanelSampleSpecButtons,
                             new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        jPanelSourceSampleSpec.add(jScrollPaneSampleSpec,
                             new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        jScrollPaneSampleSpec.getViewport().add(jTextAreaSampleSpec);
        jSplitPaneSamples.add(jTabbedPaneSample, JSplitPane.RIGHT);
        jSplitPaneSamples.add(jScrollPaneExistingSamples, JSplitPane.LEFT);
        jScrollPaneExistingSamples.getViewport().add(jListExistingSamples);
        jPanelSampleTargetSpec.add(jPanelSampleTargetSpecButtons,
                                  new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleTargetSpec.add(jScrollPaneSampleTargetSpec,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneSampleTargetSpec.getViewport().add(jTextAreaSampleTargetSpec);
        jTabbedPaneSample.add(jPanelSourceSampleAmnSemantics, "Source Sample AMN Semantics");
        jSplitPaneSourceSampleAmnSemantics.add(jPanelSourceSampleAmnSemanticsNonRec, JSplitPane.TOP);
        jPanelSampleAmnSemanticsNonRecButtons.add(
                jButtonSourceSampleAmnSemanticsNonRecBrowse);
        jPanelSampleAmnSemanticsNonRecButtons.add(
                jButtonSourceSampleAmnSemanticsNonRecDelete);
        jSplitPaneSourceSampleAmnSemantics.add(jPanelSourceSampleAmnSemanticsRec, JSplitPane.BOTTOM);
        jPanelSourceSampleAmnSemantics.add(jSplitPaneSourceSampleAmnSemantics,
                                     new GridBagConstraints(0, 0, 1, 1, 1.0,
                1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jSplitPaneSampleAmnSemanticsNonRec.add(
                jScrollPaneSourceSampleAmnSemanticsNonRecFiles, JSplitPane.LEFT);
        jScrollPaneSourceSampleAmnSemanticsNonRecFiles.getViewport().add(
                jListSourceSampleAmnSemanticsNonRecFiles);
        jSplitPaneSampleAmnSemanticsNonRec.add(
                jScrollPaneSampleAmnSemanticsNonRec, JSplitPane.RIGHT);
        jScrollPaneSampleAmnSemanticsNonRec.getViewport().add(
                jTextAreaSourceSampleAmnSemanticsNonRec);
        jPanelSourceSampleAmnSemanticsNonRec.add(jSplitPaneSampleAmnSemanticsNonRec, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSourceSampleAmnSemanticsNonRec.add(
                jPanelSampleAmnSemanticsNonRecButtons, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSourceSampleAmnSemanticsRec.add(jSplitPaneSourceSampleAmnSemanticsRec, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSourceSampleAmnSemanticsRec.add(jPanelSampleAmnSemanticsRecButtons, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSampleAmnSemanticsRecButtons.add(jButtonSourceSampleAmnSemanticsRecBrowse);
        jPanelSampleAmnSemanticsRecButtons.add(
                jButtonSourceSampleAmnSemanticsRecExport);
        jPanelSampleAmnSemanticsRecButtons.add(jButtonSourceSampleAmnSemanticsRecDelete);
        jSplitPaneSourceSampleAmnSemanticsRec.add(
                jScrollPaneSourceSampleAmnSemanticsRecFiles, JSplitPane.LEFT);
        jScrollPaneSourceSampleAmnSemanticsRecFiles.getViewport().add(
                jListSourceSampleAmnSemanticsRecFiles);
        jSplitPaneSourceSampleAmnSemanticsRec.add(jScrollPaneSampleAmnSemanticsRec, JSplitPane.RIGHT);
        jScrollPaneSampleAmnSemanticsRec.getViewport().add(
                jTextAreaSourceSampleAmnSemanticsRec);
        jTabbedPaneSample.add(jPanelTargetSampleAmnSemantics,
                              "Target Sample AMN Semantics");
        jSplitPaneTargetSampleAmnSemantics.add(
                jPanelTargetSampleAmnSemanticsNonRec, JSplitPane.TOP);
        jSplitPaneTargetSampleAmnSemantics.add(jPanelTargetSampleAmnSemanticsRec, JSplitPane.BOTTOM);
        jPanelTargetSampleAmnSemanticsRecButtons.add(
                jButtonTargetSampleAmnSemanticsRecBrowse);
        jPanelTargetSampleAmnSemanticsRecButtons.add(
                jButtonTargetSampleAmnSemanticsRecExport);
        jPanelTargetSampleAmnSemanticsRecButtons.add(
                jButtonTargetSampleAmnSemanticsRecDelete);
        jPanelTargetSampleAmnSemantics.add(jSplitPaneTargetSampleAmnSemantics,
                                          new GridBagConstraints(0, 0, 1, 1,
                1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelTargetSampleAmnSemanticsNonRec.add(
                jSplitPaneTargetSampleAmnSemanticsNonRec, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jSplitPaneTargetSampleAmnSemanticsNonRec.add(
                jScrollPaneTargetSampleAmnSemanticsNonRecFiles, JSplitPane.LEFT);
        jScrollPaneTargetSampleAmnSemanticsNonRecFiles.getViewport().add(
                jListTargetSampleAmnSemanticsNonRecFiles);
        jSplitPaneTargetSampleAmnSemanticsNonRec.add(
                jScrollPaneTargetSampleAmnSemanticsNonRec, JSplitPane.RIGHT);
        jSplitPaneTargetSampleAmnSemanticsRec.add(
                jScrollPaneTargetSampleAmnSemanticsRecFiles, JSplitPane.LEFT);
        jScrollPaneTargetSampleAmnSemanticsRecFiles.getViewport().add(
                jListTargetSampleAmnSemanticsRecFiles);
        jSplitPaneTargetSampleAmnSemanticsRec.add(
                jScrollPaneTargetSampleAmnSemanticsRec, JSplitPane.RIGHT);
        jScrollPaneTargetSampleAmnSemanticsRec.getViewport().add(
                jTextAreaTargetSampleAmnSemanticsRec);
        jPanelTargetSampleAmnSemanticsRec.add(
                jSplitPaneTargetSampleAmnSemanticsRec, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelTargetSampleAmnSemanticsRec.add(
                jPanelTargetSampleAmnSemanticsRecButtons, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneTargetSampleAmnSemanticsNonRec.getViewport().add(
                jTextAreaTargetSampleAmnSemanticsNonRec);
        jTabbedPaneSample.add(jPanelRefinementProof, "Refinement Proof");
        jPanelRefinementProof.add(jPanelRefinementProofButtons,
                                  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelRefinementProofButtons.add(jButtonRefinementProofBrowse);
        jPanelRefinementProof.add(jScrollPaneRefinementProof,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jScrollPaneRefinementProof.getViewport().add(jTextAreaRefinementProof);
        jPanelSyntax.add(jLabelSyntaxDocTitle,
                         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jLabelSyntaxDate,
                         new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jLabelSyntaxLink,
                         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jTextFieldSyntaxDocTitle,
                         new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jTextFieldSyntaxLink,
                         new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 0), 0, 0));
        jPanelSyntax.add(jButtonBrowseSyntaxLink,
                         new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jLabelSemanticsDocTitle,
                            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jTextFieldSemanticsDocTitle,
                            new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jLabelSemanticsDate,
                            new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jLabelSemanticsLink,
                            new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jTextFieldSemanticsLink,
                            new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jButtonBrowseSemantics,
                            new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSyntax.add(jFormattedTextFieldSyntaxDate,
                         new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
        jPanelSemantics.add(jFormattedTextFieldSemanticsDate,
                            new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jTextFieldShortTitle,
                          new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jTextFieldFullTitle,
                          new GridBagConstraints(1, 1, 3, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jLabelShortTitle,
                          new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.EAST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jLabelFullTitle,
                          new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.EAST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jPanelSyntax,
                          new GridBagConstraints(0, 2, 4, 1, 1.0, 1.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 0, 5), 0, 0));
        jPanelRegCard.add(jPanelSemantics,
                          new GridBagConstraints(0, 3, 4, 1, 1.0, 1.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 0, 5), 0, 0));
        jPanelRegCard.add(jPanelRCButtons,
                          new GridBagConstraints(0, 6, 4, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jLabelRefinedModel,
                          new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0
                                                 , GridBagConstraints.EAST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0)); // disable all tabs except registration card
        jPanelRegCard.add(jTextFieldRefinedModel,
                          new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        jPanelRegCard.add(jButtonExploreExtension,
                          new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(5, 5, 5, 5), 0, 0));
        this.getContentPane().add(jTabbedPaneActivities,
                                  java.awt.BorderLayout.CENTER);
        jPanelReferenceSchemaEditor.setLayout(borderLayoutSchemaEditor);
        jPanelReferenceSchemaIntegrationStub.setLayout(borderLayoutSchemaIntegration);




        for (int i = 1; i <= 6; i++){
            jTabbedPaneActivities.setEnabledAt(i, false);
        }
        // disable all sample tabs except registration card
        for (int i = 1; i <= 3; i++){
            jTabbedPaneSample.setEnabledAt(i, false);
        }
        // set dividers locations
        setDividersLocations();
        // set samples cell renderer and list model
        jListExistingSamples.setCellRenderer(new SampleRegDefCellRenderer());
        jListExistingSamples.setModel(new DefaultListModel());
    }

    JTabbedPane jTabbedPaneActivities = new JTabbedPane();
    JPanel jPanelRegCard = new JPanel();
    GridBagLayout gridBagLayoutRC = new GridBagLayout();
    JPanel jPanelModelSyntax = new JPanel();
    GridBagLayout gridBagLayoutMS = new GridBagLayout();
    JLabel jLabelShortTitle = new JLabel();
    JTextField jTextFieldShortTitle = new JTextField();
    JLabel jLabelFullTitle = new JLabel();
    JTextField jTextFieldFullTitle = new JTextField();
    JPanel jPanelSyntax = new JPanel();
    GridBagLayout gridBagLayoutRCS = new GridBagLayout();
    JLabel jLabelSyntaxDocTitle = new JLabel();
    JLabel jLabelSyntaxDate = new JLabel();
    JLabel jLabelSyntaxLink = new JLabel();
    JTextField jTextFieldSyntaxDocTitle = new JTextField();
    JTextField jTextFieldSyntaxLink = new JTextField();
    JButton jButtonBrowseSyntaxLink = new JButton();
    JPanel jPanelSemantics = new JPanel();
    GridBagLayout gridBagLayoutRCSem = new GridBagLayout();
    JLabel jLabelSemanticsDocTitle = new JLabel();
    JTextField jTextFieldSemanticsDocTitle = new JTextField();
    JLabel jLabelSemanticsDate = new JLabel();
    JLabel jLabelSemanticsLink = new JLabel();
    JTextField jTextFieldSemanticsLink = new JTextField();
    JButton jButtonBrowseSemantics = new JButton();
    JPanel jPanelRCButtons = new JPanel();
    JButton jButtonUpdateRegCard = new JButton();
    FlowLayout flowLayoutRCB = new FlowLayout();
    JButton jButtonDeleteCard = new JButton();
    TitledBorder titledBorder3 = new TitledBorder("");
    JPanel jPanelModelSyntaxButtons = new JPanel();
    JButton jButtonBrowseModelSyntaxFile = new JButton();
    JButton jButtonUpdateModelSyntax = new JButton();
    JPanel jPanelAmnSemantics = new JPanel();
    GridBagLayout gridBagLayoutAMNS = new GridBagLayout();
    JPanel jPanelAmnSemanticsButtons = new JPanel();
    JButton jButtonUpdateAmnSemantics = new JButton();
    JButton jButtonBrowseAmnTranslatorFile = new JButton();
    JSplitPane jSplitPaneAmnSemantics = new JSplitPane();
    JScrollPane jScrollPaneVerbalAmnSemantics = new JScrollPane();
    JTextArea jTextAreaVerbalAmnSemantics = new JTextArea();
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
    JPanel jPanelReferenceSchemaIntegration = new JPanel();
    GridBagLayout gridBagLayoutOI = new GridBagLayout();
    JPanel jPanelReferenceSchemaIntegrationStub = new JPanel();
    JPanel jPanelTranslator = new JPanel();
    GridBagLayout gridBagLayoutTr = new GridBagLayout();
    JSplitPane jSplitPaneTranslator = new JSplitPane();
    JPanel jPanelTTMButtons = new JPanel();
    JButton jButtonBrowseTranslator = new JButton();
    JButton jButtonGenerateTemplate = new JButton();
    JButton jButtonExportTranslator = new JButton();
    JButton jButtonUpdateTranslator = new JButton();
    JScrollPane jScrollPaneVerbalRules = new JScrollPane();
    JTextArea jTextAreaVerbalRules = new JTextArea();
    JPanel jPanelSamples = new JPanel();
    GridBagLayout gridBagLayoutSam = new GridBagLayout();
    JSplitPane jSplitPaneSamples = new JSplitPane();
    JTabbedPane jTabbedPaneSample = new JTabbedPane();
    JPanel jPanelSampleRegCard = new JPanel();
    GridBagLayout gridBagLayoutSRC = new GridBagLayout();
    JPanel jPanelSampleButtons = new JPanel();
    JButton jButtonNewSample = new JButton();
    JButton jButtonDeleteSample = new JButton();
    JLabel jLabelSampleTitle = new JLabel();
    JTextField jTextFieldSampleTitle = new JTextField();
    JPanel jPanelSourceSampleSpec = new JPanel();
    GridBagLayout gridBagLayoutSS = new GridBagLayout();
    JPanel jPanelSampleSpecButtons = new JPanel();
    JButton jButtonExportSourceSampleSpec = new JButton();
    JButton jButtonBrowseSourceSampleSpec = new JButton();
    JPanel jPanelSampleTargetSpec = new JPanel();
    GridBagLayout gridBagLayoutSCS = new GridBagLayout();
    JPanel jPanelSampleTargetSpecButtons = new JPanel();
    JButton jButtonBrowseSampleTargetSpec = new JButton();
    JCheckBox jCheckBoxSampleVerification = new JCheckBox();
    JPanel jPanelSampleVerification = new JPanel();
    JPanel jPanelSampleRegCardButtons = new JPanel();
    JButton jButtonTranslateSampleTargetSpec2Amn = new JButton();
    JSplitPane jSplitPaneModelSyntax = new JSplitPane();
    JScrollPane jScrollPaneModelSyntaxFiles = new JScrollPane();
    JScrollPane jScrollPaneModelSyntax = new JScrollPane();
    JTextArea jTextAreaModelSyntax = new JTextArea();
    TextFileJList jListModelSyntaxFiles = new TextFileJList(jTextAreaModelSyntax);
    JSplitPane jSplitPaneTranslator2Amn = new JSplitPane();
    JScrollPane jScrollPaneTranslator2AmnFiles = new JScrollPane();
    JScrollPane jScrollPaneTranslator2Amn = new JScrollPane();
    JTextArea jTextAreaTranslator2Amn = new JTextArea();
    TextFileJList jListTranslator2AmnFiles = new TextFileJList(jTextAreaTranslator2Amn);
    JSplitPane jSplitPaneTranslator2TM = new JSplitPane();
    JScrollPane jScrollPaneTranslator2TMFiles = new JScrollPane();
    JScrollPane jScrollPaneTranslator2CM = new JScrollPane();
    JTextArea jTextAreaTranslator2TM = new JTextArea();
    TextFileJList jListTranslator2TMFiles = new TextFileJList(jTextAreaTranslator2TM);
    JScrollPane jScrollPaneSampleSpec = new JScrollPane();
    FileJTextArea jTextAreaSampleSpec = new FileJTextArea();
    JScrollPane jScrollPaneExistingSamples = new JScrollPane();
    JList jListExistingSamples = new JList();
    JScrollPane jScrollPaneSampleTargetSpec = new JScrollPane();
    FileJTextArea jTextAreaSampleTargetSpec = new FileJTextArea();
    JPanel jPanelSourceSampleAmnSemantics = new JPanel();
    GridBagLayout gridBagLayoutSAMNS = new GridBagLayout();
    JSplitPane jSplitPaneSourceSampleAmnSemantics = new JSplitPane();
    JPanel jPanelSourceSampleAmnSemanticsNonRec = new JPanel();
    JPanel jPanelSourceSampleAmnSemanticsRec = new JPanel();
    GridBagLayout gridBagLayoutSAMNSNR = new GridBagLayout();
    JSplitPane jSplitPaneSampleAmnSemanticsNonRec = new JSplitPane();
    JPanel jPanelSampleAmnSemanticsNonRecButtons = new JPanel();
    JButton jButtonSourceSampleAmnSemanticsNonRecBrowse = new JButton();
    JScrollPane jScrollPaneSourceSampleAmnSemanticsNonRecFiles = new JScrollPane();
    JScrollPane jScrollPaneSampleAmnSemanticsNonRec = new JScrollPane();
    JTextArea jTextAreaSourceSampleAmnSemanticsNonRec = new JTextArea();
    TextFileJList jListSourceSampleAmnSemanticsNonRecFiles = new TextFileJList(jTextAreaSourceSampleAmnSemanticsNonRec);
    GridBagLayout gridBagLayoutSAMNSR = new GridBagLayout();
    JSplitPane jSplitPaneSourceSampleAmnSemanticsRec = new JSplitPane();
    JPanel jPanelSampleAmnSemanticsRecButtons = new JPanel();
    JScrollPane jScrollPaneSourceSampleAmnSemanticsRecFiles = new JScrollPane();
    JScrollPane jScrollPaneSampleAmnSemanticsRec = new JScrollPane();
    JTextArea jTextAreaSourceSampleAmnSemanticsRec = new JTextArea();
    TextFileJList jListSourceSampleAmnSemanticsRecFiles = new TextFileJList(jTextAreaSourceSampleAmnSemanticsRec);
    JButton jButtonSourceSampleAmnSemanticsRecDelete = new JButton();
    JButton jButtonSourceSampleAmnSemanticsRecExport = new JButton();
    JButton jButtonSourceSampleAmnSemanticsRecBrowse = new JButton();
    JButton jButtonSourceSampleAmnSemanticsNonRecDelete = new JButton();
    JPanel jPanelTargetSampleAmnSemantics = new JPanel();
    JSplitPane jSplitPaneTargetSampleAmnSemantics = new JSplitPane();
    JPanel jPanelTargetSampleAmnSemanticsNonRec = new JPanel();
    JPanel jPanelTargetSampleAmnSemanticsRec = new JPanel();
    GridBagLayout gridBagLayoutCSAMNS = new GridBagLayout();
    GridBagLayout gridBagLayoutCSAMNSNR = new GridBagLayout();
    JSplitPane jSplitPaneTargetSampleAmnSemanticsNonRec = new JSplitPane();
    JScrollPane jScrollPaneTargetSampleAmnSemanticsNonRecFiles = new JScrollPane();
    JScrollPane jScrollPaneTargetSampleAmnSemanticsNonRec = new JScrollPane();
    JTextArea jTextAreaTargetSampleAmnSemanticsNonRec = new JTextArea();
    TextFileJList jListTargetSampleAmnSemanticsNonRecFiles = new TextFileJList(jTextAreaTargetSampleAmnSemanticsNonRec);
    GridBagLayout gridBagLayoutCSAMNSR = new GridBagLayout();
    JSplitPane jSplitPaneTargetSampleAmnSemanticsRec = new JSplitPane();
    JPanel jPanelTargetSampleAmnSemanticsRecButtons = new JPanel();
    JButton jButtonTargetSampleAmnSemanticsRecDelete = new JButton();
    JButton jButtonTargetSampleAmnSemanticsRecExport = new JButton();
    JButton jButtonTargetSampleAmnSemanticsRecBrowse = new JButton();
    JScrollPane jScrollPaneTargetSampleAmnSemanticsRecFiles = new JScrollPane();
    JScrollPane jScrollPaneTargetSampleAmnSemanticsRec = new JScrollPane();
    JTextArea jTextAreaTargetSampleAmnSemanticsRec = new JTextArea();
    TextFileJList jListTargetSampleAmnSemanticsRecFiles = new TextFileJList(jTextAreaTargetSampleAmnSemanticsRec);
    JPanel jPanelRefinementProof = new JPanel();
    GridBagLayout gridBagLayoutSRP = new GridBagLayout();
    JScrollPane jScrollPaneRefinementProof = new JScrollPane();
    JPanel jPanelRefinementProofButtons = new JPanel();
    JButton jButtonRefinementProofBrowse = new JButton();
    FileJTextArea jTextAreaRefinementProof = new FileJTextArea();
    JTextField jFormattedTextFieldSyntaxDate = new JFormattedTextField(DateHelper.createMaskFormatter("##/##/####"));
    JTextField jFormattedTextFieldSemanticsDate = new JFormattedTextField(DateHelper.createMaskFormatter("##/##/####"));
    JButton jButtonDeleteModelSyntaxFile = new JButton();
    JButton jButtonDeleteAmnTranslatorFile = new JButton();
    JButton jButtonDeleteTranslator = new JButton();
    JButton jButtonExportModelSyntaxFile = new JButton();
    JButton jButtonExportAmnTranslatorFile = new JButton();
    JButton jButtonUpdateSampleRegCard = new JButton();
    JLabel jLabelRefinedModel = new JLabel();
    JTextField jTextFieldRefinedModel = new JTextField();
    JButton jButtonExploreExtension = new JButton();
    JButton jButtonTranslate2AmnSampleSourceSpec = new JButton();
    Border border1 = BorderFactory.createEtchedBorder(Color.white,
            new Color(148, 145, 140));
    Border border2 = new TitledBorder(border1, "ASF Translator");
    JButton jButtonBrowseSchemaModule = new JButton();
    BorderLayout borderLayoutMR = new BorderLayout();
    BorderLayout borderLayoutSchemaEditor = new BorderLayout();
    BorderLayout borderLayoutSchemaIntegration = new BorderLayout();

    // START Registration Card tab
    public void this_windowClosing(WindowEvent e) {
        try {
            if (StringHelper.isNullEmptyOrBlank(model.get_name()))
                removeModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonBrowseSyntaxLink_actionPerformed(ActionEvent e) {
        BrowserControl.displayURL(jTextFieldSyntaxLink.getText());
    }

    public void jButtonBrowseSemantics_actionPerformed(ActionEvent e) {
        BrowserControl.displayURL(jTextFieldSemanticsLink.getText());
    }

    public void jButtonExploreExtension_actionPerformed(ActionEvent e) {
        try {
            exploreExtension();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonUpdateRegCard_actionPerformed(ActionEvent e) {
        try {
            updateModelRegCard();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteCard_actionPerformed(ActionEvent e) {
        try {
            removeModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }
    // END Registration Card tab

    // START Model Syntax tab
    public void jButtonBrowseModelSyntaxFile_actionPerformed(ActionEvent e) {
        try {
            jListModelSyntaxFiles.addFile("%sdf%", "SDF syntax files (*.sdf)");
        } catch (FileNotFoundException ex){
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteModelSyntaxFile_actionPerformed(ActionEvent e) {
        jListModelSyntaxFiles.removeSelectedFile();
    }

    public void jButtonExportModelSyntaxFile_actionPerformed(ActionEvent e) {
        try {
            jListModelSyntaxFiles.exportSelectedFile("%sdf%", "SDF syntax files (*.sdf)");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonUpdateModelSyntax_actionPerformed(ActionEvent e) {
        try {
            updateAbstractSyntax();
        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
        }
    }

    public void jListModelSyntaxFiles_mouseClicked(MouseEvent e) {
        jListModelSyntaxFiles.updateLastSelectedContent();
    }
    // END Model Syntax tab

    // START AMN Semantics tab
    public void jButtonBrowseAmnTranslatorFile_actionPerformed(ActionEvent e) {
        try {
            jListTranslator2AmnFiles.addFile("%sdf%asf%", "SDF and ASF files (*.sdf, *.asf)");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteAmnTranslatorFile_actionPerformed(ActionEvent e) {
        jListTranslator2AmnFiles.removeSelectedFile();
    }

    public void jButtonExportAmnTranslatorFile_actionPerformed(ActionEvent e) {
        try {
            jListTranslator2AmnFiles.exportSelectedFile("%sdf%asf%", "SDF and ASF files (*.sdf, *.asf)");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonUpdateAmnSemantics_actionPerformed(ActionEvent e) {
        try {
            updateAmnSemantics();
        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
        }
    }

    public void jListTranslator2AmnFiles_mousePressed(MouseEvent e) {
        jListTranslator2AmnFiles.updateLastSelectedContent();
    }
    // END AMN Semantics tab

    // START Reference Schema tab
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
        } catch (IOException ex) {
            logger.info(ex.toString());
        } catch (RecognitionException ex) {
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

    public void jButtonBrowseSchemaModule_actionPerformed(ActionEvent e) {
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
    // END ReferenceSchema tab

    // START Translator to Target Model tab
    public void jButtonGenerateTemplate_actionPerformed(ActionEvent e) {
        try {
            generateTranslatorToTargetModelTemplate();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        } catch (TranslatorTemplateConstructionException ex) {
            logger.info(ex.toString());
        } catch (SdfDefinitionException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        } catch (RecognitionException ex) {
            logger.info(ex.toString());
        }

    }

    public void jListTranslator2CMFiles_mouseClicked(MouseEvent e) {
        jListTranslator2TMFiles.updateLastSelectedContent();
    }

    public void jButtonBrowseTranslator_actionPerformed(ActionEvent e) {
        try {
            jListTranslator2TMFiles.addFile("%sdf%asf%", "SDF and ASF files (*.sdf, *.asf)");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteTranslator_actionPerformed(ActionEvent e) {
        jListTranslator2TMFiles.removeSelectedFile();
    }

    public void jButtonExportTranslator_actionPerformed(ActionEvent e) {
        try {
            jListTranslator2TMFiles.exportSelectedFile("%sdf%asf%", "SDF and ASF files (*.sdf, *.asf)");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonUpdateTranslator_actionPerformed(ActionEvent e) {
        try {
            updateTranslatorToTargetModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }
    // END Translator to Target Model tab


    // START Samples tab
    public void jButtonNewSample_actionPerformed(ActionEvent e) {
        try {
            this.registerNewSample();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonDeleteSample_actionPerformed(ActionEvent e) {
        try {
            this.removeSelectedSample();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jListExistingSamples_mouseClicked(MouseEvent e) {
        try {
            openSelectedSample();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jListSampleAmnSemanticsNonRecFiles_mouseClicked(MouseEvent e) {
        jListSourceSampleAmnSemanticsNonRecFiles.updateLastSelectedContent();
    }

    public void jListSampleAmnSemanticsRecFiles_mouseClicked(MouseEvent e) {
        jListSourceSampleAmnSemanticsRecFiles.updateLastSelectedContent();
    }

    public void jListTargetSampleAmnSemanticsNonRecFiles_mouseClicked(MouseEvent e) {
        jListTargetSampleAmnSemanticsNonRecFiles.updateLastSelectedContent();
    }

    public void jListTargetSampleAmnSemanticsRecFiles_mouseClicked(MouseEvent e) {
        jListTargetSampleAmnSemanticsRecFiles.updateLastSelectedContent();
    }

    public void jButtonUpdateSampleRegCard_actionPerformed(ActionEvent e) {
        try {
            updateSampleCard();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonBrowseSampleSpec_actionPerformed(ActionEvent e) {
        try {
            jTextAreaSampleSpec.importTextFromFile("", "");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonExportSampleSpec_actionPerformed(ActionEvent e) {
        try {
            jTextAreaSampleSpec.exportTextToFile("", "");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonTranslate2AmnSampleSourceSpec_actionPerformed(ActionEvent e)  {
        try {
            translateSampleSourceSpec2Amn();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }

    }


    public void jButtonBrowseSampleTargetSpec_actionPerformed(ActionEvent e) {
        try {
            jTextAreaSampleTargetSpec.importTextFromFile("",  "");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonTranslateSampleTargetSpec2Amn_actionPerformed(ActionEvent e) {
        try {
            translateSampleTargetSpec2Amn();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }

    }

    public void jCheckBoxSampleVerification_mouseClicked(MouseEvent e) {
        // Required information for proof are:
        // 1. Source sample AMN semantics reconciled files
        // 2. Target sample AMN semantics reconciled files
        // 3. Text of proof of the refinement
        if (jTextAreaRefinementProof.getText().trim().compareTo("") == 0 ||
            jListSourceSampleAmnSemanticsRecFiles.getModel().getSize() == 0 &&
            jListTargetSampleAmnSemanticsRecFiles.getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(null, "Required information for proof is not presented.");
            jCheckBoxSampleVerification.setSelected(false);
        }
    }

    public void jButtonSampleAmnSemanticsNonRecBrowse_actionPerformed(ActionEvent e) {
        try {
            TextFile file;

            file = jListSourceSampleAmnSemanticsNonRecFiles.addFile("%mch%ref%", "AMN specifications (*.mch, *.ref)");
            jListSourceSampleAmnSemanticsRecFiles.addFile(file);
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonSampleAmnSemanticsNonRecDelete_actionPerformed(ActionEvent e) {
        jListSourceSampleAmnSemanticsNonRecFiles.removeSelectedFile();
    }

    public void jButtonSampleAmnSemanticsRecBrowse_actionPerformed(ActionEvent e) {
        try {
            jListSourceSampleAmnSemanticsRecFiles.addFile("%mch%ref%", "AMN specifications (*.mch, *.ref)");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonSampleAmnSemanticsRecExport_actionPerformed(ActionEvent e) {
        try {
            jListSourceSampleAmnSemanticsRecFiles.exportSelectedFile("%mch%ref%",
                    "AMN specifications (*.mch, *.ref)");
        } catch (IOException ex) {
             logger.info(ex.toString());
        }
    }

    public void jButtonSampleAmnSemanticsRecDelete_actionPerformed(ActionEvent e) {
        jListSourceSampleAmnSemanticsRecFiles.removeSelectedFile();
    }

    public void jButtonTargetSampleAmnSemanticsRecBrowse_actionPerformed(ActionEvent e) {
        try {
            jListTargetSampleAmnSemanticsRecFiles.addFile("%mch%ref%",
                    "AMN specifications (*.mch, *.ref)");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonTargetSampleAmnSemanticsRecExport_actionPerformed(ActionEvent e) {
        try {
            jListTargetSampleAmnSemanticsRecFiles.exportSelectedFile("%mch%ref%",
                    "AMN specifications (*.mch, *.ref)");
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }

    public void jButtonTargetSampleAmnSemanticsRecDelete_actionPerformed(ActionEvent e) {
        jListTargetSampleAmnSemanticsRecFiles.removeSelectedFile();
    }


    public void jButtonRefinementProofBrowse_actionPerformed(ActionEvent e) {
        try {
            jTextAreaRefinementProof.importTextFromFile("", "");
        } catch (FileNotFoundException ex) {
            logger.info(ex.toString());
        } catch (IOException ex) {
            logger.info(ex.toString());
        }
    }
    // END Samples tab

    // END GUI part

    // START GUI support part

    // set dividers locations
    private void setDividersLocations(){
        jSplitPaneModelSyntax.setDividerLocation(150);
        jSplitPaneAmnSemantics.setDividerLocation(200);
        jSplitPaneReferenceSchema.setDividerLocation(300);
        jSplitPaneTranslator.setDividerLocation(200);
        jSplitPaneSamples.setDividerLocation(150);
        jSplitPaneTranslator2Amn.setDividerLocation(150);
        jSplitPaneTranslator2TM.setDividerLocation(150);
        jSplitPaneSourceSampleAmnSemantics.setDividerLocation(300);
        jSplitPaneSampleAmnSemanticsNonRec.setDividerLocation(150);
        jSplitPaneSourceSampleAmnSemanticsRec.setDividerLocation(150);
        jSplitPaneTargetSampleAmnSemantics.setDividerLocation(300);
        jSplitPaneTargetSampleAmnSemanticsNonRec.setDividerLocation(150);
        jSplitPaneTargetSampleAmnSemanticsRec.setDividerLocation(150);
    }


    // END GUI support part


    // START Implementation part

    private Logger logger;

    private DB db;
    private ModelRegDef model;
    // current shown sample of the model
    private SampleRegDef sample;

    private SchemaEditorGui schemaEditor = null;
    private SchemaMappingGui schemaMapping = null;


    private void createNewModel() throws SQLException {
        model = db.new_modelReg();
        model.set_target(ModelKindChooserDialog.showModelKindChooserDialog());
        setModelKindDependableElements();
    }

    // Shows all existing elements of the registartion card.
    // Enables allowed tabs.
    private void openModel() throws SQLException {
        // Show model kind (source or target)
        setModelKindDependableElements();

        // show registartion card data
        jTextFieldShortTitle.setText(model.get_name());
        jTextFieldFullTitle.setText(model.get_fullTitle());
        jTextFieldSyntaxDocTitle.setText(model.get_syntDocTitle());
        jTextFieldSyntaxLink.setText(model.get_syntDocURI());
        jTextFieldSemanticsDocTitle.setText(model.get_semDocTitle());
        jTextFieldSemanticsLink.setText(model.get_semDocURI());

        // show syntax document date
        if (model.get_syntDocDate() != null) {
            ((JFormattedTextField) jFormattedTextFieldSyntaxDate).setValue(
                    DateHelper.convertDateIntoFormattedText(model.get_syntDocDate()));
            logger.info(DateHelper.convertDateIntoFormattedText(model.get_syntDocDate()));
        }

        // show semantics document date
        if (model.get_semDocDate() != null) {
            ((JFormattedTextField) jFormattedTextFieldSemanticsDate).setValue(
                    DateHelper.convertDateIntoFormattedText(model.get_semDocDate()));
            logger.info(DateHelper.convertDateIntoFormattedText(model.get_syntDocDate()));
        }

        // show refined extension name
        showRefinedExtensionName();

        // enable Model Syntax tab
        jTabbedPaneActivities.setEnabledAt(1, true);

        // show Model Syntax
        jListModelSyntaxFiles.fillFromFileContainerSet(model.get_abstrSyntax());

        // If abstract syntax is presented
        // enable AMN Semantics, Reference Schema, Samples tabs
        if(model.get_abstrSyntax().size() != 0){
            jTabbedPaneActivities.setEnabledAt(2, true);
            jTabbedPaneActivities.setEnabledAt(3, true);
            jTabbedPaneActivities.setEnabledAt(6, true);
        }

        // show verbal AMN semantics
        jTextAreaVerbalAmnSemantics.setText(model.get_verbAmnSem());

        // show AMN semantics
        jListTranslator2AmnFiles.fillFromFileContainerSet(model.get_transToAmn());

        // show Reference Schema Template
        jTextAreaReferenceSchemaTemplate.setText(model.get_refSchTmpl());

        // if reference schema is presented
        // enable Reference Schema Integration, Translator to Target Model tabs
        // show reference schema
        if(model.get_refSchema() != null){
            jTabbedPaneActivities.setEnabledAt(4, true);
            jTabbedPaneActivities.setEnabledAt(5, true);

            showReferenceSchemaEditor();
        }

        // show verbal rules of translation into target model
        jTextAreaVerbalRules.setText(model.get_verbMapTarget());
        jListTranslator2TMFiles.fillFromFileContainerSet(model.get_transTargetModel());

        // show existing samples
        showExistingSamples();
    }

    public void setModelKindDependableElements() throws SQLException {
        if(model.get_target()){
            this.setTitle("Model Registrar --- TARGET model");
            jLabelRefinedModel.setText("Source model:");
            jButtonExploreExtension.setToolTipText("Explore extensions to search for extension to be source model");
            this.jButtonTranslateSampleTargetSpec2Amn.setEnabled(false);
        } else {
            this.setTitle("Model Registrar --- SOURCE model");
            jLabelRefinedModel.setText("Target model:");
            jButtonExploreExtension.setToolTipText("Explore extensions to search for extension to be target model");
            this.jButtonTranslate2AmnSampleSourceSpec.setEnabled(false);
        }
    }

    public void showRefinedExtensionName() throws SQLException {
        if(model.get_refinedExtension() != null){
            jTextFieldRefinedModel.setText(model.get_refinedExtension().get_name());
            showReferenceSchemaIntegration();
        }
    }

    // START Registration Card tab

    private void updateModelRegCard() throws SQLException {
        int year;
        int month;
        int day;
        Date date;


        // if a model has not been created yet, create it;
        // enable Model Syntax tab
        if (jTextFieldShortTitle.getText().trim().compareTo("") != 0) {
            if (model == null) createNewModel();
            jTabbedPaneActivities.setEnabledAt(1, true);
        } else {
            JOptionPane.showMessageDialog(null, "Short title have to be present.");
            return;
        }


        model.set_name(jTextFieldShortTitle.getText());
        model.set_fullTitle(jTextFieldFullTitle.getText());
        model.set_syntDocTitle(jTextFieldSyntaxDocTitle.getText());
        model.set_syntDocURI(jTextFieldSyntaxLink.getText());
        model.set_semDocTitle(jTextFieldSemanticsDocTitle.getText());
        model.set_semDocURI(jTextFieldSemanticsLink.getText());


        // START update syntax document date
        date = null;

        try {
            date = DateHelper.convertFormattedStringIntoDate(jFormattedTextFieldSyntaxDate.getText());
        } catch (Exception ex) {
            logger.info(ex.toString());
            JOptionPane.showMessageDialog(null, "Incorrect syntax document date.");
        }

        if (date != null) {
            model.set_syntDocDate(date);
            ((JFormattedTextField)jFormattedTextFieldSyntaxDate).setValue(jFormattedTextFieldSyntaxDate.getText());
        }
        // END update syntax document date

        // START update semantics document date
        date = null;

        try {
            date = DateHelper.convertFormattedStringIntoDate(jFormattedTextFieldSemanticsDate.getText());
        } catch (Exception ex) {
            logger.info(ex.toString());
            JOptionPane.showMessageDialog(null, "Incorrect semantics document date.");
        }

        if (date != null) {
            model.set_semDocDate(date);
            ((JFormattedTextField)jFormattedTextFieldSemanticsDate).setValue(jFormattedTextFieldSemanticsDate.getText());
        }
        // END update semantics document date

        // commit changes in repository
        // db.commit();
    }

    private void removeModel() throws SQLException {
        int answer = JOptionPane.showConfirmDialog(
                null,
                "Remove model " + jTextFieldShortTitle.getText() + " from the Model Registry?",
                "Remove model",
                JOptionPane.YES_NO_OPTION);

        if (answer == 0) {
            RepositoryCleaner.delete(model);
            // db.commit();

            this.dispose();
        }
    }

    private void exploreExtension() throws SQLException {
        ExtensionExplorer explorer;

        if(model.get_target())
            logger.info("Initiating search for an extension to be source model ...");
        else
            logger.info("Initiating search for an extension to be target model ...");

        explorer = new ExtensionExplorer(this, model);
    }
    // END Registration Card tab


    // START Model Syntax Tab
    private void updateAbstractSyntax() throws SQLException {
        jListModelSyntaxFiles.loadFilesToRepository(db, model.get_abstrSyntax());

        // commit changes in repository
        // db.commit();

        // if syntax is presented
        // enable AMN Semantics, Reference Schema, Samples tabs
        if(model.get_abstrSyntax().size() != 0){
            jTabbedPaneActivities.setEnabledAt(2, true);
            jTabbedPaneActivities.setEnabledAt(3, true);
            jTabbedPaneActivities.setEnabledAt(6, true);
        }
    }
    // END Model Syntax Tab

    // START AMN semantics tab
    private void updateAmnSemantics() throws SQLException {
        model.set_verbAmnSem(jTextAreaVerbalAmnSemantics.getText());
        jListTranslator2AmnFiles.loadFilesToRepository(db, model.get_transToAmn());

        // commit changes in repository
        // db.commit();
    }
    // END AMN semantics tab

    // START Reference Schema tab
    private void createReferenceSchemaTemplate() throws SQLException, SdfDefinitionException,
            SDF2RSException, ReferenceSchemaModelException,
            RecognitionException, IOException {

        Module module = null;

        module = createSdfForModelSyntax();

        FormattingWriter writer = new DefaultFormattingWriter(new StringWriter(), 1, "  ", "\n");
        SDF2RS sdf2so = new SDF2RS(module, writer);

        sdf2so.generateSchema();
        jTextAreaReferenceSchemaTemplate.setText(writer.toString());
    }

    private Module createSdfForModelSyntax() throws IOException,
            SdfDefinitionException, RecognitionException {
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

        return module;
    }

    private void editReferenceSchema() throws SQLException {
        StringReader reader = new StringReader(jTextAreaReferenceSchemaTemplate.getText());
        ModuleDef module;

        if(model.get_refSchema() != null){
            //RepositoryCleaner.delete(model.get_refSchema());
            //model.get_refSchema().delete();
        }
        module = (new SynthesisLoader()).loadFile(db, reader, false);
        model.set_refSchema(module);

        showReferenceSchemaEditor();
        showReferenceSchemaIntegration();
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
                model.set_refSchema(module);
        }

        showReferenceSchemaEditor();
        showReferenceSchemaIntegration();
    }

    private void showReferenceSchemaEditor() throws SQLException {
        SchemaEditorGui gui = null;
        ModuleDef refSchema = null;

        refSchema = model.get_refSchema();

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

    private void closeReferenceSchemaEditor(){
        if(schemaEditor != null){
            schemaEditor.end();
            jPanelReferenceSchemaEditor.remove(schemaEditor);
        }
        logger.info("Schema editor closed.");
    }

    private void updateReferenceSchema() throws SQLException {
        model.set_refSchTmpl(jTextAreaReferenceSchemaTemplate.getText());
        // db.commit();

        // if Reference Schema is presented
        // enable Reference Schema Integration, Translator to Target Model tabs
        if(model.get_refSchema() != null){
            jTabbedPaneActivities.setEnabledAt(4, true);
            jTabbedPaneActivities.setEnabledAt(5, true);
        }

    }
    // END Reference Schema tab

    // START Reference Schema Integration tab
    private void showReferenceSchemaIntegration() throws SQLException {
        SchemaMappingGui gui = null;
        ModuleDef modelRefSchema = null, extRefSchema = null;
        ExtensionRegDef extension = null;

        extension = model.get_refinedExtension();
        if(extension == null) return;

        modelRefSchema = model.get_refSchema();
        extRefSchema = extension.get_refSchema();

        if(modelRefSchema != null && extRefSchema != null){
            closeReferenceSchemaIntegration();
            gui = new SchemaMappingGui();
            schemaMapping = gui;
            jPanelReferenceSchemaIntegrationStub.add("Center", gui);

            if(model.get_target())
                gui.start(extRefSchema, modelRefSchema);
            else
                gui.start(modelRefSchema, extRefSchema);

            validate();
            repaint();
        }
    }

    private void closeReferenceSchemaIntegration(){
        if(schemaMapping != null){
            schemaMapping.end();
            jPanelReferenceSchemaEditor.remove(schemaMapping);
        }
        logger.info("Schema integration closed.");
    }
    // END Reference Schema Integration tab

    // START Translator to Target Model tab
    private void generateTranslatorToTargetModelTemplate() throws  SQLException,
            SdfDefinitionException, TranslatorTemplateConstructionException,
            RecognitionException, IOException {
        Module source = null, target = null;
        ModuleDef sourceSchema = null, targetSchema = null;
        Set<Similarity> sims;
        FormattingWriter signaturesWriter, equationsWriter;
        TranslatorTemplateConstructor constructor;
        TextFile signatures, equations;
        DefaultListModel listModel;


        if(model.get_target()){
            target = this.createSdfForModelSyntax();
            targetSchema = model.get_refSchema();

            if(model.get_refinedExtension() == null)
                logger.info("Source extension does not exists");
            else {
                source = SdfForModelSyntaxCreator.createSdf(model.get_refinedExtension());
                sourceSchema = model.get_refinedExtension().get_refSchema();
            }
        } else {
            source = this.createSdfForModelSyntax();
            sourceSchema = model.get_refSchema();

            if(model.get_refinedExtension() == null)
                logger.info("Target extension does not exists");
            else {
                target = SdfForModelSyntaxCreator.createSdf(model.get_refinedExtension());
                targetSchema = model.get_refinedExtension().get_refSchema();
            }
        }

        //sims = TestSimilarityCreator.createSimilarities();
        sims = (new SimilarityCreator(sourceSchema, targetSchema)).createSimilarities();

        signaturesWriter = new DefaultFormattingWriter(new StringWriter(), 1, "  ", "\n");
        equationsWriter = new DefaultFormattingWriter(new StringWriter(), 1, "  ", "\n");

        constructor = new TranslatorTemplateConstructor(source, target, sims, signaturesWriter, equationsWriter);
        constructor.writeTranslatorTemplate();

        signaturesWriter.flush();
        equationsWriter.flush();

        signatures = new TextFile(model.get_name()+"2TargetModelTranslator.sdf", signaturesWriter.toString());
        equations = new TextFile(model.get_name()+"2TargetModelTranslator.asf", equationsWriter.toString());

        listModel = (DefaultListModel)jListTranslator2TMFiles.getModel();
        listModel.addElement(signatures);
        listModel.addElement(equations);
    }

    private void updateTranslatorToTargetModel() throws SQLException {
        model.set_verbMapTarget(jTextAreaVerbalRules.getText());
        jListTranslator2TMFiles.loadFilesToRepository(db, model.get_transTargetModel());

        // db.commit();
    }
    // END Translator to Target Model tab


    // START Sample tab

    private void clearSampleCard(){
        jTextFieldSampleTitle.setText("");
        jTextAreaSampleSpec.setText("");
        jTextAreaSampleTargetSpec.setText("");
        jCheckBoxSampleVerification.setSelected(false);

        ((DefaultListModel)jListSourceSampleAmnSemanticsNonRecFiles.getModel()).removeAllElements();
        jTextAreaSourceSampleAmnSemanticsNonRec.setText("");
        ((DefaultListModel)jListSourceSampleAmnSemanticsRecFiles.getModel()).removeAllElements();
        jTextAreaSourceSampleAmnSemanticsRec.setText("");

        ((DefaultListModel)jListTargetSampleAmnSemanticsNonRecFiles.getModel()).removeAllElements();
        jTextAreaTargetSampleAmnSemanticsNonRec.setText("");
        ((DefaultListModel)jListTargetSampleAmnSemanticsRecFiles.getModel()).removeAllElements();
        jTextAreaTargetSampleAmnSemanticsRec.setText("");

        jTextAreaRefinementProof.setText("");

        for (int i = 1; i <= 3; i++) {
            jTabbedPaneSample.setEnabledAt(i, false);
        }
    }

    private void showExistingSamples() throws SQLException {
        // add samples from model into samples list
        for (Object s: model.get_samples()) {
            SampleRegDef sample = (SampleRegDef) s;
            ((DefaultListModel)jListExistingSamples.getModel()).addElement(sample);
        }
    }

    private void removeSelectedSample() throws SQLException {
        if (!jListExistingSamples.isSelectionEmpty()){
            SampleRegDef selection = (SampleRegDef)jListExistingSamples.getSelectedValue();

            // remove selection from list
            ((DefaultListModel)jListExistingSamples.getModel()).remove(jListExistingSamples.getSelectedIndex());
            // clear card
            clearSampleCard();
            // remove selected sample from repository
            RepositoryCleaner.delete(selection);
            // reset current sample
            sample = null;
            //commit changes
            // db.commit();
        }
    }

    private void registerNewSample() throws SQLException {
        SampleRegDef newSample;

        // clearSampleCard, clear selection at samples list
        clearSampleCard();
        jListExistingSamples.clearSelection();
        // create sample
        newSample = db.new_sampleReg();
        newSample.set_name("NewSample");
        // add sample to model
        model.get_samples().add(newSample);
        // add sample to existing sample list
        ((DefaultListModel)jListExistingSamples.getModel()).addElement(newSample);
        // show sample title
        jTextFieldSampleTitle.setText("NewSample");
        // set current sample
        sample = newSample;
        // commit creation
        // db.commit();
    }

    private void openSelectedSample() throws SQLException {
        if(jListExistingSamples.isSelectionEmpty()) return;

        SampleRegDef selected = (SampleRegDef)jListExistingSamples.getSelectedValue();

        // set current sample
        sample = selected;

        clearSampleCard();

        // Show elements of the selected sample
        jTextFieldSampleTitle.setText(selected.get_name());
        jTextAreaSampleSpec.setText(selected.get_specification());
        jTextAreaSampleTargetSpec.setText(selected.get_targetSpec());
        jCheckBoxSampleVerification.setSelected(selected.get_refIsProved());
        jListSourceSampleAmnSemanticsNonRecFiles.fillFromFileContainerSet(selected.get_amnSpec());
        jListSourceSampleAmnSemanticsRecFiles.fillFromFileContainerSet(selected.get_recAmnSpec());
        jListTargetSampleAmnSemanticsNonRecFiles.fillFromFileContainerSet(selected.get_targetAmnSpec());
        jListTargetSampleAmnSemanticsRecFiles.fillFromFileContainerSet(selected.get_recTargetAmnSpec());
        jTextAreaRefinementProof.setText(selected.get_proof());

        enableSampleTabs();
    }

    private void enableSampleTabs(){
        // Tabs control
        //
        // If sample specification is presented, open AMN Semantics tab.
        if (jTextAreaSampleSpec.getText().trim().compareTo("") != 0)
                jTabbedPaneSample.setEnabledAt(1, true);
        // If target sample specification is presented, open Target AMN Semantics tab.
        if (jTextAreaSampleTargetSpec.getText().trim().compareTo("") != 0)
                jTabbedPaneSample.setEnabledAt(2, true);
        // If both reconciled sample AMN semantics and
        // reconciled targetl sample AMN semantics are presented,
        // open Refinement Proof tab.
        if (jListSourceSampleAmnSemanticsRecFiles.getModel().getSize() != 0 &&
            jListTargetSampleAmnSemanticsRecFiles.getModel().getSize() != 0) {
            jTabbedPaneSample.setEnabledAt(3, true);
        }
    }

    private void updateSampleCard() throws SQLException {
        if(sample == null) return;

        sample.set_name(jTextFieldSampleTitle.getText());
        sample.set_specification(jTextAreaSampleSpec.getText());
        sample.set_targetSpec(jTextAreaSampleTargetSpec.getText());
        sample.set_refIsProved(jCheckBoxSampleVerification.isSelected());

        jListSourceSampleAmnSemanticsNonRecFiles.loadFilesToRepository(db, sample.get_amnSpec());
        jListSourceSampleAmnSemanticsRecFiles.loadFilesToRepository(db, sample.get_recAmnSpec());
        jListTargetSampleAmnSemanticsNonRecFiles.loadFilesToRepository(db, sample.get_targetAmnSpec());
        jListTargetSampleAmnSemanticsRecFiles.loadFilesToRepository(db, sample.get_recTargetAmnSpec());

        sample.set_proof(jTextAreaRefinementProof.getText());

        enableSampleTabs();

        // db.commit();

        logger.info("Update of sample registration card finished.");
    }

    private void translateSampleSourceSpec2Amn() throws SQLException, IOException {
        StringReader reader = null;
        Set<TextFile> files = null;

        // Model is target, so source model is canonical.
        // Source sample is translated into AMN by ADT2BTranslator
        if(model.get_target()){
            reader = new StringReader(sample.get_targetSpec());
            files = translateSampleSpec2Amn(reader);

            if(files.size() != 0){
                logger.info("Showing AMN specs ...");
                jListSourceSampleAmnSemanticsNonRecFiles.fillFromTextFileSet(files);
                jListSourceSampleAmnSemanticsRecFiles.fillFromTextFileSet(files);
            } else
                logger.info("AMN specs were not created.");
        }
    }

    private void translateSampleTargetSpec2Amn() throws SQLException, IOException {
        StringReader reader = null;
        Set<TextFile> files = null;

        // Model is source, so target model is canonical.
        // Target sample is translated into AMN by ADT2BTranslator
        if(!model.get_target()){
            reader = new StringReader(sample.get_targetSpec());
            files = translateSampleSpec2Amn(reader);

            if(files.size() != 0){
                logger.info("Showing AMN specs ...");
                jListTargetSampleAmnSemanticsNonRecFiles.fillFromTextFileSet(files);
                jListTargetSampleAmnSemanticsRecFiles.fillFromTextFileSet(files);
            } else
                logger.info("AMN specs were not created.");
        }
    }


    // A sample should consist of one module possibly containing several types
    private Set<TextFile> translateSampleSpec2Amn(Reader reader) throws SQLException, IOException {
        ModuleDef module = null;
         Set<TextFile> result = null;

        // Load sample spec into repository.
        //
        module = (new SynthesisLoader()).loadFile(db, reader, false);

        if(module != null){
            logger.info("Module "+module.get_name()+" loaded.");
        } else {
            logger.info("Empty module created while loading into repository.");
            return null;
        }

        // define an ADT to translate into AMN
        Set types = module.get_containedTypes();
        TypeDef theType = null;
        if(types.size() == 0){
            logger.info("Sample spec contain no types.");
        } else if(types.size() == 1){
            theType = (TypeDef)types.iterator().next();
        } else {
            // If the module contains several types,
            // offer to choose on of them to translate into AMN
            theType = (TypeDef)ElementChooserDialog.showElementChooserDialog("Choose a type to translate into AMN", types);
        }

        // ADT was not chosen, translate whole module.
        if(theType == null){
            logger.info("Sample ADT was not chosen, whole module is translated.");

            Module2BTranslator translator = new Module2BTranslator();
            translator.translate(module);
            translator.generateSetOperations();
            translator.generateIncludeElementInClassOperations();
            result = translator.getResultedMachines();
        } else
        // translate the ADT into AMN and show the result
        if(theType instanceof ADTDef){
            Module2BTranslator t = new Module2BTranslator();
            t.translate((ADTDef)theType);
            result = t.getResultedMachines();
        } else {
            logger.info("Chosen type "+theType.get_name()+" is not ADT.");
        }

        RepositoryCleaner.delete(module);
        return result;
    }

    // END Sample tab
}


class ModelRegistrar_jButtonBrowseSchemaModule_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseSchemaModule_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseSchemaModule_actionPerformed(e);
    }
}


// Action Adapters


class ModelRegistrar_this_windowAdapter extends WindowAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_this_windowAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}



class ModelRegistrar_jButtonTranslate2AmnSampleSourceSpec_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonTranslate2AmnSampleSourceSpec_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonTranslate2AmnSampleSourceSpec_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonExploreExtension_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonExploreExtension_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExploreExtension_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonRefinementProofBrowse_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonRefinementProofBrowse_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonRefinementProofBrowse_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonTargetSampleAmnSemanticsRecDelete_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonTargetSampleAmnSemanticsRecDelete_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonTargetSampleAmnSemanticsRecDelete_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonTargetSampleAmnSemanticsRecExport_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonTargetSampleAmnSemanticsRecExport_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonTargetSampleAmnSemanticsRecExport_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonTargetSampleAmnSemanticsRecBrowse_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonTargetSampleAmnSemanticsRecBrowse_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonTargetSampleAmnSemanticsRecBrowse_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonSampleAmnSemanticsRecDelete_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonSampleAmnSemanticsRecDelete_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSampleAmnSemanticsRecDelete_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonSampleAmnSemanticsRecExport_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonSampleAmnSemanticsRecExport_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSampleAmnSemanticsRecExport_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonSampleAmnSemanticsRecBrowse_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonSampleAmnSemanticsRecBrowse_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSampleAmnSemanticsRecBrowse_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonSampleAmnSemanticsNonRecDelete_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonSampleAmnSemanticsNonRecDelete_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSampleAmnSemanticsNonRecDelete_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonSampleAmnSemanticsNonRecBrowse_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonSampleAmnSemanticsNonRecBrowse_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSampleAmnSemanticsNonRecBrowse_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonUpdateSampleRegCard_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonUpdateSampleRegCard_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateSampleRegCard_actionPerformed(e);
    }
}


class ModelRegistrar_jCheckBoxSampleVerification_mouseAdapter extends
        MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jCheckBoxSampleVerification_mouseAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jCheckBoxSampleVerification_mouseClicked(e);
    }
}


class ModelRegistrar_jButtonTranslateSampleTargetSpec2Amn_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonTranslateSampleTargetSpec2Amn_actionAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonTranslateSampleTargetSpec2Amn_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseSampleTargetSpec_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseSampleTargetSpec_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseSampleTargetSpec_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonExportSampleSpec_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonExportSampleSpec_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExportSampleSpec_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseSampleSpec_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseSampleSpec_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseSampleSpec_actionPerformed(e);
    }
}


class ModelRegistrar_jListTargetSampleAmnSemanticsNonRecFiles_mouseAdapter extends
        MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListTargetSampleAmnSemanticsNonRecFiles_mouseAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListTargetSampleAmnSemanticsNonRecFiles_mouseClicked(e);
    }
}


class ModelRegistrar_jListTargetSampleAmnSemanticsRecFiles_mouseAdapter extends
        MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListTargetSampleAmnSemanticsRecFiles_mouseAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListTargetSampleAmnSemanticsRecFiles_mouseClicked(e);
    }
}


class ModelRegistrar_jListSampleAmnSemanticsNonRecFiles_mouseAdapter extends
        MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListSampleAmnSemanticsNonRecFiles_mouseAdapter(
            ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListSampleAmnSemanticsNonRecFiles_mouseClicked(e);
    }
}


class ModelRegistrar_jListSampleAmnSemanticsRecFiles_mouseAdapter extends
        MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListSampleAmnSemanticsRecFiles_mouseAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListSampleAmnSemanticsRecFiles_mouseClicked(e);
    }
}


class ModelRegistrar_jListExistingSamples_mouseAdapter extends MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListExistingSamples_mouseAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListExistingSamples_mouseClicked(e);
    }
}


class ModelRegistrar_jButtonDeleteSample_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonDeleteSample_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteSample_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonNewSample_actionAdapter implements ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonNewSample_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonNewSample_actionPerformed(e);
    }
}


class ModelRegistrar_jListTranslator2CMFiles_mouseAdapter extends MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListTranslator2CMFiles_mouseAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListTranslator2CMFiles_mouseClicked(e);
    }
}

class ModelRegistrar_jButtonUpdateTranslator_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonUpdateTranslator_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateTranslator_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonExportTranslator_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonExportTranslator_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExportTranslator_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonDeleteTranslator_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonDeleteTranslator_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteTranslator_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonGenerateTemplate_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonGenerateTemplate_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonGenerateTemplate_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseTranslator_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseTranslator_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseTranslator_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonExportAmnTranslatorFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonExportAmnTranslatorFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExportAmnTranslatorFile_actionPerformed(e);
    }
}

class ModelRegistrar_jButtonExportModelSyntaxFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonExportModelSyntaxFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonExportModelSyntaxFile_actionPerformed(e);
    }
}

class ModelRegistrar_jListTranslator2AmnFiles_mouseAdapter extends MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListTranslator2AmnFiles_mouseAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.jListTranslator2AmnFiles_mousePressed(e);
    }
}


class ModelRegistrar_jButtonVODUpdate_actionAdapter implements ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonVODUpdate_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonVODUpdate_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonVODEdit_actionAdapter implements ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonVODEdit_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonVODEdit_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonCreateReferenceSchemaTemplate_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCreateReferenceSchemaTemplate_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonUpdateAmnSemantics_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonUpdateAmnSemantics_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateAmnSemantics_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonDeleteAmnTranslatorFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonDeleteAmnTranslatorFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteAmnTranslatorFile_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseAmnTranslatorFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseAmnTranslatorFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseAmnTranslatorFile_actionPerformed(e);
    }
}


class ModelRegistrar_jListModelSyntaxFiles_mouseAdapter extends MouseAdapter {
    private ModelRegistrar adaptee;
    ModelRegistrar_jListModelSyntaxFiles_mouseAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jListModelSyntaxFiles_mouseClicked(e);
    }
}


class ModelRegistrar_jButtonUpdateModelSyntax_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonUpdateModelSyntax_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateModelSyntax_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonDeleteModelSyntaxFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteModelSyntaxFile_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseModelSyntaxFile_actionAdapter(ModelRegistrar
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseModelSyntaxFile_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonDeleteCard_actionAdapter implements ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonDeleteCard_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDeleteCard_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonUpdateRegCard_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonUpdateRegCard_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonUpdateRegCard_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseSemantics_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseSemantics_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseSemantics_actionPerformed(e);
    }
}


class ModelRegistrar_jButtonBrowseSyntaxLink_actionAdapter implements
        ActionListener {
    private ModelRegistrar adaptee;
    ModelRegistrar_jButtonBrowseSyntaxLink_actionAdapter(ModelRegistrar adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBrowseSyntaxLink_actionPerformed(e);
    }
}
