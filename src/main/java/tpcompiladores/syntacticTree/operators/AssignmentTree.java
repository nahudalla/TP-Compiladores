package tpcompiladores.syntacticTree.operators;

import tpcompiladores.conversions.*;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;
import tpcompiladores.syntacticTree.conversions.SyntacticTreeWithConversions;

public class AssignmentTree extends SyntacticTreeWithConversions {
    private static final ConversionMatrix matrix = new ConversionMatrix();

    public AssignmentTree(SyntacticTree leftTree, SyntacticTree rightTree) {
        super("Asignacion", AssignmentTree.matrix, leftTree, rightTree);
    }

    static {
        matrix.setCell(Type.INT, Type.INT, new ConversionMatrixCell(
                new EmptyConversion(),
                new EmptyConversion(),
                Type.INT
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
