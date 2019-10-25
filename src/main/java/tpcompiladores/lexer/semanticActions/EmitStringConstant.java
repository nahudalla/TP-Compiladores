package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;
import tpcompiladores.parser.yacc_generated.Parser;
import tpcompiladores.symbolsTable.SymbolsTableEntry;

public class EmitStringConstant implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        String constant = compilerContext.getCharactersRecorder().getRecordedString();
        String key = this.addToSymbolsTable(compilerContext, constant);

        compilerContext.getLexer().setNextToken(
                Parser.CONST_STRING,
                key
        );
    }

    private String addToSymbolsTable(CompilerContext compilerContext, String constant) {
        SymbolsTableEntry symbolsTableEntry = new SymbolsTableEntry();
        symbolsTableEntry.setLexeme(constant);

        return compilerContext.getSymbolsTable().addStringConstant(symbolsTableEntry);
    }

    @Override
    public String toString() {
        return "  -- EmitStringConstant";
    }
}
