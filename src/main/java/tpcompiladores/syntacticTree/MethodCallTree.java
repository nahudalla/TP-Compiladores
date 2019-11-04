package tpcompiladores.syntacticTree;

import tpcompiladores.CompilerContext;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.parser.Class;

public class MethodCallTree extends SyntacticTree {
    private Class klass;
    private String methodName;

    private MethodCallTree(Class klass, String methodName) {
        this.klass = klass;
        this.methodName = methodName;
    }

    private MethodCallTree() {
        this.methodName = null;
        this.klass = null;
    }

    public static MethodCallTree create(CompilerContext compilerContext, Class klass, String methodName){
        if (klass.hasMethod(methodName)) {
            return new MethodCallTree(klass, methodName);
        }

        compilerContext.getLogger().logSemanticError(
                "el metodo " + methodName + "no se encuentra en la clase " + klass.getName()
        );

        return new MethodCallTree();
    }
}
