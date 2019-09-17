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

        if(!this.isConstantInConstantsTable()) {
            this.addToConstantsTable();
        }

        this.emitConstantString();
    }

    private boolean isConstantInConstantsTable() {
        return this.lexerContext.getConstantsTable().containsKey(this.stringConstant);
    }

    private void addToConstantsTable() {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(this.stringConstant);

        this.lexerContext.getConstantsTable().put(
                this.stringConstant,
                symbolTableEntry
        );
    }

    private void emitConstantString() {
        this.lexerContext.getLexer().setNextToken(
                TokenNumbers.CONST_STRING,
                this.stringConstant
        );
    }
}
