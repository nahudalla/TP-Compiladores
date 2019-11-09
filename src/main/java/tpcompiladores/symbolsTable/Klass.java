package tpcompiladores.symbolsTable;

import tpcompiladores.syntacticTree.SyntacticTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Klass {
    private String name;
    private Map<String, SymbolsTableEntry> attributes = new HashMap<>();
    private Map<String, SymbolsTableEntry> methods = new HashMap<>();
    private Klass[] extendedClasses = new Klass[0];

    public Klass(String name) {
        this.name = name;
    }

    public void setMembers (List<SymbolsTableEntry> members) {
        for (SymbolsTableEntry member : members) {
            switch (member.getUse()) {
                case ATTRIBUTE: this.addAttribute(member); break;
                case METHOD: this.addMethod(member); break;
                default: throw new Error(
                    "Los miembros de una clase solo pueden ser ATTRIBUTE o METHOD. Se recibio: "
                    + member.getUse().name()
                );
            }
        }
    }

    private void addAttribute (SymbolsTableEntry attribute) {
        attribute.setKlass(this);
        this.attributes.put(attribute.getLexeme(), attribute);
    }

    private void addMethod (SymbolsTableEntry method) {
        method.setKlass(this);
        this.methods.put(method.getLexeme(), method);
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

    public boolean hasAttribute (String attributeName) {
        for (String attribute : this.attributes.keySet()) {
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

	public String getName() {
		return this.name;
	}
}
