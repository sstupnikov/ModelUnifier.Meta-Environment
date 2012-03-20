package unifier;

import javax.swing.JFrame;
import java.util.*;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JOptionPane;
import meta.DB;
import java.sql.SQLException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JPanel;
import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Handler;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import unifier.util.JTextAreaHandler;
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
public class ModelManager extends CenteredFrame {
    public ModelManager() {
        modelRegistryConnection = "";

        // log init
        logFrame = new LogFrame();
        logFrame.pack();
        logFrame.validate();

        logger = Logger.getLogger("unifier");
        handler = new JTextAreaHandler(logFrame.getLogTextArea());
        logger.addHandler(handler);

        // END log init

        // GUI elements init
        try {
            jbInit();
            closeRegistryDependentMenuActions();

            // set windows style
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            logger.info("Model Manager GUI elements initialization failed.");
        }

        center();
    }

    private void jbInit() throws Exception {
        this.getContentPane().setBackground(SystemColor.control);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setJMenuBar(jMenuBarMR);
        this.setTitle("Model Manager");
        this.addWindowListener(new ModelManager_this_windowAdapter(this));
        this.getContentPane().setLayout(gridBagLayoutMM);
        jMenuFile.setMnemonic(KeyEvent.VK_F);
        jMenuFile.setText("File");
        jMenuItemOpenModelRegistry.setText("Open Model Registry ...");
        jMenuItemOpenModelRegistry.addActionListener(new
                ModelManager_jMenuItemOpenModelRegistry_actionAdapter(this));
        jMenuItemCloseModelRegistry.setText("Close Model Registry");
        jMenuItemCloseModelRegistry.addActionListener(new
                ModelManager_jMenuItemCloseModelRegistry_actionAdapter(this));
        jMenuView.setText("View");
        jMenuItemLog.setText("Log");
        jMenuItemLog.addActionListener(new
                ModelManager_jMenuItemLog_actionAdapter(this));
        jMenuItemNewModel.setText("New Model ...");
        jMenuItemNewModel.addActionListener(new
                ModelManager_jMenuItemNewModel_actionAdapter(this));
        jMenuSearch.setText("Search");
        jMenuSearch.setMnemonic(KeyEvent.VK_S);
        jMenuItemFindModel.setText("Find Model ...");
        jMenuItemFindModel.addActionListener(new
                ModelManager_jMenuItemFindModel_actionAdapter(this));
        jMenuItemExploreExtensions.setText("Explore Extensions ...");
        jMenuItemExploreExtensions.addActionListener(new
                ModelManager_jMenuItemExploreExtensions_actionAdapter(this));
        jPanelModelManager.setPreferredSize(new Dimension(300, 50));
        jMenuItemNewExtension.setText("New Extension ...");
        jMenuItemNewExtension.addActionListener(new
                ModelManager_jMenuItemNewExtension_actionAdapter(this));
        jMenuBarMR.add(jMenuFile);
        jMenuBarMR.add(jMenuSearch);
        jMenuBarMR.add(jMenuView);
        jMenuFile.add(jMenuItemOpenModelRegistry);
        jMenuFile.add(jMenuItemCloseModelRegistry);
        jMenuFile.add(jMenuItemNewModel);
        jMenuFile.add(jMenuItemNewExtension);
        jMenuView.add(jMenuItemLog);
        jMenuSearch.add(jMenuItemFindModel);
        jMenuSearch.add(jMenuItemExploreExtensions);
        this.getContentPane().add(jPanelModelManager,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

    }

    private GridBagLayout gridBagLayoutMM = new GridBagLayout();
    private JMenuBar jMenuBarMR = new JMenuBar();
    private JMenu jMenuFile = new JMenu();
    private JMenuItem jMenuItemOpenModelRegistry = new JMenuItem();
    private JMenuItem jMenuItemCloseModelRegistry = new JMenuItem();
    private JMenu jMenuView = new JMenu();
    private JMenuItem jMenuItemLog = new JMenuItem();
    private JMenuItem jMenuItemNewModel = new JMenuItem();
    private JMenu jMenuSearch = new JMenu();
    private JMenuItem jMenuItemFindModel = new JMenuItem();
    private JMenuItem jMenuItemExploreExtensions = new JMenuItem();
    private JPanel jPanelModelManager = new JPanel();
    private JMenuItem jMenuItemNewExtension = new JMenuItem();

    public void jMenuItemOpenModelRegistry_actionPerformed(ActionEvent e) {
        openModelRegistry();
    }

    public void jMenuItemCloseModelRegistry_actionPerformed(ActionEvent e) {
        closeConnectionToModelRegistry();
    }

    public void jMenuItemLog_actionPerformed(ActionEvent e) {
        logFrame.setVisible(true);
    }

    public void jMenuItemNewModel_actionPerformed(ActionEvent e) {
        try {
            registerNewModel();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jMenuItemNewExtension_actionPerformed(ActionEvent e) {
        try {
            registerNewExtension();
        } catch (SQLException ex) {
            logger.info(ex.toString());
        }
    }

    public void jMenuItemFindModel_actionPerformed(ActionEvent e) {
        searchForModel();
    }

    public void jMenuItemExploreExtensions_actionPerformed(ActionEvent e) {
        exploreExtensions();
    }

    public void this_windowClosing(WindowEvent e) {
        closeConnectionToModelRegistry();
        System.exit(0);
    }

    // END GUI part

    // START GUI support part
    private void closeRegistryDependentMenuActions(){
        jMenuItemCloseModelRegistry.setEnabled(false);
        jMenuItemOpenModelRegistry.setEnabled(true);
        jMenuItemNewModel.setEnabled(false);
        jMenuItemNewExtension.setEnabled(false);
        jMenuSearch.setEnabled(false);
    }

    private void openRegistryDependentMenuActions(){
        jMenuItemCloseModelRegistry.setEnabled(true);
        jMenuItemOpenModelRegistry.setEnabled(false);
        jMenuItemNewModel.setEnabled(true);
        jMenuItemNewExtension.setEnabled(true);
        jMenuSearch.setEnabled(true);
    }

    // END GUI support part

    // FIELDS
    static DB db;

    private Logger logger;
    private Handler handler;

    private LogFrame logFrame;
    private String modelRegistryConnection;


    // METHODS

    private void openModelRegistry() {
        // check last connection to the model registry string
        try{
            LineNumberReader initFile = new LineNumberReader(new FileReader(
                    "ModelRegistryConnection.ini"));
            modelRegistryConnection = initFile.readLine();
            initFile.close();
        }
        catch (IOException ex) {
            logger.info("Unable to access ModelRegistryConnection.ini\n");
            logger.info(ex.toString());
        }

        // input connection to the model registry string
        String connection = (String) JOptionPane.showInputDialog(
                null, "Input connection to a model registry:",
                "Model Registry Connection", JOptionPane.PLAIN_MESSAGE, null,
                null, modelRegistryConnection);

        if ((connection != null) && (connection.length() > 0)) {
            modelRegistryConnection = connection;
            logger.info("Trying to connect "+connection);
            establishConnectionToModelRegistry();
            if (db != null) {
                openRegistryDependentMenuActions();
            }
        }

        // save connection to the model registry string
        try {
            FileWriter initFile = new FileWriter("ModelRegistryConnection.ini");
            initFile.write(modelRegistryConnection);
            initFile.close();
        } catch (IOException ex) {
            logger.info("Unable to create ModelRegistryConnection.ini");
        }

    }

    private void establishConnectionToModelRegistry() {
        try {
            db = DB.open(modelRegistryConnection);
        } catch (SQLException ex) {
            logger.info("Failed to connect to "+modelRegistryConnection);
            logger.info(ex.toString());
        }
    }

    private void closeConnectionToModelRegistry(){
        if (db != null && db.getConnection() != null) {
            try {
                db.commit();
                logger.info("Model registry has been committed.");

                db.close();
                logger.info("Connection to model registry closed.");
            } catch (SQLException ex) {
                logger.info("Failed to close "+modelRegistryConnection);
                logger.info(ex.toString());
            }
        }

        System.out.println("CLOSED");

        closeRegistryDependentMenuActions();
    }


    private void registerNewModel() throws SQLException {
        ModelRegistrar registrar;

        logger.info("Creating new model ...");
        registrar = new ModelRegistrar(db);
    }

    private void searchForModel(){
        ModelExplorer explorer;

        logger.info("Initiating search for a model ...");
        explorer = new ModelExplorer(db);
    }

    private void exploreExtensions(){
        ExtensionExplorer explorer;

        logger.info("Exploring extensions ...");
        explorer = new ExtensionExplorer(db);
        explorer.pack();
        explorer.validate();
        explorer.setVisible(true);
    }

    private void registerNewExtension() throws SQLException {
        ExtensionRegistrar registrar;

        logger.info("Creating new extension ...");
        registrar = new ExtensionRegistrar(db);
        registrar.pack();
        registrar.validate();
        registrar.setVisible(true);
    }

}




// Action Adapters

class ModelManager_this_windowAdapter extends WindowAdapter {
    private ModelManager adaptee;
    ModelManager_this_windowAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}

class ModelManager_jMenuItemNewExtension_actionAdapter implements
        ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemNewExtension_actionAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemNewExtension_actionPerformed(e);
    }
}


class ModelManager_jMenuItemExploreExtensions_actionAdapter implements
        ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemExploreExtensions_actionAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemExploreExtensions_actionPerformed(e);
    }
}


class ModelManager_jMenuItemFindModel_actionAdapter implements ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemFindModel_actionAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemFindModel_actionPerformed(e);
    }
}


class ModelManager_jMenuItemNewModel_actionAdapter implements ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemNewModel_actionAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemNewModel_actionPerformed(e);
    }
}


class ModelManager_jMenuItemLog_actionAdapter implements ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemLog_actionAdapter(ModelManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemLog_actionPerformed(e);
    }
}


class ModelManager_jMenuItemCloseModelRegistry_actionAdapter implements
        ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemCloseModelRegistry_actionAdapter(ModelManager
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemCloseModelRegistry_actionPerformed(e);
    }
}


class ModelManager_jMenuItemOpenModelRegistry_actionAdapter implements
        ActionListener {
    private ModelManager adaptee;
    ModelManager_jMenuItemOpenModelRegistry_actionAdapter(ModelManager
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItemOpenModelRegistry_actionPerformed(e);
    }
}
