package tpcompiladores.symbolsTable;

import java.util.Objects;

public class Type {
    private String name;
    public static final Type INT = new Type("int");
    public static final Type LONG = new Type("long");
    public static final Type STRING = new Type("_string");
    public static final Type INVALID = new Type("_invalid");
    public static final Type ERROR = new Type("_error");

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Type(" +
                this.name + ")";
    }
}
