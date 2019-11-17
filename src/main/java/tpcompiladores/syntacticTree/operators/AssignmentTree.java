package tpcompiladores.syntacticTree.operators;

import java.io.PrintStream;

import tpcompiladores.assembler_generation.Register;
import tpcompiladores.assembler_generation.Registers;
import tpcompiladores.conversions.ConversionIntToLong;
import tpcompiladores.conversions.ConversionMatrix;
import tpcompiladores.conversions.ConversionMatrixCell;
import tpcompiladores.conversions.EmptyConversion;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.ASMOperationResult;
import tpcompiladores.syntacticTree.LeafTree;
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

    @Override
    public ASMOperationResult generateCodeWithResult (PrintStream printStream, Registers registers) {
        ASMOperationResult leftHandSide = this.leftTree.generateCodeWithResult(printStream, registers);
        ASMOperationResult rightHandSide = this.rightTree.generateCodeWithResult(printStream, registers);

        if (rightHandSide.isInVariable()) {
            Register tmp;

            if (Type.LONG.equals(this.rightTree.resultType())) {
                tmp = registers.useRegister32();
            } else {
                tmp = registers.useRegister16();
            }

            printStream.println("MOV " + tmp + ", " + rightHandSide);
            printStream.println("MOV " + leftHandSide + ", " + tmp);

            registers.freeRegister(tmp);
        } else {
            printStream.println("MOV " + leftHandSide + ", " + rightHandSide);
        }

        if (rightHandSide.isInRegister()) {
            registers.freeRegister(rightHandSide.getRegister());
        }

        return null;
    }
}
