package tpcompiladores.assembler_generation.errors;

import java.io.PrintStream;

import tpcompiladores.CompilerContext;
import tpcompiladores.assembler_generation.ASMDumpable;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.SymbolsTableEntryUse;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.LeafTree;
import tpcompiladores.syntacticTree.PrintTree;
import tpcompiladores.syntacticTree.SyntacticTree;

public abstract class BaseError implements ASMDumpable {
    private SyntacticTree tree;

    public BaseError (String msg) {
        SymbolsTableEntry message = new SymbolsTableEntry();
        message.setLexeme(msg);
        message.setUse(SymbolsTableEntryUse.CONSTANT);
        message.setType(Type.STRING);

        CompilerContext.getInstance().getSymbolsTable().addStringConstant(message);

        this.tree = new PrintTree(new LeafTree(message));
    }

    @Override
    public void generateData(PrintStream printStream) { }

    protected void generateCode(String label, PrintStream printStream, Registers registers) {
        printStream.println(label + ":");
        tree.generateCode(printStream, registers);
        printStream.println("invoke ExitProcess, 1");
    }
}
