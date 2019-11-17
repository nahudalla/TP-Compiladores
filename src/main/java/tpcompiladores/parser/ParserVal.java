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
  public SymbolsTableEntry objTableRef;
  public Type type;
  public List<SymbolsTableEntry> tableRefs;

  public ParserVal clone () {
    ParserVal v = new ParserVal();

    v.bval = this.bval;
    v.ival = this.ival;
    v.sval = this.sval;
    v.objTableRef = this.objTableRef;
    v.tableRef = this.tableRef;
    v.tableRefs = this.tableRefs;
    v.tree = this.tree;
    v.type = this.type;

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

  public ParserVal(SymbolsTableEntry tableRef, boolean isObj) {
    this.objTableRef = tableRef;
  }
}
