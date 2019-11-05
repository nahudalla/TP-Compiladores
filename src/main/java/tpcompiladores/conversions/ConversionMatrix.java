package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;

import java.util.HashMap;
import java.util.Map;

public class ConversionMatrix {
    private ConversionMatrixCell[][] conversionMatrix;
    private Map<Type,Integer> typeToIndex;

    private int typeToIndex(Type left) {
        if (!this.typeToIndex.containsKey(left)) return this.typeToIndex.size()-1;

        return this.typeToIndex.get(left);
    }

    public ConversionMatrix () {
        this.conversionMatrix = new ConversionMatrixCell[4][4];
        this.typeToIndex = new HashMap<Type,Integer>();
        this.typeToIndex.put(Type.INT, 0);
        this.typeToIndex.put(Type.LONG, 1);
        this.typeToIndex.put(Type.INVALID, 2);
    }

    public ConversionMatrixCell getCell (Type left,Type right) {
        return this.conversionMatrix [this.typeToIndex(left)][this.typeToIndex(right)];
    }

    public void setCell (Type left, Type right, ConversionMatrixCell conversionMatrixCell) {
        this.conversionMatrix[this.typeToIndex(left)][this.typeToIndex(right)] = conversionMatrixCell;
    }
}
