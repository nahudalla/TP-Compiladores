package tpcompiladores.syntacticTree;

import tpcompiladores.CompilerContext;
import tpcompiladores.parser.Klass;

public class MethodCallTree extends SyntacticTree {
    private Klass klass;
    private String methodName;

    private MethodCallTree(Klass klass, String methodName) {
        this.klass = klass;
        this.methodName = methodName;
    }

    private MethodCallTree() {
        this.methodName = null;
        this.klass = null;
    }

    public static MethodCallTree create(CompilerContext compilerContext, Klass klass, String methodName){
        if (klass.hasMethod(methodName)) {
            return new MethodCallTree(klass, methodName);
        }

        compilerContext.getLogger().logSemanticError(
                "el metodo " + methodName + " no se encuentra en la clase " + klass.getName()
        );

        return new MethodCallTree();
    }
}
