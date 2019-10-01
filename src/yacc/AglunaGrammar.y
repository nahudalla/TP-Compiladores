%{
import tpcompiladores.CompilerContext;
import java.io.IOException;
%}

%token ID

%%
exp : ID ';' ID ';' ID {
    logWarning($1.sval);
    logWarning($3.sval);
    logWarning($5.sval);
}
;

%%
private CompilerContext context;

public Parser (CompilerContext context) {
    this.context = context;
    context.setParser(this);
}

private void logWarning (String message) {
    this.context.getLogger().logWarning(message);
}

public void setSymbolsTableReference (String reference) {
    this.yylval = new ParserVal(reference);
}

public int parse () {
    return this.yyparse();
}

int yylex () {
    try {
        return this.context.getLexer().getNextToken();
    } catch (IOException e) {
        this.yyerror(e.getMessage());
        return -1;
    }
}

void yyerror (String message) {
    this.context.getLogger().logError(message);
}
