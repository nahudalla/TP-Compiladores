package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public class ComposedSemanticAction implements SemanticAction {
    private SemanticAction semanticActionA;
    private SemanticAction semanticActionB;

    public ComposedSemanticAction(SemanticAction semanticActionA, SemanticAction semanticActionB){
        this.semanticActionA = semanticActionA;
        this.semanticActionB = semanticActionB;
    }

    @Override
    public void run(CompilerContext compilerContext) {
        this.semanticActionA.run(compilerContext);
        this.semanticActionB.run(compilerContext);
    }
}
