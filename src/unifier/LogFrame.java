package unifier;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JTextArea;

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
public class LogFrame extends JFrame {
    public LogFrame() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setTitle("Log");
        this.getContentPane().setLayout(gridBagLayout1);
        jTextAreaLog.setEditable(false);
        this.getContentPane().add(jScrollPaneLog,
                                  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 400, 300));
        jScrollPaneLog.getViewport().add(jTextAreaLog);
        jTextAreaLog.setText("");
        jScrollPaneLog.setPreferredSize(new Dimension(300, 200));
    }

    JScrollPane jScrollPaneLog = new JScrollPane();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JTextArea jTextAreaLog = new JTextArea();


    public JTextArea getLogTextArea(){
        return jTextAreaLog;
    }

    public void appendLog (String s){
        jTextAreaLog.append(s);
    }

}
