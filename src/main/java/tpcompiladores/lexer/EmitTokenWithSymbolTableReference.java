package tpcompiladores.lexer;

public class EmitTokenWithSymbolTableReference extends  EmitToken implements SemanticAction{
    EmitToken emitToken;
    public EmitTokenWithSymbolTableReference(Lexer elementLexer) {
        super(elementLexer);
    }

    @Override
    public void run(LexerContext lexerContext) {
        lexerContext.getLexer().setNextToken(emitToken.getToken());
    }
}
