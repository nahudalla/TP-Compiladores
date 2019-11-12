package tpcompiladores.syntacticTree.conversions;

import tpcompiladores.conversions.ConversionIntToLong;
import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.conversions.ConversionMatrixCell;
import tpcompiladores.conversions.EmptyConversion;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class SmallResultTypeTree extends SyntacticTreeWithConversions {
    private static final ConversionMatrix matrix = new ConversionMatrix();

    public SmallResultTypeTree(String operation, SyntacticTree leftTree, SyntacticTree rightTree) {
        super(operation, SmallResultTypeTree.matrix, leftTree, rightTree);
    }

    static {
        matrix.setCell(Type.INT, Type.INT, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.INT
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
