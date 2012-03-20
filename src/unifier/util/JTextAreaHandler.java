package unifier.util;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
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
public class JTextAreaHandler extends Handler {

    public JTextAreaHandler(JTextArea area){
        textArea = area;
    }

    private JTextArea textArea;

    /**
     * Close the <tt>Handler</tt> and free all associated resources.
     *
     * @throws SecurityException if a security manager exists and if the
     *   caller does not have <tt>LoggingPermission("control")</tt>.
     * @todo Implement this java.util.logging.Handler method
     */
    public void close() throws SecurityException {
    }

    /**
     * Flush any buffered output.
     *
     * @todo Implement this java.util.logging.Handler method
     */
    public void flush() {
    }

    /**
     * Publish a <tt>LogRecord</tt>.
     *
     * @param record description of the log event. A null record is silently
     *   ignored and is not published
     * @todo Implement this java.util.logging.Handler method
     */
    public void publish(LogRecord record) {
        String recordString;

        recordString = record.getLoggerName() + ": " + record.getMessage() + "\n";

        textArea.append(recordString);
        System.out.print(recordString);
    }
}
