package tpcompiladores.symbolsTable;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Table;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class SymbolsTable {
    private final static int TABLE_COLUMN_WIDTH = 50;
    private final static String NUMERIC_CONSTANT_PREFIX = "NUM_CONST_";
    private final static String STRING_CONSTANT_PREFIX = "STR_CONST_";
    private final static String ID_PREFIX = "ID_";

    private Map<String, SymbolsTableEntry> symbolsTable = new HashMap<>();

    public SymbolsTableEntry getEntry(String key){
        return this.symbolsTable.get(key);
    }

    public String addNumericConstant(SymbolsTableEntry symbolsTableEntry){
        return this.insertIfNotPresent(NUMERIC_CONSTANT_PREFIX, symbolsTableEntry);
    }

    public String addStringConstant(SymbolsTableEntry symbolsTableEntry) {
        return this.insertIfNotPresent(STRING_CONSTANT_PREFIX, symbolsTableEntry);
    }

    public String addIdentifier(SymbolsTableEntry symbolsTableEntry) {
        return this.insertIfNotPresent(ID_PREFIX, symbolsTableEntry);
    }

    private String insertIfNotPresent(String prefix, SymbolsTableEntry symbolsTableEntry){
        String key = prefix + symbolsTableEntry.getLexeme();
        boolean alreadyInTable = this.symbolsTable.containsKey(key);

        if (!alreadyInTable) {
            symbolsTableEntry.setIdentifier(key);
            this.symbolsTable.put(key, symbolsTableEntry);
        }

        return key;
    }

    public void print(PrintStream stream) {
        String[][] tableData = this.buildTableData();

        if (tableData == null) {
            stream.println("TABLA DE SIMBOLOS VACIA");
            return;
        }

        ColumnFormatter<String> centeredFormatter = ColumnFormatter.text(Alignment.CENTER, TABLE_COLUMN_WIDTH);

        String[] header = new String[SymbolsTableEntry.COLUMN_NAMES.length];
        for (int i = 0; i < header.length; i++) {
            header[i] = centeredFormatter.format(SymbolsTableEntry.COLUMN_NAMES[i]);
        }

        ColumnFormatter<String> leftFormatter = ColumnFormatter.text(Alignment.LEFT, TABLE_COLUMN_WIDTH);
        Table table = Table.of(
                header,
                tableData,
                leftFormatter
        );
        stream.println(table);
    }

    private String[][] buildTableData () {
        if (this.symbolsTable.isEmpty()) return null;

        String[][] tableData = new String[this.symbolsTable.size()][];

        int i = 0;
        for (SymbolsTableEntry entry : this.symbolsTable.values()) {
            tableData[i++] = entry.toTableRow();
        }

        return tableData;
    }
}
