package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;

public class ConversionMatrixCell {
    private Conversion left = null, right = null;
    private Type result;

    public ConversionMatrixCell(Conversion left, Conversion right, Type result) {
        this.left = left;
        this.right = right;
        this.result = result;
    }

    public Conversion getLeft() {
        return this.left;
    }

    public Conversion getRight() {
        return this.right;
    }

    public Type getResult() {
        return this.result;
    }
}
