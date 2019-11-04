package tpcompiladores.parser;

import tpcompiladores.syntacticTree.SyntacticTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Klass {
    private String name;
    private String[] attributes = new String[0];
    private Map<String, SyntacticTree> methods = new HashMap<>();
    private Klass[] extendedClasses = new Klass[0];

    public Klass(String name) {
        this.name = name;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public void setMethods(Map<String,SyntacticTree> methods) {
        this.methods = methods;
    }

    public void setExtendedClasses(Klass[] extendedClasses) {
        this.extendedClasses = extendedClasses;
    }

    public boolean isSameOrParent (Klass otherClass) {
        if (this.equals(otherClass)) return true;

        for (Klass parent : this.extendedClasses) {
            if (parent.isSameOrParent(otherClass)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasMethod (String methodName) {
        return this.methods.containsKey(methodName);
    }

    public boolean hasAttribute (final String attributeName) {
        for (String attribute : attributes) {
            if (attributeName.equals(attribute)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Klass)) return false;
        Klass klass = (Klass) o;
        return Objects.equals(name, klass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
