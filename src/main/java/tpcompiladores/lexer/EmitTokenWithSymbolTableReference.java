package tpcompiladores.lexer;

public class EmitTokenWithSymbolTableReference extends EmitToken {
    String keyToken;
    public EmitTokenWithSymbolTableReference(int valueToken, String keyToken) {
        super(valueToken);
        this.keyToken = keyToken;
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLexer().setNextToken(super.getToken());
    }
}
