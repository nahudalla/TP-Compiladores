package tpcompiladores.symbolsTable;

import java.util.List;

import tpcompiladores.Logger;
import tpcompiladores.syntacticTree.SyntacticTree;

public class SymbolsTableEntry {
    public static final String[] COLUMN_NAMES = { "Identificador", "Lexema", "Tipo", "Uso" };

    private String identifier;
    private String lexeme;
    private Type type;
    private SymbolsTableEntryUse use;
    private Klass klass;
    private SyntacticTree tree;

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

    public boolean isObject () {
        return SymbolsTableEntryUse.OBJECT.equals(this.getUse());
    }

    public SymbolsTableEntryUse getUse() {
        return this.use;
    }

    public void changeUse(SymbolsTableEntryUse use) {
        this.use = use;
    }

    public void setUse(SymbolsTableEntryUse use) {
        if (this.use != null) {
            Logger.getInstance().logSemanticError(
                "El identificador '" + this.getLexeme() + "' ya ha sido declarado."
            );
        } else this.changeUse(use);
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

    public String[] toTableRow () {
        return new String[] {
                this.preprocessFieldValue(this.identifier),
                this.preprocessFieldValue(this.lexeme),
                this.preprocessFieldValue(this.type),
                this.preprocessFieldValue(this.use)
        };
    }

    private String preprocessFieldValue(SymbolsTableEntryUse value) {
        if (value == null) return "";

        return this.preprocessFieldValue(value.name());
    }

    private String preprocessFieldValue(Type value) {
        if (value == null) return "";

        return this.preprocessFieldValue(value.getName());
    }

    private String preprocessFieldValue (String value) {
        if (value == null) return "";

        return value;
    }
}
