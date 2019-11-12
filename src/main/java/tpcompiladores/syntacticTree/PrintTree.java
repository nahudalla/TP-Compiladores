package tpcompiladores.syntacticTree;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Registers;

public class PrintTree extends SyntacticTree {
    public PrintTree(LeafTree stringConstantTree) {
        super(stringConstantTree);
    }

    private LeafTree getStringConstantTree () {
        return (LeafTree) this.leftTree;
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        String strIdentifier = this.getStringConstantTree().getSymbolsTableEntry().getIdentifier();

        printStream.println("invoke MessageBox, NULL, addr " + strIdentifier + ", addr " + strIdentifier + ", MB_OK");
    }
}
