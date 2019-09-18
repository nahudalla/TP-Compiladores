package tpcompiladores.lexer.semanticActions;

import tpcompiladores.lexer.LexerContext;

public class ComposedSemanticAction implements SemanticAction {
    private SemanticAction semanticActionA;
    private SemanticAction semanticActionB;

    public ComposedSemanticAction(SemanticAction semanticActionA, SemanticAction semanticActionB){
        this.semanticActionA = semanticActionA;
        this.semanticActionB = semanticActionB;
    }

    @Override
    public void run(LexerContext lexerContext) {
        this.semanticActionA.run(lexerContext);
        this.semanticActionB.run(lexerContext);
    }
}
