package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.ReservedWords;
import tpcompiladores.symbolsTable.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class EmitIdentifierOrReservedWord implements SemanticAction {
    @Override
    public void run(CompilerContext compilerContext) {
        String identifier = this.truncateIdentifierIfNecessary(
                compilerContext,
                compilerContext.getCharactersRecorder().getRecordedString()
        );

        if (this.emitReservedWord(compilerContext, identifier)) return;

        String key = this.addIdentifierToSymbolsTable(compilerContext, identifier);
        compilerContext.getLexer().setNextToken(TokenNumbers.ID, key);
    }

    private boolean emitReservedWord(CompilerContext compilerContext, String lexeme) {
        Integer reservedWordToken = ReservedWords.getInstance().getTokenNumber(lexeme);

        if (reservedWordToken != null) {
            compilerContext.getLexer().setNextToken(reservedWordToken);
            return true;
        }

        return false;
    }

    private String truncateIdentifierIfNecessary (CompilerContext compilerContext, String identifier) {
        if (identifier.length() > CompilerConstants.MAX_ID_LENGTH){
            String newIdentifier = identifier.substring(0, CompilerConstants.MAX_ID_LENGTH);

            compilerContext.getLogger().logWarning(
                    "Identificador truncado ("+newIdentifier+"): "+identifier
            );

            return newIdentifier;
        }

        return identifier;
    }

    private String addIdentifierToSymbolsTable (CompilerContext compilerContext, String identifier) {
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(identifier);

        return compilerContext.getSymbolsTable().addIdentifier(symbolTableEntry);
    }
}
