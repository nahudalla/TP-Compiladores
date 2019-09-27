package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerConstants;
import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.ReservedWords;
import tpcompiladores.symbolsTable.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class EmitIdentifierOrReservedWord implements SemanticAction {
    @Override
    public void run(LexerContext lexerContext) {
        String identifier = this.truncateIdentifierIfNecessary(
                lexerContext,
                lexerContext.getCharactersRecorder().getRecordedString()
        );

        if (this.emitReservedWord(lexerContext, identifier)) return;

        String key = this.addIdentifierToSymbolsTable(lexerContext, identifier);
        lexerContext.getLexer().setNextToken(TokenNumbers.ID, key);
    }

    private boolean emitReservedWord(LexerContext lexerContext, String lexeme) {
        Integer reservedWordToken = ReservedWords.getInstance().getTokenNumber(lexeme);

        if (reservedWordToken != null) {
            lexerContext.getLexer().setNextToken(reservedWordToken);
            return true;
        }

        return false;
    }

    private String truncateIdentifierIfNecessary (LexerContext lexerContext, String identifier) {
        if (identifier.length() > CompilerConstants.MAX_ID_LENGTH){
            String newIdentifier = identifier.substring(0, CompilerConstants.MAX_ID_LENGTH);

            lexerContext.getLogger().logWarning(
                    "Identificador truncado ("+newIdentifier+"): "+identifier
            );

            return newIdentifier;
        }

        return identifier;
    }

    private String addIdentifierToSymbolsTable (LexerContext lexerContext, String identifier) {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(identifier);

        return lexerContext.getSymbolsTable().addIdentifier(symbolTableEntry);
    }
}
