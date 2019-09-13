package tpcompiladores.lexer;

public class Lexer {
    private Integer nextToken;

    public Integer getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
    }

    public void setNextToken(int nextToken, String symbolsTableReference){
        this.nextToken = nextToken;
    }
}
