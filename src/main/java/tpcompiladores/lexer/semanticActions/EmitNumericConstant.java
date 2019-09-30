package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.lexer.*;
import tpcompiladores.symbolsTable.SymbolTableEntry;

public class EmitNumericConstant implements SemanticAction {
    private static final long MAX_SAFE_LONG = CompilerConstants.MAX_LONG - 1;
    private CompilerContext compilerContext;
    private long constant;

    @Override
    public void run(CompilerContext compilerContext) {
        this.compilerContext = compilerContext;

        String recordedString = compilerContext.getCharactersRecorder().getRecordedString();

        try {
            this.constant = Long.parseLong(recordedString);
        } catch (NumberFormatException e) {
            this.emitOutOfRangeError(recordedString);
            this.constant = MAX_SAFE_LONG;
        }

        if (this.constant <= CompilerConstants.MAX_INT) {
            this.processInt();
        } else if (constant <= CompilerConstants.MAX_LONG) {
            this.processLong();
        } else {
            long originalConstant = this.constant;
            this.constant = MAX_SAFE_LONG;
            this.processLong();
            this.emitOutOfRangeError(originalConstant);
        }
    }

    private void processNumericConstant(int token){
        SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
        symbolTableEntry.setLexeme(String.valueOf(this.constant));
        String key = compilerContext.getSymbolsTable().addNumericConstant(symbolTableEntry);
        compilerContext.getLexer().setNextToken(token, key);
    }

    private void processInt(){
        this.processNumericConstant(TokenNumbers.CONST_INT);
    }

    private void emitOutOfRangeError(long constant) {
        this.emitOutOfRangeError(String.valueOf(constant));
    }

    private void emitOutOfRangeError(String constant){
        this.compilerContext.getLogger().logError(
                "Constante fuera de rango: " + constant + ". Maximo permitido: " + CompilerConstants.MAX_LONG +
                        ". Asumiendo el valor: " + MAX_SAFE_LONG + "."
        );
    }

    private void processLong(){
        this.processNumericConstant(TokenNumbers.CONST_LONG);
    }
}
