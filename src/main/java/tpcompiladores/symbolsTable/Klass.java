package tpcompiladores.symbolsTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                case VARIABLE: this.addAttribute(member); break;
                case METHOD: this.addMethod(member); break;
                default: throw new Error(
                    "Los miembros de una clase solo pueden ser ATTRIBUTE o METHOD. Se recibio: "
                    + member.getUse().name()
                );
            }
        }
    }

    private void addAttribute (SymbolsTableEntry attribute) {
        attribute.changeUse(SymbolsTableEntryUse.ATTRIBUTE);
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
        if (this.methods.containsKey(methodName)) return true;

        for (Klass parent : this.extendedClasses) {
            if (parent.hasMethod(methodName)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasAttribute (String attributeName) {
        if (this.attributes.containsKey(attributeName)) return true;

        for (Klass parent : this.extendedClasses) {
            if (parent.hasAttribute(attributeName)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasMember (SymbolsTableEntry entry) {
        if (entry == null || entry.getUse() == null) return false;

        switch (entry.getUse()) {
            case ATTRIBUTE: return this.hasAttribute(entry.getLexeme());
            case METHOD: return this.hasMethod(entry.getLexeme());
            default: return false;
        }
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
