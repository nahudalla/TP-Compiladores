package tpcompiladores.lexer;

import tpcompiladores.parser.yacc_generated.Parser;

public class TokenDisplayName {
    private static String buildResWordStr (String name) {
        return "PAL_RESERVADA(" + name + ")";
    }

    private static final String UNKNOWN = "DESCONOCIDO";
    private static final String CTE_NUM = "CONSTANTE_NUMERICA";
    private static final String STRING = "CADENA";
    private static final String IDENTIFIER = "IDENTIFICADOR";
    private static final String EOF = "FIN_DE_ARCHIVO";
    private static final String IF = buildResWordStr("IF");
    private static final String ELSE = buildResWordStr("ELSE");
    private static final String END_IF = buildResWordStr("END_IF");
    private static final String PRINT = buildResWordStr("PRINT");
    private static final String INT = buildResWordStr("INT");
    private static final String BEGIN = buildResWordStr("BEGIN");
    private static final String END = buildResWordStr("END");
    private static final String LONG = buildResWordStr("LONG");
    private static final String DO = buildResWordStr("DO");
    private static final String WHILE = buildResWordStr("WHILE");
    private static final String CLASS = buildResWordStr("CLASS");
    private static final String EXTENDS = buildResWordStr("EXTENDS");
    private static final String ASSIGNMENT = "ASIGNACION";
    private static final String LESS_OR_EQUAL = "MENOR_IGUAL";
    private static final String NOT_EQUAL = "DISTINTO";
    private static final String GREATER_OR_EQUAL = "MAYOR_IGUAL";
    private static final String EQUALS = "IGUAL";
    private static final String UNTIL = buildResWordStr("UNTIL");
    private static final String VOID = buildResWordStr("VOID");

    public static String get (Integer tokenId) {
        if (tokenId == null) return UNKNOWN;
        if (tokenId == Parser.EOF) return EOF;
        if (tokenId < 256) return Character.toString((char) tokenId.intValue());

        int primitiveTokenId = tokenId;

        switch (primitiveTokenId) {
            case Parser.NUMERIC_CONST: return CTE_NUM;
            case Parser.CONST_STRING: return STRING;
            case Parser.IF: return IF;
            case Parser.ELSE: return ELSE;
            case Parser.END_IF: return END_IF;
            case Parser.PRINT: return PRINT;
            case Parser.ID: return IDENTIFIER;
            case Parser.INT: return INT;
            case Parser.BEGIN: return BEGIN;
            case Parser.END: return END;
            case Parser.LONG: return LONG;
            case Parser.DO: return DO;
            case Parser.WHILE: return WHILE;
            case Parser.CLASS: return CLASS;
            case Parser.EXTENDS: return EXTENDS;
            case Parser.ASSIGNMENT: return ASSIGNMENT;
            case Parser.LESS_OR_EQUAL: return LESS_OR_EQUAL;
            case Parser.NOT_EQUAL: return NOT_EQUAL;
            case Parser.GREATER_OR_EQUAL: return GREATER_OR_EQUAL;
            case Parser.EQUALS: return EQUALS;
            case Parser.UNTIL: return UNTIL;
            case Parser.VOID: return VOID;
            default: return UNKNOWN;
        }
    }

    private TokenDisplayName () {}
}
