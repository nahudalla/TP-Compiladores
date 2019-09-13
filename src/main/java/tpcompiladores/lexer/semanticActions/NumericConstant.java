package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;
import tpcompiladores.lexer.SymbolTableEntry;
import tpcompiladores.lexer.TokenNumbers;

public class NumericConstant implements SemanticAction {

    @Override
    public void run(LexerContext lexerContext) {
       Integer constant = Integer.getInteger(lexerContext.getCharactersRecorder().getRecordedString());

       if ((constant > Math.pow(2,31) * -1) && (constant < Math.pow(2,31) -1)) {
           String constant_string = lexerContext.getCharactersRecorder().getRecordedString();
           lexerContext.getLexer().setNextToken(TokenNumbers.CONST_LONG,constant_string);
           SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
           symbolTableEntry.setLexeme(constant_string);
           lexerContext.getConstantsTable().put(constant_string,symbolTableEntry);
       }
       else
         this.processConstantInt(lexerContext,constant);
    }

    private void processConstantInt(LexerContext lexerContext, Integer constant){
        if ((constant > Math.pow(2,15) * -1) && ( constant < Math.pow(2,15) -1 )) {
            String constant_string = lexerContext.getCharactersRecorder().getRecordedString();
            lexerContext.getLexer().setNextToken(TokenNumbers.CONST_INT,constant_string);
            SymbolTableEntry symbolTableEntry = new SymbolTableEntry();
            symbolTableEntry.setLexeme(constant_string);
            lexerContext.getConstantsTable().put(constant_string,symbolTableEntry);
        }
        else
            lexerContext.getLogger().logError("Constante fuera de rango");
    }
}
