package com.taras.app;

import com.taras.main.GrammarLexer;
import com.taras.main.GrammarParser;
import com.taras.main.ThrowingErrorListener;
import com.taras.main.VisitorClass;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.table.DefaultTableModel;

public class Evaluate{
    public static String transform(String cell, DefaultTableModel dm){
        if(cell==null)
            return "0";
        for (int k = 0; k < cell.length(); k++) {
            if (cell.charAt(k) == '#') {
                int x = cell.charAt(k + 1) - 65;
                int y = cell.charAt(k + 2) - 49;
                String val = String.valueOf(evaluate('(' + String.valueOf(dm.getValueAt(y, x)) + ')'));
                cell = cell.substring(0, k) + val + cell.substring(k + 3, cell.length());
                k = 0;
            }
        }
        return cell;
    }
    public static double evaluate(String expression){
        GrammarLexer lexer = new GrammarLexer(new ANTLRInputStream(expression));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowingErrorListener());
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokenStream);
        ParseTree tree = parser.expression();
        VisitorClass visitor = new VisitorClass();
        return visitor.visit(tree);
    }
}