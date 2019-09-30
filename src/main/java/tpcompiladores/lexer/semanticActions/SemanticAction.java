package tpcompiladores.lexer.semanticActions;

import tpcompiladores.CompilerContext;

public interface SemanticAction {
    void run(CompilerContext compilerContext);
}
