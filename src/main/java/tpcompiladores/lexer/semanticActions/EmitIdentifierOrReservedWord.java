package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;
import tpcompiladores.lexer.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class EmitIdentifierOrReservedWord implements SemanticAction {
    private String word;
    private LexerContext lexerContext;

    @Override
    public void run(LexerContext lexerContext) {
        this.word = lexerContext.getCharactersRecorder().getRecordedString();
        this.lexerContext = lexerContext;

        Integer reservedWordToken = lexerContext.getReservedWordsTable().get(this.word);

        if (reservedWordToken != null) {
            lexerContext.getLexer().setNextToken(reservedWordToken);
        } else {
            this.truncateWordIfNecessary();

            if (!this.isIdentifierInSymbolsTable()) this.addIdentifierToSymbolsTable();

            this.emitIdentifier();
        }
    }

    private void truncateWordIfNecessary () {
        if (this.word.length() > 25){
            String newWord = this.word.subSequence(0,24).toString();
            this.lexerContext.getLogger().logWarning(
                    "Identificador truncado ("+newWord+"): "+this.word
            );
            this.word = newWord;
        }
    }

    private boolean isIdentifierInSymbolsTable () {
        return this.lexerContext.getSymbolsTable().containsKey(this.word);
    }

    private void addIdentifierToSymbolsTable () {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(this.word);

        this.lexerContext.getSymbolsTable().put(
                this.word,
                symbolTableEntry
        );
    }

    private void emitIdentifier () {
        this.lexerContext.getLexer().setNextToken(TokenNumbers.ID, this.word);
    }
}
