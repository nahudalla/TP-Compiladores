package tpcompiladores.symbolsTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolsTable {
    private final static String NUMERIC_CONSTANT_PREFIX = "NUM_CONST_";
    private final static String STRING_CONSTANT_PREFIX = "STR_CONST_";
    private final static String ID_PREFIX = "ID_";

    private Map<String, SymbolTableEntry> symbolsTable = new HashMap<>();

    public SymbolTableEntry getEntry(String key){
        return this.symbolsTable.get(key);
    }

    public String addNumericConstant(SymbolTableEntry symbolTableEntry){
        return this.insertIfNotPresent(NUMERIC_CONSTANT_PREFIX, symbolTableEntry);
    }

    public String addStringConstant(SymbolTableEntry symbolTableEntry) {
        return this.insertIfNotPresent(STRING_CONSTANT_PREFIX, symbolTableEntry);
    }

    public String addIdentifier(SymbolTableEntry symbolTableEntry) {
        return this.insertIfNotPresent(ID_PREFIX, symbolTableEntry);
    }

    private String insertIfNotPresent(String prefix, SymbolTableEntry symbolTableEntry){
        String key = prefix + symbolTableEntry.getLexeme();
        boolean alreadyInTable = this.symbolsTable.containsKey(key);

        if (!alreadyInTable) {
            this.symbolsTable.put(key, symbolTableEntry);
        }

        return key;
    }
}
