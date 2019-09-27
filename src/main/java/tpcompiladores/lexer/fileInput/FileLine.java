package tpcompiladores.lexer.fileInput;

public class FileLine {
    private String fileLine = null;
    private int index = 0;
    private boolean hasNextLine;

    public FileLine(String fileLine, boolean hasNextLine){
        this.fileLine = fileLine;
        this.hasNextLine = hasNextLine;
    }

    public Character getNextCharacter() {
        if(this.fileLine == null) return null;

        if(this.index < this.fileLine.length()) {
            return this.fileLine.charAt(this.index++);
        } else if (this.hasNextLine) {
            this.fileLine = null;
            return '\n';
        } else {
            this.fileLine = null;
            return null;
        }
    }
}
