package tpcompiladores.lexer;

public class TokenDisplayName {
    private static final String UNKNOWN = "DESCONOCIDO";
    private static final String CTE_INTEGER = "CTE_ENTERA";
    private static final String CTE_LONG = "CTE_LARGA";
    private static final String STRING = "CADENA";
    private static final String IDENTIFIER = "IDENTIFICADOR";
    private static final String EOF = "FIN_DE_ARCHIVO";
    private static final String IF = "IF";
    private static final String ELSE = "ELSE";
    private static final String END_IF = "";
    private static final String PRINT = "PRINT";
    private static final String INT = "INT";
    private static final String BEGIN = "BEGIN";
    private static final String END = "END";
    private static final String LONG = "LONG";
    private static final String DO = "DO";
    private static final String WHILE = "WHILE";
    private static final String CLASS = "CLASS";
    private static final String EXTENDS = "EXTENDS";
    private static final String ASSIGNMENT = "ASIGNACION";
    private static final String LESS_OR_EQUAL = "MENOR_IGUAL";
    private static final String NOT_EQUAL = "DISTINTO";
    private static final String GREATER_OR_EQUAL = "MAYOR_IGUAL";
    private static final String EQUALS = "IGUAL";
    private static final String UNTIL = "UNTIL";
    private static final String VOID = "VOID";

    public static String get (Integer tokenId) {
        if (tokenId == null) return UNKNOWN;
        if (tokenId == TokenNumbers.EOF) return EOF;
        if (tokenId < 256) return Character.toString((char) tokenId.intValue());

        switch (tokenId) {
            case TokenNumbers.CONST_INT: return CTE_INTEGER;
            case TokenNumbers.CONST_LONG: return CTE_LONG;
            case TokenNumbers.CONST_STRING: return STRING;
            case TokenNumbers.IF: return IF;
            case TokenNumbers.ELSE: return ELSE;
            case TokenNumbers.END_IF: return END_IF;
            case TokenNumbers.PRINT: return PRINT;
            case TokenNumbers.ID: return IDENTIFIER;
            case TokenNumbers.INT: return INT;
            case TokenNumbers.BEGIN: return BEGIN;
            case TokenNumbers.END: return END;
            case TokenNumbers.LONG: return LONG;
            case TokenNumbers.DO: return DO;
            case TokenNumbers.WHILE: return WHILE;
            case TokenNumbers.CLASS: return CLASS;
            case TokenNumbers.EXTENDS: return EXTENDS;
            case TokenNumbers.ASSIGNMENT: return ASSIGNMENT;
            case TokenNumbers.LESS_OR_EQUAL: return LESS_OR_EQUAL;
            case TokenNumbers.NOT_EQUAL: return NOT_EQUAL;
            case TokenNumbers.GREATER_OR_EQUAL: return GREATER_OR_EQUAL;
            case TokenNumbers.EQUALS: return EQUALS;
            case TokenNumbers.UNTIL: return UNTIL;
            case TokenNumbers.VOID: return VOID;
            default: return UNKNOWN;
        }
    }

    private TokenDisplayName () {}
}
