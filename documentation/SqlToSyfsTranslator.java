package sql.translator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;
import queryprogram.mds.Module;
import queryprogram.mds.Schema;

import java.io.*;
import java.net.URL;

import sql.parser.CaseInsentitiveANTLRStringStream;
import sql.translator.SqlAstToSyfsTranslator;
import sql.SqlLexer;
import sql.SqlParser;

/**
 * User: DmitryM
 * Date: 19.11.2007
 * Time: 7:52:37
 */
public class SqlToSyfsTranslator {
    protected Resolver resolver;
    protected StringTemplateGroup stg;

    public SqlToSyfsTranslator(Resolver resolver) {
        this.resolver = resolver;
        this.stg = loadSyfsGroup();
    }

    public SqlToSyfsTranslator(Schema schema, Module[] defaultModules) {
        this(new Resolver(schema, defaultModules));
    }

    protected StringTemplateGroup loadSyfsGroup() {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Reader syfsGroupReader = new InputStreamReader(loader.getResourceAsStream("sql/translator/syfs.stg"));

        return new StringTemplateGroup(syfsGroupReader, AngleBracketTemplateLexer.class);
    }

    public void translate(String sql, Writer syfsWriter) throws RecognitionException, IOException {
        ANTLRStringStream stream = new CaseInsentitiveANTLRStringStream(sql);
        CommonTokenStream tokenStream = new CommonTokenStream(new SqlLexer(stream));
        SqlParser parser = new SqlParser(tokenStream);
        Tree tree = (Tree) parser.select().getTree();

        SqlAstToSyfsTranslator tran = new SqlAstToSyfsTranslator(resolver, tree, stg, syfsWriter);
        tran.writeQuery();
    }

    public String translate(String sql) throws IOException, RecognitionException {
        StringWriter syfsWriter = new StringWriter();
        translate(sql, syfsWriter);

        return syfsWriter.toString();
    }

}
