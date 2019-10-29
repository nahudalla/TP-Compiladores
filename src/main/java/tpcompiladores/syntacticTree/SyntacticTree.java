package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

import java.io.PrintStream;

public abstract class SyntacticTree {
    protected String value;
    protected SyntacticTree leftTree;
    protected SyntacticTree rightTree;

    // constructor con nodo actual, hijos
    protected SyntacticTree (String value, SyntacticTree leftTree, SyntacticTree rightTree) {
        this.value = value;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    //mostrarse por pantalla
    public void print (PrintStream stream) {   }

    //buscar su nodo izquierdo cuyos hijos sean hojas
    protected SyntacticTree leftMostNodeWithLeaves () {
        return null;
    }

    //tipo_resultado
    public abstract Type resultType ();

    //isRefToMethod() : boolean
    public abstract boolean isReferenceToMethod();

    public abstract boolean isReferenceToVariable();

}
