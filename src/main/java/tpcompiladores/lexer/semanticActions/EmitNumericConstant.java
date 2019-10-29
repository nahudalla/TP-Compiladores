package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.parser.yacc_generated.Parser;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;

public class EmitNumericConstant implements SemanticAction {
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
            this.constant = CompilerConstants.MAX_POSITIVE_LONG;
        }

        if (this.constant <= CompilerConstants.MAX_ABS_INT) {
            this.processInt();
        } else if (constant <= CompilerConstants.MAX_ABS_LONG) {
            this.processLong();
        } else {
            long originalConstant = this.constant;
            this.constant = CompilerConstants.MAX_POSITIVE_LONG;
            this.processLong();
            this.emitOutOfRangeError(originalConstant);
        }
    }

    private void processNumericConstant(Type type){
        SymbolsTableEntry symbolsTableEntry = new SymbolsTableEntry();
        symbolsTableEntry.setLexeme(String.valueOf(this.constant));
        symbolsTableEntry.setType(type);
        String key = compilerContext.getSymbolsTable().addNumericConstant(symbolsTableEntry);
        compilerContext.getLexer().setNextToken(Parser.NUMERIC_CONST, key);
    }

    private void processInt(){
        this.processNumericConstant(Type.INT);
    }

    private void emitOutOfRangeError(long constant) {
        this.emitOutOfRangeError(String.valueOf(constant));
    }

    private void emitOutOfRangeError(String constant){
        this.compilerContext.getLogger().logLexerError(
                "Constante fuera de rango: " + constant + ". Maximo permitido: " + CompilerConstants.MAX_ABS_LONG +
                        ". Asumiendo el valor: " + CompilerConstants.MAX_POSITIVE_LONG + "."
        );
    }

    private void processLong(){
        this.processNumericConstant(Type.LONG);
    }

    @Override
    public String toString() {
        return "  -- EmitNumericConstant";
    }
}
