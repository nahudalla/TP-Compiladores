package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.TokenNumbers;
import tpcompiladores.symbolsTable.SymbolTableEntry;

public class EmitStringConstant implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        String constant = compilerContext.getCharactersRecorder().getRecordedString();
        String key = this.addToSymbolsTable(compilerContext, constant);

        compilerContext.getLexer().setNextToken(
                TokenNumbers.CONST_STRING,
                key
        );
    }

    private String addToSymbolsTable(CompilerContext compilerContext, String constant) {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(constant);

        return compilerContext.getSymbolsTable().addStringConstant(symbolTableEntry);
    }
}
