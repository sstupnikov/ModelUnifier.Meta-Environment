package unifier.util;

import java.io.Writer;
import java.io.IOException;

public abstract class FormattingWriter {
    protected Writer writer;

    protected FormattingWriter(Writer writer) {
        this.writer = writer;
    }

    public abstract void indent();
    public abstract int changeIndent(int i);
    public abstract void ln(int i);

    public void ln() {
        ln(0);
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Writer writer() {
        return writer;
    }

    public void write(String text) {
        try {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(char ch) {
        try {
            writer.write(ch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return writer.toString();
    }
}

