package tpcompiladores.parser;

import tpcompiladores.syntacticTree.SyntacticTree;

public class ParsingResult {
  private SyntacticTree syntacticTree;
  private int code;

  public ParsingResult (int code, SyntacticTree syntacticTree) {
    this.code = code;
    this.syntacticTree = syntacticTree;
  }

  public int getCode() {
    return this.code;
  }

  public SyntacticTree getSyntacticTree() {
    return this.syntacticTree;
  }
}
