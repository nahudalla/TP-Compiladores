package tpcompiladores.syntacticTree;

public class PrintTree extends SyntacticTree {
    public PrintTree(LeafTree stringConstantTree) {
        super(stringConstantTree);
    }

    private LeafTree getStringConstantTree () {
        return (LeafTree) this.leftTree;
    }
}
