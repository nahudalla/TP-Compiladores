package tpcompiladores.symbolsTable;

import java.io.PrintStream;
import java.util.List;

import tpcompiladores.Logger;
import tpcompiladores.assembler_generation.ASMDumpable;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.syntacticTree.SyntacticTree;

public class SymbolsTableEntry implements ASMDumpable {
    private String identifier;
    private String lexeme;
    private Type type;
    private SymbolsTableEntryUse use;
    private Klass klass;
    private SyntacticTree tree;

    public void print(int indentation, PrintStream stream) {
        String spaces = "";
        for (int i = 0; i < indentation; i++) {
            spaces += " ";
        }

        stream.print(spaces);
        stream.println("Lexeme: " + lexeme);

        stream.print(spaces);
        stream.println("Type: " + type);

        stream.print(spaces);
        stream.println("Uso: " + use);

        stream.print(spaces);
        stream.print("Clase: ");

        if (SymbolsTableEntryUse.CLASS.equals(use)) {
            stream.println();
            klass.print(indentation + 2, stream);
        } else {
            stream.println(klass);
        }

        if (tree != null) {
            stream.print(spaces);
            stream.println("Ver arbol sintactico mas abajo");
        }
	}

    @Override
    public void generateData(PrintStream printStream) {
        switch (this.use) {
            case CONSTANT: this.dumpConstantToASM(printStream); break;
            case VARIABLE: this.dumpVariableToASM(printStream); break;
            case OBJECT: this.dumpObjectToASM(printStream); break;
            default: break;
        }
    }

    public String generateAttributeIdentifier (SymbolsTableEntry attributeReference) {
        return this.identifier + "@" + attributeReference.identifier;
    }

    private void dumpObjectToASM(PrintStream printStream) {
        for (SymbolsTableEntry attribute : this.klass.getAttributes()) {
            attribute.dumpVariableToASM(
                this.generateAttributeIdentifier(attribute),
                printStream
            );
        }
    }

    private void dumpVariableToASM(PrintStream printStream) {
        this.dumpVariableToASM(this.identifier, printStream);
    }

    private void dumpVariableToASM(String identifier, PrintStream printStream) {
        printStream.print(identifier + " ");

        if (Type.INT.equals(this.type)) printStream.print("DW");
        else if (Type.LONG.equals(this.type)) printStream.print("DD");
        else throw new Error("Tipo invalido en variable de la tabla de simbolos!!");

        printStream.println(" 0");
    }

    private void dumpConstantToASM(PrintStream printStream) {
        if (!Type.STRING.equals(this.type)) return;

        printStream.print(this.identifier);
        printStream.print(" DB \"");
        printStream.print(this.lexeme.replaceAll("\"", "\"\""));
        printStream.println("\", 0");
    }

    @Override
    public void generateCode(PrintStream printStream, Registers registers) {
        if (!SymbolsTableEntryUse.METHOD.equals(this.use)) return;
        if (this.tree == null) return;

        printStream.println(this.identifier + ":");
        this.tree.generateCode(printStream, registers);
        printStream.println();
        printStream.println("RET");
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public SyntacticTree getTree() {
        return tree;
    }

    public void setTree(SyntacticTree tree) {
        this.tree = tree;
    }

    public Klass getKlass() {
        return this.klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public static void setKlass(List<SymbolsTableEntry> entries, Klass klass) {
        for (SymbolsTableEntry entry : entries) {
            entry.setKlass(klass);
        }
    }

    public boolean isObject() {
        return SymbolsTableEntryUse.OBJECT.equals(this.getUse());
    }

    public SymbolsTableEntryUse getUse() {
        return this.use;
    }

    public void changeUse(SymbolsTableEntryUse use) {
        this.use = use;
    }

    public void setUse(SymbolsTableEntryUse use) {
        if (!SymbolsTableEntryUse.CONSTANT.equals(use) && this.use != null) {
            Logger.getInstance().logSemanticError("El identificador '" + this.getLexeme() + "' ya ha sido declarado.");
        } else
            this.changeUse(use);
    }

    public static void setUse(List<SymbolsTableEntry> entries, SymbolsTableEntryUse use) {
        for (SymbolsTableEntry entry : entries) {
            entry.setUse(use);
        }
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static void setType(List<SymbolsTableEntry> entries, Type type) {
        for (SymbolsTableEntry entry : entries) {
            entry.setType(type);
        }
    }
}
