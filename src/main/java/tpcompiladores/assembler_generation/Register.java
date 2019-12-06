package tpcompiladores.assembler_generation;

import tpcompiladores.syntacticTree.ASMOperationResult;

public enum Register {
    // PELIGRO!! No cambiar el orden de definición de los registros,
    // eliminar o agregar nuevos sin adaptar los métodos de abajo.

    // 32-bit
    EBX, ECX, EDX, EAX,
    // 16-bit
    BX, CX, DX, AX;

    private ASMOperationResult operationResult = null;

    public int getIndex () {
        return this.ordinal() % 4;
    }

    public static Register bits32FromIndex (int index) {
        return Register.values()[index % 4];
    }

    public static Register bits16FromIndex (int index) {
        return Register.values()[(index % 4) + 4];
    }

    public Register toRegister32 () {
        return Register.values()[this.ordinal() % 4];
    }

    public Register toRegister16 () {
        return Register.values()[(this.ordinal() % 4) + 4];
    }

    public Register getCounterpart () {
        if (this.is16BitRegister()) return this.toRegister32();
        else return this.toRegister16();
    }

    public void setASMOperationResult (ASMOperationResult operationResult) {
        this.operationResult = operationResult;
        this.getCounterpart().operationResult = operationResult;
    }

    public boolean is32BitRegister () {
        return (this.ordinal() % 4) == this.ordinal();
    }

    public boolean is16BitRegister () {
        return !this.is32BitRegister();
    }

    public ASMOperationResult getASMOperationResult () {
        return this.operationResult;
    }
}
