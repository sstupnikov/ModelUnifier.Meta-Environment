package sql.translator;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.AutoIndentWriter;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.StringTemplateWriter;
import queryprogram.mds.Attribute;
import queryprogram.mds.Class;
import static sql.SqlParser.*;
import sql.parser.SqlParserException;
import sql.schema.Column;
import sql.schema.SqlDataSourceSchema;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: DmitryM
 * Date: 12.11.2007
 * Time: 6:16:06
 */
public class SqlAstToSyfsTranslator {
    private final static String CATALOG_CLASS_NAME = "catalogData";
    private final static String DEFAULT_RESULT_CLASS_NAME = "R";

    private Resolver resolver;
    private Tree tree;
    private String resultClass = null;
    
    private StringTemplateGroup stg;
    private Map<String, Class> aliasToClassMap;
    private Map<String, DerivedColumn> columnNameToDerivedColumnMap = new HashMap<String, DerivedColumn>();

    private Writer writer;

    public SqlAstToSyfsTranslator(Resolver resolver, Tree tree, StringTemplateGroup stg, Writer writer) {
        this.resolver = resolver;
        this.tree = tree;
        this.stg = stg;
        this.writer = writer;
    }

    public void writeQuery() throws IOException {
        StringTemplate lpT = stg.getInstanceOf("logic_program");

        if (tree.getType() == UNION) union(tree, lpT);
        else select(tree, lpT);

        StringTemplateWriter stWriter = new AutoIndentWriter(writer);
        lpT.write(stWriter);
    }

    private void union(Tree tree, StringTemplate lpT) {
        Tree tree1 = tree.getChild(0);

        if (tree1.getType() == UNION) union(tree1, lpT);
        else select(tree1, lpT);

        select(tree.getChild(1), lpT);
    }

    private void select(Tree tree, StringTemplate lpT) {
        // patch INTO
        CommonTree selectTree = (CommonTree) tree;
        Tree t = selectTree.getChild(1); // INTO or FROM
        if (t.getType() == INTO) {
            if (resultClass == null) {
                resultClass = t.getChild(0).getText();
                selectTree.deleteChild(1);
            } else throw new SqlParserException(
                    "The INTO clause can be specified in the first SELECT clause only.", t);
        } else { // There is no INTO
            if (resultClass == null) resultClass = DEFAULT_RESULT_CLASS_NAME;
        }

        // collect tables
        Tree fromTree = tree.getChild(1);
        collectClasses(fromTree);
        deriveColumns();

        // expand stars
        CommonTree selectListTree = (CommonTree) tree.getChild(0);
        expandStars(selectListTree);

        Tree whereTree = tree.getChild(2);

        StringTemplate rule = stg.getInstanceOf("rule");

        StringTemplate head = stg.getInstanceOf("class_predicate");
        rule.setAttribute("head", head);
        head.setAttribute("class_name", resultClass);
        head.setAttribute("variable_id", "_");

        // process selec list
        int exprCount = 0;
        for (int i = 0; i < selectListTree.getChildCount(); i++) {
            Tree columnExprTree = selectListTree.getChild(i);
            Tree exprTree = columnExprTree.getChild(0);

            StringTemplate reductElementT = stg.getInstanceOf("reduct_element");

            String columnId = text(columnExprTree.getChild(1));

            if (exprTree.getType() == COLUMN_NAME) {
                if (columnId == null) columnId = text(exprTree.getChild(exprTree.getChildCount() - 1));
                reductElementT.setAttribute("attrs", translateColumnName(exprTree));
            }
            else {
                String attrName = String.format("Expr%d", exprCount++);
                if (columnId == null) columnId = attrName;

                StringTemplate idT = stg.getInstanceOf("id");
                idT.setAttribute("attr", attrName);
                idT.setAttribute("expr", expr(exprTree));
                rule.setAttribute("predicates", idT);

                reductElementT.setAttribute("attrs", attrName);
            }

            reductElementT.setAttribute("newAttr", columnId);
            head.setAttribute("reduct_elements", reductElementT);
        }

        // process where clause
        if (whereTree != null) {
            rule.setAttribute("predicates", logicalOp(whereTree.getChild(0)));
        }

        // process from clause
        SqlDataSourceSchema sqlSchema = (SqlDataSourceSchema) resolver.schema;

        List<DerivedColumn> derivedColumns = new ArrayList<DerivedColumn>();
        for (DerivedColumn dc : columnNameToDerivedColumnMap.values()) if (dc.useCount > 0) {
            if (!derivedColumns.contains(dc)) derivedColumns.add(dc);
            dc.derivedName = dc.column.name;
        }

        for (DerivedColumn dc : derivedColumns) {
            for (DerivedColumn _dc : derivedColumns) if (dc != _dc && dc.derivedName.equals(_dc.derivedName)) {
                dc.derivedName = dc.alias + "_" + dc.derivedName;
                _dc.derivedName = _dc.alias + "_" + _dc.derivedName;
            }
        }

        Map<String, StringTemplate> aliasToClassTMap = new HashMap<String, StringTemplate>(aliasToClassMap.size());

        for (DerivedColumn dc : derivedColumns) {
            StringTemplate cpT = aliasToClassTMap.get(dc.alias);
            if (cpT == null) {
                cpT = stg.getInstanceOf("class_predicate");
                aliasToClassTMap.put(dc.alias, cpT);
                
                cpT.setAttribute("variable_id", dc.alias);
                Class _class = aliasToClassMap.get(dc.alias);
                cpT.setAttribute("class_name", tableName(sqlSchema.getOriginalClassFromTable(_class)));

                rule.setAttribute("class_predicates", cpT);
            }

            StringTemplate reductElemT = stg.getInstanceOf("reduct_element");
            reductElemT.setAttribute("newAttr", dc.derivedName);
            reductElemT.setAttribute("attrs", dc.column.path);

            cpT.setAttribute("reduct_elements", reductElemT);
        }

        lpT.setAttribute("rules", rule);
    }

    private String text(Tree tree) {
        return tree != null ? tree.getText() : null;
    }

    private String tableName(Class _class) {
        return _class.getInModule().getName() + "." + _class.getName();
    }

    private Object logicalOp(Tree logicalOpTree) {
        switch(logicalOpTree.getType()) {
            case AND:
                StringTemplate conjT = stg.getInstanceOf("logical_conjunction");
                conjT.setAttribute("p1", logicalOp(logicalOpTree.getChild(0)));
                conjT.setAttribute("p2", logicalOp(logicalOpTree.getChild(1)));

                return conjT;

            case ARITH_PREDICATE:
                StringTemplate arithPredT = stg.getInstanceOf("arith_predicate");
                arithPredT.setAttribute("op", logicalOpTree.getText());
                arithPredT.setAttribute("e1", expr(logicalOpTree.getChild(0)));
                arithPredT.setAttribute("e2", expr(logicalOpTree.getChild(1)));

                return arithPredT;

            case BETWEEN: // x BETWEEN a AND b
                Object xExpr = expr(logicalOpTree.getChild(0));
                Object aExpr = expr(logicalOpTree.getChild(1));
                Object bExpr = expr(logicalOpTree.getChild(2));

                StringTemplate leT1 = stg.getInstanceOf("arith_predicate");
                leT1.setAttribute("op", "<=");
                leT1.setAttribute("e1", aExpr);
                leT1.setAttribute("e2", xExpr);

                StringTemplate leT2 = stg.getInstanceOf("arith_predicate");
                leT2.setAttribute("op", "<=");
                leT2.setAttribute("e1", xExpr);
                leT2.setAttribute("e2", bExpr);

                StringTemplate andT = stg.getInstanceOf("logical_conjunction");
                andT.setAttribute("p1", leT1);
                andT.setAttribute("p2", leT2);

                return andT;

            case LIKE:
                StringTemplate likeFuncT = stg.getInstanceOf("sql_like");
                likeFuncT.setAttribute("expr", expr(logicalOpTree.getChild(0)));
                likeFuncT.setAttribute("pattern", logicalOpTree.getChild(1).getText());
                if (logicalOpTree.getChild(2) != null)
                    likeFuncT.setAttribute("escape", logicalOpTree.getChild(2).getText());

                return likeFuncT;

            case XMATCH:
                SqlDataSourceSchema sqlSchema = (SqlDataSourceSchema) resolver.schema;

                StringTemplate matchT = stg.getInstanceOf("match");

                for (int i = 0; i < 2; i++) {
                    Tree aliasTree = logicalOpTree.getChild(i);
                    String alias = aliasTree.getText();
                    Class table = aliasToClassMap.get(alias);
                    if (table == null) throw new SqlParserException(
                            String.format("Unresolved table alias '%s'.", alias), aliasTree);

                    Class _class = sqlSchema.getOriginalClassFromTable(table);
                    if (!isCatalogClass(_class)) throw new SqlParserException(
                            String.format("XMATCH can not apply to the table '%s'.", tableName(_class)), aliasTree);

                    matchT.setAttribute("args", alias);
                }

                String rad = logicalOpTree.getChild(2).getText();
                matchT.setAttribute("r", rad);

                return matchT;

            default:
                throw new SqlParserException("Unsupported SQL construction", logicalOpTree);
        }
    }

    private boolean isCatalogClass(Class _class) {
        return CATALOG_CLASS_NAME.equals(_class.getName()) ||
                _class.getSuperClasses().length > 0 && isCatalogClass(_class.getSuperClasses()[0]);
    }

    private Object translateColumnName(Tree columnNameTree) {
        Tree id1 = columnNameTree.getChild(0);
        Tree id2 = columnNameTree.getChild(1);

        String id = text(id1);
        if (id2 != null) id += "." + text(id2);

        DerivedColumn dc = columnNameToDerivedColumnMap.get(id);

        if (dc == null) throw new SqlParserException(
                String.format("Unresolved column identifier '%s'.", id), id1);
        else if (dc == DerivedColumn.AMBIGUOUS_COLUMN_NAME) throw new SqlParserException(
                String.format("Ambiguous column identifier, there are more than one column '%s'.", id), id1);

        dc.useCount++;

        return dc;
    }

    private Object expr(Tree exprTree) {
        switch(exprTree.getType()) {
            case ARITH_BINARY_OP:
                StringTemplate arithBinOpT = stg.getInstanceOf("arith_binary_op");
                arithBinOpT.setAttribute("op", exprTree.getText());
                arithBinOpT.setAttribute("e1", expr(exprTree.getChild(0)));
                arithBinOpT.setAttribute("e2", expr(exprTree.getChild(1)));

                return arithBinOpT;

            case ARITH_UNARY_OP:
                StringTemplate arithUnaryOpT = stg.getInstanceOf("arith_unary_op");
                arithUnaryOpT.setAttribute("op", exprTree.getText());
                arithUnaryOpT.setAttribute("e1", expr(exprTree.getChild(0)));

                return arithUnaryOpT;

            case COLUMN_NAME:
                return translateColumnName(exprTree);

            case USER_FUNCTION:
                throw new SqlParserException("User functions are not supported in ADQL.", exprTree);

            case SQL_FUNCTION:
                StringTemplate funcT = stg.getInstanceOf("function");

                boolean isSqlFunc = exprTree.getType() == SQL_FUNCTION;
                if (isSqlFunc) {
                    funcT.setAttribute("id", "sql");
                    funcT.setAttribute("id", exprTree.getText());
                }
                else {
                    Tree objNameTree = exprTree.getChild(0);
                    for (int i = 0; i < objNameTree.getChildCount(); i++)
                        funcT.setAttribute("id", objNameTree.getChild(i).getText());
                }

                for (int i = isSqlFunc ? 0 : 1; i < exprTree.getChildCount(); i++)
                    funcT.setAttribute("args", expr(exprTree.getChild(i)));

                return funcT;

            default:
                return exprTree.getText();
        }
    }

    private void expandStars(CommonTree selectListTree) {
        if (selectListTree.getType() == STAR) { // SELECT DISTINCT * FROM ...
            selectListTree.token.setType(SELECT_LIST);
            selectListTree.token.setText(tokenNames[SELECT_LIST]);

            List<String> cols = new ArrayList<String>();

            for (Map.Entry<String, Class> classEntry : aliasToClassMap.entrySet()) {
                for (Attribute attr : classEntry.getValue().getInstanceType().getAttributes()) {
                    if (cols.contains(attr.name)) throw new SqlParserException(String.format(
                            "Result set includes few columns with the same name '%s', " +
                                    "the symbol * can not be used in the select list.", attr.name), selectListTree);

                    cols.add(attr.name);

                    String alias = classEntry.getKey();
                    selectListTree.addChild(createColumnExpr(alias, attr.name));
                }
            }
        } else {
            for (int i = 0; i < selectListTree.getChildCount(); i++) {
                if (selectListTree.getChild(i).getType() == STAR) {
                    Tree startTree = selectListTree.getChild(i);
                    selectListTree.deleteChild(i);

                    String alias = startTree.getChild(0).getText();
                    Tree aliasTree = startTree.getChild(0);
                    String alias1 = aliasTree.getText();
                    Class _class1 = aliasToClassMap.get(alias1);
                    if (_class1 == null) throw new SqlParserException(
                            String.format("Unresolved table alias '%s'.", alias1), aliasTree);

                    Class _class = _class1;

                    for (Attribute attr : _class.getInstanceType().getAttributes()) {
                        selectListTree.addChild(createColumnExpr(alias, attr.name));
                    }
                }
            }
        }
    }

    private CommonTree createColumnExpr(String alias, String attrName) {
        CommonTree columnExprTree = new CommonTree(new CommonToken(COLUMN_EXPR));
        CommonTree columnNameTree = new CommonTree(new CommonToken(COLUMN_NAME));
        columnNameTree.addChild(new CommonTree(new CommonToken(ID, alias)));
        columnNameTree.addChild(new CommonTree(new CommonToken(ID, attrName)));
        columnExprTree.addChild(columnNameTree);
        columnExprTree.addChild(new CommonTree(new CommonToken(ID, attrName)));

        return columnExprTree;
    }

    private void collectClasses(Tree fromTree) {
        int n = fromTree.getChildCount();
        aliasToClassMap = new HashMap<String, Class>(n);

        for (int i = 0; i < n; i++) {
            Tree fromElemTree = fromTree.getChild(i);

            Tree tableNameTree = fromElemTree.getChild(0);
            Tree idTree1 = tableNameTree.getChild(0);
            Class table = tableNameTree.getChild(1) != null
                    ? resolver.resolveClass(idTree1, tableNameTree.getChild(1))
                    : resolver.resolveClass(idTree1);
            String alias = text(fromElemTree.getChild(1));
            if (alias == null) alias = table.getName();

            if (aliasToClassMap.containsKey(alias)) throw new SqlParserException(
                    "Table alias duplication.", fromElemTree.getChild(1));

            aliasToClassMap.put(alias, table);
        }
    }

    private void deriveColumns() {
        for (Map.Entry<String, Class> e : aliasToClassMap.entrySet()) {
            String alias = e.getKey();
            Class _class = e.getValue();
            
            for (Attribute a : _class.getInstanceType().getAttributes()) {
                DerivedColumn dc = new DerivedColumn(alias, (Column) a);
                columnNameToDerivedColumnMap.put(alias + "." + a.name, dc);

                _l: for (Class _c : aliasToClassMap.values()) if (_class != _c) {
                   if (_c.getInstanceType().findAttribute(a) != null) {
                        dc = DerivedColumn.AMBIGUOUS_COLUMN_NAME;
                        break _l;
                    }
                }

                columnNameToDerivedColumnMap.put(a.name, dc);
            }
        }
    }

}

class DerivedColumn {
    public final static DerivedColumn AMBIGUOUS_COLUMN_NAME = new DerivedColumn(null, null);

    public String alias;
    public Column column;
    public String derivedName;
    public int useCount = 0;

    DerivedColumn(String alias, Column column) {
        this.alias = alias;
        this.column = column;
    }

    public String toString() {
        return derivedName;
    }

    @Override
    public int hashCode() {
        return column.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        DerivedColumn dc = (DerivedColumn)obj;
        return column.equals(dc.column);
    }
}
