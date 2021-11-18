package com.taras.main;

import com.taras.Grammar.GrammarLexer;
import com.taras.Grammar.GrammarParser;
import com.taras.Grammar.ThrowingErrorListener;
import com.taras.Grammar.VisitorClass;
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
                int p=k;
                if(!(cell.length()==k+3)&&(cell.charAt(k+3)-49>=0 && cell.charAt(k+3)-49<=9)){
                    y=(y+1)*10+(cell.charAt(k+3)-49);
                    p++;
                }
                String val = String.valueOf(evaluate('(' + String.valueOf(dm.getValueAt(y, x)) + ')'));
                cell = cell.substring(0, k) + val + cell.substring(p + 3, cell.length());
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