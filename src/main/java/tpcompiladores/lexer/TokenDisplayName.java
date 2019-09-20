package tpcompiladores.lexer;

public class TokenDisplayName {
    private static final String UNKNOWN = "DESCONOCIDO";
    private static final String INTEGER = "ENTERO";
    private static final String LONG = "LARGO";
    private static final String STRING = "CADENA";
    private static final String IDENTIFIER = "IDENTIFICADOR";
    private static final String EOF = "FIN_DE_ARCHIVO";

    public static String get (Integer tokenId) {
        if (tokenId == null) return UNKNOWN;
        if (tokenId == TokenNumbers.EOF) return EOF;
        if (tokenId < 256) return Character.toString((char) tokenId.intValue());

        switch (tokenId) {
            case TokenNumbers.CONST_INT: return INTEGER;
            case TokenNumbers.CONST_LONG: return LONG;
            case TokenNumbers.CONST_STRING: return STRING;
            case TokenNumbers.ID: return IDENTIFIER;
            default: return UNKNOWN;
        }
    }

    private TokenDisplayName () {}
}
