package tpcompiladores.lexer;

public class EmitToken {
    Integer number;
    Lexer element;

    public  EmitToken(Lexer elementLexer){
        this.number = elementLexer.getNextToken();
        this.element = elementLexer;
    }

    protected Integer getToken() {
        return this.number;
    }
}
