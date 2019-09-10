package tpcompiladores.lexer;

public class Lexer {
    private int nextToken;

    public Lexer(int nextToken) {
        this.nextToken = nextToken;
    }

    public int getNextToken() {
        return nextToken;
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
    }

    public void setNextToken(int nextToken, String keyToken){
        this.nextToken = nextToken;
    }
}
