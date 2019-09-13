package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;
import tpcompiladores.lexer.SemanticAction;
import tpcompiladores.lexer.SymbolTableEntry;

public class EmitIdentifierOrReservedWord implements SemanticAction {
    private String word;

    public EmitIdentifierOrReservedWord(String word){
        this.word = word;
    }

    @Override
    public void run(LexerContext lexerContext) {
        this.word = lexerContext.getCharactersRecorder().getRecordedString();

       if (lexerContext.getReservedWordsTable().containsKey(this.word)) {
           Integer value = lexerContext.getReservedWordsTable().get(this.word);
           lexerContext.getLexer().setNextToken(value,this.word);
       }
        if  (!lexerContext.getSymbolsTable().containsKey(this.word)){
            processIdentifie(lexerContext);

        }
    }

    private void processIdentifie(LexerContext lexerContext){
          if (!(this.word.length() <= 25)){
             this.word = word.subSequence(0,24).toString();
          }
        lexerContext.getSymbolsTable().put("identifier",new SymbolTableEntry(this.word));

    }
}
