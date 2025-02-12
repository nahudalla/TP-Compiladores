package tpcompiladores.conversions;

import tpcompiladores.symbolsTable.Type;

import java.util.HashMap;
import java.util.Map;

public class ConversionMatrix {
    private ConversionMatrixCell[][] conversionMatrix;
    private Map<Type,Integer> typeToIndex;

    private int convertTypeToIndex(Type type) {
        Integer index = typeToIndex.get(type);
        if (index == null) return this.conversionMatrix.length-1;

        return index;
    }

    public ConversionMatrix () {
        this.conversionMatrix = new ConversionMatrixCell[4][4];
        this.typeToIndex = new HashMap<Type,Integer>();
        this.typeToIndex.put(Type.INT, 0);
        this.typeToIndex.put(Type.LONG, 1);
        this.typeToIndex.put(Type.INVALID, 2);

        this.setCell(Type.INT, Type.INT, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.ERROR
        ));

        this.setCell(Type.INT, Type.LONG, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.ERROR
        ));

        this.setCell(Type.LONG, Type.INT, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.ERROR
        ));

        this.setCell(Type.LONG, Type.LONG, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.ERROR
        ));

        Conversion empty = new EmptyConversion();
        ConversionMatrixCell cell = new ConversionMatrixCell(empty, empty, Type.INVALID);
        this.fillPredefinedCells(Type.INVALID, cell);

        Conversion error = new EmptyConversion();
        ConversionMatrixCell errorCell = new ConversionMatrixCell(error, error, Type.ERROR);
        this.fillPredefinedCells(Type.ERROR, errorCell);

    }

    private void fillPredefinedCells (Type type, ConversionMatrixCell cell) {
        for(int i = 0; i < this.conversionMatrix.length; i++) {
            this.conversionMatrix[i][this.convertTypeToIndex(type)] = cell;
            this.conversionMatrix[this.convertTypeToIndex(type)][i] = cell;
        }
    }

    public ConversionMatrixCell getCell (Type left, Type right) {
        return this.conversionMatrix[this.convertTypeToIndex(left)][this.convertTypeToIndex(right)];
    }

    public void setCell (Type left, Type right, ConversionMatrixCell conversionMatrixCell) {
        this.conversionMatrix[this.convertTypeToIndex(left)][this.convertTypeToIndex(right)] = conversionMatrixCell;
    }
}
