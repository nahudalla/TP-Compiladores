package tpcompiladores.assembler_generation;

import java.util.Arrays;

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

    public Register useSpecialRegister32 () {
        return Register.bits32FromIndex(this.useFreeSpecialRegister());
    }

    public Register useSpecialRegister16 () {
        return Register.bits16FromIndex(this.useFreeSpecialRegister());
    }

    public Register useAXRegister () {
        int index = this.registersUsage.length-1;

        if (this.registersUsage[index]) throw new Error("El registro AX ya esta siendo usado!!");

        this.registersUsage[index] = true;

        return Register.bits16FromIndex(index);
    }

    public void freeRegister (Register register) {
        this.registersUsage[register.getIndex()] = false;
    }

    public boolean isUsed (Register register) {
        return this.registersUsage[register.getIndex()];
    }

    public boolean isFree (Register register) {
        return !this.isUsed(register);
    }
}
