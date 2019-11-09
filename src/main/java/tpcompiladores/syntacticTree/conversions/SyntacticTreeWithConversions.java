package tpcompiladores.syntacticTree.conversions;

import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.SyntacticTree;

public class SyntacticTreeWithConversions extends SyntacticTree {
    private Type resultType = null;

    protected SyntacticTreeWithConversions(ConversionMatrix conversionMatrix, SyntacticTree leftTree, SyntacticTree rightTree) {
        super(
            SyntacticTreeWithConversions.convertLeftTree(conversionMatrix, leftTree, rightTree),
            SyntacticTreeWithConversions.convertRightTree(conversionMatrix, leftTree, rightTree)
        );

        this.resultType = conversionMatrix.getCell(leftTree.resultType(), rightTree.resultType()).getResult();
    }

    private static SyntacticTree convertLeftTree (ConversionMatrix matrix, SyntacticTree leftTree, SyntacticTree rightTree) {
        Type leftType = leftTree.resultType();
        Type rightType = rightTree.resultType();
        return matrix
            .getCell(leftType, rightType)
            .getLeftConversion()
            .convert(leftTree);
    }

    private static SyntacticTree convertRightTree (ConversionMatrix matrix, SyntacticTree leftTree, SyntacticTree rightTree) {
        Type leftType = leftTree.resultType();
        Type rightType = rightTree.resultType();
        return matrix
            .getCell(leftType, rightType)
            .getRightConversion()
            .convert(rightTree);
    }

    @Override
    public Type resultType() {
        return this.resultType;
    }
}
