package tpcompiladores.lexer;

public class FileLine {
    private String fileLine = null;
    private int index = 0;

    public FileLine(String fileLine){
        this.fileLine = fileLine;
    }

    public Character getNextCharacter(){
        Character character;
        if(fileLine != null) {
            if(index < fileLine.length()) {
                character = this.fileLine.charAt(index);
                index++;
            } else{
                character = '\n';
            }
        } else {
            return null;
        }
        return character;
    }
}
