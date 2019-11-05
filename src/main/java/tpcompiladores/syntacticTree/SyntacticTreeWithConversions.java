package tpcompiladores.syntacticTree;

import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.symbolsTable.Type;

public class SyntacticTreeWithConversions extends SyntacticTree{
    private Type resultType = null;

    protected SyntacticTreeWithConversions(ConversionMatrix conversionMatrix, SyntacticTree leftTree, SyntacticTree rightTree) {
        super(
            conversionMatrix.getCell(leftTree.resultType(), rightTree.resultType()).getLeftConversion().convert(leftTree),
            conversionMatrix.getCell(leftTree.resultType(), rightTree.resultType()).getRightConversion().convert(leftTree)
        );

        this.resultType = conversionMatrix.getCell(leftTree.resultType(), rightTree.resultType()).getResult();
    }

    @Override
    public Type resultType() {
        return this.resultType;
    }
}
