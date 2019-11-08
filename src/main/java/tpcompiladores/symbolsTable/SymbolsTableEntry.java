package tpcompiladores.symbolsTable;

import java.util.List;

public class SymbolsTableEntry {
    public static final String[] COLUMN_NAMES = {
        "Identificador", "Lexema", "Tipo", "Uso"
    };

    private String identifier;
    private String lexeme;
    private Type type;
    private SymbolsTableEntryUse use;

    public String getIdentifier() {
        return this.identifier;
    }

    public SymbolsTableEntryUse getUse() {
        return this.use;
    }

    public void setUse(SymbolsTableEntryUse use) {
        this.use = use;
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
