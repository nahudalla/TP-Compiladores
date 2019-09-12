package tpcompiladores.lexer;

public class EmitTokenWithSymbolTableReference extends EmitToken {
    private String reference;

    public EmitTokenWithSymbolTableReference(int valueToken, String reference) {
        super(valueToken);
        this.reference = reference;
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLexer().setNextToken(super.getToken(), this.reference);
    }
}
