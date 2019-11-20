package tpcompiladores.symbolsTable;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import tpcompiladores.Logger;
import tpcompiladores.assembler_generation.ASMGenerator;

public class SymbolsTable {
    private final static String NUMERIC_CONSTANT_PREFIX = "NUM_CONST_";
    private final static String STRING_CONSTANT_PREFIX = "STR_CONST_";
    private final static String ID_PREFIX = "ID_";

    private int stringConstantIdentifierNumber = 0;
    private Map<String, Integer> stringConsantIdentifiers = new HashMap<>();
    private Map<String, SymbolsTableEntry> symbolsTable = new HashMap<>();
    private ASMGenerator asmGenerator;

    public SymbolsTable(ASMGenerator asmGenerator) {
        this.asmGenerator = asmGenerator;
    }

    public SymbolsTableEntry getEntry(String key){
        return this.symbolsTable.get(key);
    }

    public String addNumericConstant(SymbolsTableEntry symbolsTableEntry){
        return this.insertIfNotPresent(NUMERIC_CONSTANT_PREFIX, symbolsTableEntry);
    }

    public String addStringConstant(SymbolsTableEntry symbolsTableEntry) {
        String identifierKey = symbolsTableEntry.getLexeme();
        Integer identifierNumber = this.stringConsantIdentifiers.get(identifierKey);

        if (identifierNumber == null) {
            identifierNumber = this.stringConstantIdentifierNumber++;
            this.stringConsantIdentifiers.put(identifierKey, identifierNumber);
        }

        return this.insertIfNotPresentWithKey(
            STRING_CONSTANT_PREFIX + identifierNumber,
            symbolsTableEntry
        );
    }

    public String addIdentifier(SymbolsTableEntry symbolsTableEntry) {
        return this.insertIfNotPresent(ID_PREFIX, symbolsTableEntry);
    }

    private String insertIfNotPresent(String prefix, SymbolsTableEntry symbolsTableEntry){
        return this.insertIfNotPresentWithKey(
            prefix + symbolsTableEntry.getLexeme(),
            symbolsTableEntry
        );
    }

    private String insertIfNotPresentWithKey(String key, SymbolsTableEntry symbolsTableEntry){
        boolean alreadyInTable = this.symbolsTable.containsKey(key);

        if (!alreadyInTable) {
            this.asmGenerator.addDumpable(symbolsTableEntry);
            symbolsTableEntry.setIdentifier(key);
            this.symbolsTable.put(key, symbolsTableEntry);
        }

        return key;
    }

    public void print(PrintStream stream) {
        if (this.symbolsTable.isEmpty()) {
            stream.println("TABLA DE SIMBOLOS VACIA");
            return;
        }

        for (SymbolsTableEntry entry : this.symbolsTable.values()) {
            stream.println(entry.getIdentifier());
            entry.print(2, stream);
        }
    }

	public void printMethodTrees() {
        for (SymbolsTableEntry entry : this.symbolsTable.values()) {
            if (SymbolsTableEntryUse.METHOD.equals(entry.getUse())) {
                Logger.getInstance().logSyntacticTree(entry.getLexeme(), entry.getTree());
            }
        }
	}
}
