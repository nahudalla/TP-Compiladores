package tpcompiladores.lexer.fileInput;

public class FileLine {
    private String fileLine = null;
    private int index = 0;

    public FileLine(String fileLine){
        this.fileLine = fileLine;
    }

    public Character getNextCharacter() {
        if(this.fileLine == null) return null;

        if(this.index < this.fileLine.length()) {
            return this.fileLine.charAt(this.index++);
        } else {
            this.fileLine = null;
            return '\n';
        }
    }
}
