package tpcompiladores.parser;

import tpcompiladores.syntacticTree.SyntacticTree;

import java.util.Map;

public class Class {
    private String[] attributes;
    private Map<String, SyntacticTree> methods;

    public boolean isSameOrParent (Class otherClass) {
        return false;
    }
}
