package tpcompiladores.parser;

import tpcompiladores.syntacticTree.SyntacticTree;

import java.util.Map;

public class Class {
    private String name;
    private String[] attributes;
    private Map<String, SyntacticTree> methods;

    public String getName() {
        return this.name;
    }

    public boolean isSameOrParent (Class otherClass) {
        return false;
    }

    public boolean hasMethod (String methodName) {
        return false;
    }

    public boolean hasAttribute (String attributeName) {
        return false;
    }
}
