package tpcompiladores.lexer;

import java.util.HashMap;
import java.util.Map;

public class ReservedWords {
    private static ReservedWords instance = new ReservedWords();
    public static ReservedWords getInstance(){
        return instance;
    }

    private Map<String, Integer> reservedWords = new HashMap<>();

    private ReservedWords(){
        this.reservedWords.put("if", TokenNumbers.IF);
        this.reservedWords.put("else", TokenNumbers.ELSE);
        this.reservedWords.put("end_if", TokenNumbers.END_IF);
        this.reservedWords.put("print", TokenNumbers.PRINT);
        this.reservedWords.put("int", TokenNumbers.INT);
        this.reservedWords.put("begin", TokenNumbers.BEGIN);
        this.reservedWords.put("end", TokenNumbers.END);
        this.reservedWords.put("long", TokenNumbers.LONG);
        this.reservedWords.put("do", TokenNumbers.DO);
        this.reservedWords.put("while", TokenNumbers.WHILE);
        this.reservedWords.put("class", TokenNumbers.CLASS);
        this.reservedWords.put("extends", TokenNumbers.EXTENDS);
        this.reservedWords.put("until", TokenNumbers.UNTIL);
        this.reservedWords.put("void", TokenNumbers.VOID);
    }

    public Integer getTokenNumber(String lexeme){
        return this.reservedWords.get(lexeme);
    }
}
