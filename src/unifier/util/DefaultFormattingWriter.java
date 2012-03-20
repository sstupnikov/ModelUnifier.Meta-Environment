package unifier.util;

import java.io.*;

public class DefaultFormattingWriter extends FormattingWriter {
    protected int indent = 0;
    protected String tab = "    ";
    protected String ln = "\n";
    protected static String outputPath = "";

    public static DefaultFormattingWriter createSystemOutWriter() {
        return new DefaultFormattingWriter(new OutputStreamWriter(System.out));
    }

   public static DefaultFormattingWriter createFileWriter(String path) throws IOException{
	 return new  DefaultFormattingWriter(new FileWriter(path));
   }

    public static DefaultFormattingWriter createStringWriter() {
        return new DefaultFormattingWriter(new StringWriter());
    }

    public DefaultFormattingWriter(Writer writer) {
        super(writer);
    }

    public DefaultFormattingWriter(Writer writer, int indent) {
        super(writer);
        this.indent = indent;
    }

    public DefaultFormattingWriter(Writer writer, int indent, String tab, String ln) {
        super(writer);
        this.indent = indent;
        this.tab = tab;
        this.ln = ln;
    }

    public DefaultFormattingWriter(FormattingWriter fw, int indent, String tab, String ln) {
        super(fw.writer);
        this.indent = indent;
        this.tab = tab;
        this.ln = ln;
    }

    public void ln(int i) {
        indent += i;
        write(ln);
        indent();
    }

    public void indent() {
        for(int i = 0; i < indent; i++) write(tab);
    }

    public int changeIndent(int i) {
        return (indent += i);
    }
}

