package tpcompiladores.syntacticTree;

import tpcompiladores.symbolsTable.Type;

public class PrintTree extends SyntacticTree {
    public PrintTree(LeafTree stringConstantTree) {
        super(stringConstantTree);
    }
    
    private LeafTree getStringConstantTree () {
        return (LeafTree) this.leftTree;
    }
}
