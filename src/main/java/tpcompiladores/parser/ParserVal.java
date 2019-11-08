package tpcompiladores.parser;

import java.util.List;

import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class ParserVal {
  public int ival;
  public boolean bval = false;
  public String sval = "";
  public SyntacticTree tree;
  public SymbolsTableEntry tableRef;
  public Type type;
  public List<SymbolsTableEntry> tableRefs;

  public ParserVal() {}

  public ParserVal(Type type) {
    this.type = type;
  }

  public ParserVal(int ival) {
    this.ival = ival;
  }

  public ParserVal(boolean bval) {
    this.bval = bval;
  }

  public ParserVal(String sval) {
    this.sval = sval;
  }

  public ParserVal(SyntacticTree tree) {
    this.tree = tree;
  }

  public ParserVal(SymbolsTableEntry tableRef) {
    this.tableRef = tableRef;
  }
}
