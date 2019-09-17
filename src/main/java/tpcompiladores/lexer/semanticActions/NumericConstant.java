package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;
import tpcompiladores.lexer.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class NumericConstant implements SemanticAction {

    @Override
    public void run(LexerContext lexerContext) {
        String recordedString = lexerContext.getCharactersRecorder().getRecordedString();
       Long constant = Long.getLong(recordedString);

       if ((constant > 0) && ( constant < Math.pow(2,15) -1 )) {
               String constant_string = lexerContext.getCharactersRecorder().getRecordedString();
               SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
               symbolTableEntry.setLexeme(constant_string);
               lexerContext.getConstantsTable().put(constant_string,symbolTableEntry);
               lexerContext.getLexer().setNextToken(TokenNumbers.MAX_INT,constant_string);
           }
       else {this.processLongInt(lexerContext,constant);}
    }

    private void processLongInt(LexerContext lexerContext, Long constant){
        if  (constant < Math.pow(2,31) -1) {
            String constant_string = lexerContext.getCharactersRecorder().getRecordedString();
            SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
            symbolTableEntry.setLexeme(constant_string);
            lexerContext.getConstantsTable().put(constant_string, symbolTableEntry);
            lexerContext.getLexer().setNextToken(TokenNumbers.MAX_LONG, constant_string);
        }
        else {lexerContext.getLogger().logError("Constante fuera de rango"); }
    }
}
