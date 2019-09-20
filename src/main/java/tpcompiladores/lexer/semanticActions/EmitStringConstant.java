package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.TokenNumbers;
import tpcompiladores.symbolsTable.SymbolTableEntry;

public class EmitStringConstant implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        String constant = lexerContext.getCharactersRecorder().getRecordedString();
        String key = this.addToSymbolsTable(lexerContext, constant);

        lexerContext.getLexer().setNextToken(
                TokenNumbers.CONST_STRING,
                key
        );
    }

    private String addToSymbolsTable(LexerContext lexerContext, String constant) {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(constant);

        return lexerContext.getSymbolsTable().addStringConstant(symbolTableEntry);
    }
}
