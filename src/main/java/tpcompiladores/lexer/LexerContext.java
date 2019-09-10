package tpcompiladores.lexer;

import sun.text.normalizer.SymbolTable;

import java.util.Map;

public class LexerContext {
    CharactersRecorder charactersRecorder;
    CharactersReader charactersReader;
    Lexer lexer;
    Map<String,Integer> reservedWordsTable;
    Map<String,Integer> specialTokensTable;
    Map<String, SymbolTableEntry>  symbolsTable;
    Logger logger;

    public LexerContext(CharactersRecorder charactersRecorder, Logger logger){
        this.charactersRecorder=charactersRecorder;
        this.logger=logger;
    }

    CharactersRecorder getCharacterRecorder(){
        return this.charactersRecorder;
    }

    CharactersReader getCharactersReader(){
        return this.charactersReader;
    }

    Lexer getLexer(){
        return this.lexer;
    }

    Map<String,Integer> getReservedWordStable(){
        return this.reservedWordsTable;
    }

   Logger getLogger(){
        return this.logger;
   }

   Map<String,Integer> getSpecialTokensTable(){
        return this.specialTokensTable;
   }

   Map<String,SymbolTableEntry> getSymbolsTable(){
        return this.symbolsTable;
   }

}
