package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

import java.io.PrintStream;

public abstract class SyntacticTree {
    protected SyntacticTree leftTree;
    protected SyntacticTree rightTree;

    protected SyntacticTree (SyntacticTree leftTree, SyntacticTree rightTree) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    public void print (PrintStream stream) {   }

    protected SyntacticTree leftMostNodeWithLeaves () {
        return null;
    }

    public boolean isLeaf(){
        return false;
    }

    public abstract Type resultType ();

    public boolean isReferenceToMethod(){
        return false;
    }

    public boolean isReferenceToVariable(){
        return false;
    }
}
