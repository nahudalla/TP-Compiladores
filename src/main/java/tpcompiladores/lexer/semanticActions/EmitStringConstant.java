package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;
import tpcompiladores.lexer.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class EmitStringConstant implements SemanticAction {
    private String stringConstant;
    private LexerContext lexerContext;

    @Override
    public void run(LexerContext lexerContext) {
        this.lexerContext = lexerContext;
        this.stringConstant = this.lexerContext.getCharactersRecorder().getRecordedString();

        this.addToConstantsTable(this.stringConstant);

        this.emitConstantString();
    }

    private void addToConstantsTable(String stringConstant) {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(this.stringConstant);

        this.lexerContext.getConstantsTable().put(
                this.stringConstant,
                symbolTableEntry
        );
    }

    private void emitConstantString() {
        this.lexerContext.getLexer().setNextToken(TokenNumbers.CONST_STRING,this.stringConstant);
    }
}
