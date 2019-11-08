package tpcompiladores.syntacticTree;

import tpcompiladores.conversions.ConversionIntToLong;
import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.conversions.ConversionMatrixCell;
import tpcompiladores.conversions.EmptyConversion;
import tpcompiladores.symbolsTable.Type;

public class MultiplicationTree extends SyntacticTree{
    private static final ConversionMatrix matrix = new ConversionMatrix();
    public MultiplicationTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super(leftTree, rightTree);
    }

    static {
        matrix.setCell(Type.INT, Type.INT, new ConversionMatrixCell(
                new ConversionIntToLong(),
                new ConversionIntToLong(),
                Type.LONG
        ));

        matrix.setCell(Type.INT, Type.LONG, new ConversionMatrixCell(
                new ConversionIntToLong(),
                new EmptyConversion(),
                Type.LONG
        ));

        matrix.setCell(Type.LONG, Type.INT, new ConversionMatrixCell(
                new EmptyConversion(),
                new ConversionIntToLong(),
                Type.LONG
        ));
        matrix.setCell(Type.LONG, Type.LONG, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.LONG
        ));
    }
}
