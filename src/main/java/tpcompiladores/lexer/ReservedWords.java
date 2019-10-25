package tpcompiladores.lexer;

import tpcompiladores.parser.yacc_generated.Parser;

import java.util.HashMap;
import java.util.Map;

public class ReservedWords {
    private static ReservedWords instance = new ReservedWords();
    public static ReservedWords getInstance(){
        return instance;
    }

    private Map<String, Short> reservedWords = new HashMap<>();

    private ReservedWords(){
        this.reservedWords.put("if", Parser.IF);
        this.reservedWords.put("else", Parser.ELSE);
        this.reservedWords.put("end_if", Parser.END_IF);
        this.reservedWords.put("print", Parser.PRINT);
        this.reservedWords.put("int", Parser.INT);
        this.reservedWords.put("begin", Parser.BEGIN);
        this.reservedWords.put("end", Parser.END);
        this.reservedWords.put("long", Parser.LONG);
        this.reservedWords.put("do", Parser.DO);
        this.reservedWords.put("while", Parser.WHILE);
        this.reservedWords.put("class", Parser.CLASS);
        this.reservedWords.put("extends", Parser.EXTENDS);
        this.reservedWords.put("until", Parser.UNTIL);
        this.reservedWords.put("void", Parser.VOID);
    }

    public Integer getTokenNumber(String lexeme){
        Short tokenNumber = this.reservedWords.get(lexeme);

        if (tokenNumber == null) return null;

        return (int) tokenNumber;
    }
}
