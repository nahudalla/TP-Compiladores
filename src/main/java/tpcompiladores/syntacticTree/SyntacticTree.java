package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

import java.io.PrintStream;

public abstract class SyntacticTree {
    protected SyntacticTree leftTree = null;
    protected SyntacticTree rightTree = null;

    protected SyntacticTree() {}

    protected SyntacticTree (SyntacticTree leftTree, SyntacticTree rightTree) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    public void print (PrintStream stream) {   }

    protected SyntacticTree leftMostNodeWithLeaves () {
        return null;
    }

    public boolean isLeaf(){
        return this.leftTree == null && this.rightTree == null;
    }

    public Type resultType () {
        return null;
    }

    public boolean isReferenceToMethod(){
        return false;
    }

    public boolean isReferenceToVariable(){
        return false;
    }
}
