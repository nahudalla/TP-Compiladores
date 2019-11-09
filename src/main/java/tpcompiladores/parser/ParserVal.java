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

  public ParserVal shallowClone (ParserVal val) {
    ParserVal v = new ParserVal();

    v.bval = val.bval;
    v.ival = val.ival;
    v.sval = val.sval;
    v.tableRef = val.tableRef;
    v.tableRefs = val.tableRefs;
    v.tree = val.tree;
    v.type = val.type;

    return v;
  }

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
