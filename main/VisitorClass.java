package com.taras.main;

import java.util.Collection;

public class VisitorClass extends GrammarBaseVisitor<Double>{
    @Override
    public Double visitMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));

        if (ctx.operatorToken.getType()==GrammarLexer.MULTIPLY)
            return left*right;
        else return left/right;
    }
    @Override
    public Double visitExponentialExpr(GrammarParser.ExponentialExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));

        return Math.pow(left, right);
    }
    @Override
    public Double visitAdditiveExpr(GrammarParser.AdditiveExprContext ctx) {
        double left = visit(ctx.expression(0));
        double right = visit(ctx.expression(1));

        if (ctx.operatorToken.getType()==GrammarLexer.ADD)
            return left+right;
        else return left-right;
    }
    @Override
    public Double visitNumberExpr(GrammarParser.NumberExprContext ctx) {
        return Double.parseDouble(ctx.getText());
    }
    @Override
    public Double visitParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }
    @Override
    public Double visitUnarExpr(GrammarParser.UnarExprContext ctx){
        double ex=visit(ctx.expression());
        if (ctx.operatorToken.getType()==GrammarLexer.ADD)
            return ex;
        else
            return ex*(-1);
    }
    @Override
    public Double visitDivisiveExpr(GrammarParser.DivisiveExprContext ctx){
        double ex1=visit(ctx.expression(0));
        double ex2=visit(ctx.expression(1));
        if (ctx.operatorToken.getType()==GrammarLexer.DIV){
            return (ex1-(ex1%ex2))/ex2;
        }
        else if(ctx.operatorToken.getType()==GrammarLexer.MOD)
            return ex1%ex2;
        else return 0.0;
    }
    @Override
    public Double visitNmaxExpr(GrammarParser.NmaxExprContext ctx){
        var expressions = ctx.expression();
        double max_min=visit(ctx.expression(0));
        if (ctx.operatorToken.getType()==GrammarLexer.NMAX){

            for (var expr: expressions) {
                if(visit(expr)>=max_min)
                    max_min=visit(expr);
            }
        }
        else if (ctx.operatorToken.getType()==GrammarLexer.NMIN){
            double min=visit(ctx.expression(0));
            for(var expr: expressions){
                if (visit(expr)<=max_min)
                    max_min=visit(expr);
            }
        }
        return max_min;
    }
}