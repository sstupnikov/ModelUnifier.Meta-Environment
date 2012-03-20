package unifier;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

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
public class CenteredFrame extends JFrame {

    protected void center(){
        Dimension screenSize;
        Dimension frameSize;

        pack();

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameSize = getSize();
        if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width) frameSize.width = screenSize.width;
        setSize(frameSize);
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        setVisible(true);
    }
}
