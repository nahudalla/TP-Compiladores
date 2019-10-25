package tpcompiladores;

public class CompilerConstants {
    public static final long MAX_ABS_LONG = (long) Math.pow(2, 31);
    public static final long MAX_ABS_INT = (long) Math.pow(2, 15);
    public static final long MAX_POSITIVE_LONG = MAX_ABS_LONG - 1;
    public static final long MAX_POSITIVE_INT = MAX_ABS_INT - 1;
    public static final int MAX_ID_LENGTH = 25;

    private CompilerConstants () {}
}
