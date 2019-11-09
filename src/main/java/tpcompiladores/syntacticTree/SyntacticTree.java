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

    protected SyntacticTree (SyntacticTree leftTree) {
        this.leftTree = leftTree;
    }

    public void print (PrintStream stream) {
        this.print(new TreePrinter(stream));
    }

    protected void printSelf (TreePrinter printer) {
        printer.printClass(this);
    }

    protected void print(TreePrinter printer) {
        this.printSelf(printer);
        this.printChildren(printer);
    }

    protected void printChildren (TreePrinter printer) {
        printer.increaseIndentation();
        if (this.leftTree != null) this.leftTree.print(printer);
        if (this.rightTree != null) this.rightTree.print(printer);
        printer.decreaseIndentation();
    }

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
