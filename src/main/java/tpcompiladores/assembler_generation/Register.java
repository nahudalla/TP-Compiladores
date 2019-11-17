package tpcompiladores.assembler_generation;

public enum Register {
    // PELIGRO!! No cambiar el orden de definición de los registros,
    // eliminar o agregar nuevos sin adaptar los métodos de abajo.

    // 32-bit
    EBX, ECX, EDX, EAX,
    // 16-bit
    BX, CX, DX, AX;

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
}
