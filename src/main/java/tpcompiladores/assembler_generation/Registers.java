package tpcompiladores.assembler_generation;

import java.io.PrintStream;
import java.util.Arrays;

import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.ASMOperationResult;

public class Registers {
    private final Boolean[] registersUsage = new Boolean[4];

    public Registers() {
        Arrays.fill(this.registersUsage, false);
    }

    private int useFreeRegister (int from) {
        int index = -1;
        for (int i = from; i < registersUsage.length; i++) {
            if (!this.registersUsage[i]) {
                index = i;
                break;
            }
        }

        if (index == -1) throw new Error("Could not find empty register to use!");

        this.registersUsage[index] = true;

        return index;
    }

    private int useFreeRegister () {
        return this.useFreeRegister(0);
    }

    private int useFreeSpecialRegister () {
        return this.useFreeRegister(2);
    }

    public Register useRegister32 () {
        return Register.bits32FromIndex(this.useFreeRegister());
    }

    public Register useRegister16 () {
        return Register.bits16FromIndex(this.useFreeRegister());
    }

    public Register useSameWidthRegister (Register register) {
        if (register.is16BitRegister()) return this.useRegister16();
        else return this.useRegister32();
    }

    public Register useRegisterForType (Type type) {
        if (Type.LONG.equals(type)) return this.useRegister32();
        else return this.useRegister16();
    }

    public Register useSpecialRegister32 () {
        return Register.bits32FromIndex(this.useFreeSpecialRegister());
    }

    public Register useSpecialRegister16 () {
        return Register.bits16FromIndex(this.useFreeSpecialRegister());
    }

    public void useRegister (Register register) {
        if (this.registersUsage[register.getIndex()]) {
            throw new Error("El registro " + register + " ya esta siendo usado!!");
        }

        this.registersUsage[register.getIndex()] = true;
    }

    public void freeRegister (Register register) {
        if (register == null) return;

        this.registersUsage[register.getIndex()] = false;
    }

    public boolean isUsed (Register register) {
        return this.registersUsage[register.getIndex()];
    }

    public boolean isFree (Register register) {
        return !this.isUsed(register);
    }

    public void forceUseRegister (Register requiredRegister, PrintStream printStream) {
        if (this.isFree(requiredRegister)) {
            this.useRegister(requiredRegister);
        } else {
            ASMOperationResult operationResult = requiredRegister.getASMOperationResult();
            Register newRegister = this.useSameWidthRegister(operationResult.getRegister());

            printStream.println("MOV " + newRegister + ", " + operationResult.getRegister());

            operationResult.setRegister(newRegister);
        }
    }
}
