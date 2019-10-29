%{
import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.symbolsTable.SymbolsTable;
import tpcompiladores.symbolsTable.SymbolsTableEntry;
import tpcompiladores.symbolsTable.Type;
import java.io.IOException;
%}

%token ID NUMERIC_CONST CONST_STRING IF ELSE END_IF PRINT
%token INT BEGIN END LONG DO WHILE CLASS EXTENDS ASSIGNMENT
%token LESS_OR_EQUAL NOT_EQUAL GREATER_OR_EQUAL EQUALS UNTIL VOID

%%
inicio : programa ;

programa : lista_sentencias_declarativas sentencia_ejecutable ;

lista_sentencias_declarativas : sentencia_declarativa lista_sentencias_declarativas | /* empty */ ;

sentencia_declarativa : opciones_sentencia_declarativa ';'
                      | error ';' {yyerror("Error en sentencia declarativa"); };

opciones_sentencia_declarativa : declaracion_variables
                               | declaracion_clase ;

declaracion_variables : capturar_numero_linea tipo lista_identificadores {
    if ($2.sval == "OBJ") logSyntacticStructure($1.ival, "Declaracion de objetos");
    else logSyntacticStructure($1.ival, "Declaracion de variables");
};

tipo : INT | LONG | ID { $$.sval = "OBJ"; } ;

lista_identificadores : ID | ID ',' lista_identificadores;

sentencia_ejecutable : bloque_lista_sentencias | /* empty */;

bloque_lista_sentencias : BEGIN capturar_numero_linea lista_sentencias END {
    logSyntacticStructure($2.ival, "Bloque de sentencias");
} ;

lista_sentencias : sentencia lista_sentencias | /* empty */;

sentencia : capturar_numero_linea opciones_sentencia ';' {logSyntacticStructure($1.ival, $2.sval);}
          | error ';' { yyerror("Error en sentencia ejecutable"); };

capturar_numero_linea : { $$.ival = getLineNumber(); };

opciones_sentencia : bloque_do_until
                   | bloque_if
                   | sentencia_print {$$.sval = "Print";}
                   | asignacion {$$.sval = "Asignacion";}
                   | llamada_metodo_clase {$$.sval = "Llamada a metodo de clase";};

bloque_if : IF condicion bloque_sentencias else_if END_IF { $$.sval = "If" + $4.sval; }
          | IF error else_if END_IF { yyerror("Bloque if invalido (con else valido)"); }
          ;
else_if : ELSE bloque_sentencias {$$.sval = " (con else)";} | /* empty */ {$$.sval = "";} ;

bloque_sentencias : sentencia | bloque_lista_sentencias;

bloque_do_until : DO bloque_sentencias UNTIL condicion {$$.sval = "Bloque 'do' 'until'";};

sentencia_print : PRINT '(' CONST_STRING ')';

expresion : expresion '+' termino | expresion '-' termino | termino ;

termino : termino '*' factor | termino '/' factor | factor ;

factor : ID | number | ref_miembro_clase ;

number : capturar_numero_linea number_negation NUMERIC_CONST {
    $$ = processNumericConstant($2.ival, $3.sval);

    SymbolsTable symbolsTable = this.context.getSymbolsTable();
    SymbolsTableEntry entry = symbolsTable.getEntry($$.sval);
    logSyntacticStructure($1.ival, "Constante " + entry.getType() + ": " + entry.getLexeme());
};

number_negation : '-' {$$.ival = 1;} | /* empty */ {$$.ival = 0;};

declaracion_clase : header_clase capturar_numero_linea cuerpo_clase { logSyntacticStructure($2.ival, "Declaracion clase"); }
                  | header_clase capturar_numero_linea extends_clase cuerpo_clase { logSyntacticStructure($2.ival, "Declaracion clase (con extends)"); } ;

header_clase : CLASS ID ;

extends_clase : EXTENDS lista_identificadores;

cuerpo_clase : BEGIN declaraciones_cuerpo_clase END ;

declaraciones_cuerpo_clase : declaracion_cuerpo_clase declaraciones_cuerpo_clase | /* empty */ ;

declaracion_cuerpo_clase : declaracion_variables ';'
                         | declaracion_metodos ';'
                         | error ';' { yyerror("Error en declaracion de miembro de clase"); };

declaracion_metodos : capturar_numero_linea VOID ID '(' ')' bloque_lista_sentencias {logSyntacticStructure($1.ival, "Declaracion de metodo de clase");};

ref_miembro_clase :  ID capturar_numero_linea '.' ID  {logSyntacticStructure($2.ival, "Referencia a miembro de clase");};

asignacion : izq_asignacion ASSIGNMENT expresion;

izq_asignacion : ID | ref_miembro_clase ;

llamada_metodo_clase : ref_miembro_clase '(' ')';

condicion : '(' comparacion ')'
          | comparacion ')' { yyerror("Falta parentesis de inicio de condicion"); }
          | '(' comparacion { yyerror("Falta parentesis de cierre de condicion"); }
          | '(' ')' { yyerror("Falta comparacion"); }
          | comparacion { yyerror("Faltan los parentesis para la comparacion"); }
          | '(' error ')' { yyerror("Comparacion invalida"); };

comparacion : expresion comparador expresion ;

comparador : LESS_OR_EQUAL | NOT_EQUAL | GREATER_OR_EQUAL | EQUALS | '<' | '>' ;

%%
public static final short EOF = 0;

private CompilerContext context;

public Parser (CompilerContext context) {
    this.context = context;
    context.setParser(this);
}

private void logWarning (String message) {
    this.context.getLogger().logParserWarning(message);
}

private int getLineNumber () {
    return this.context.getLogger().getCurrentLineNumber();
}

private void logSyntacticStructure (int line, String message) {
    this.context.getLogger().logSyntacticStructure(line, message);
}

public void setSymbolsTableReference (String reference) {
    this.yylval = new ParserVal(reference);
}

private ParserVal processNumericConstant (int isNegated, String symbolsTableReference) {
    SymbolsTable symbolsTable = this.context.getSymbolsTable();
    SymbolsTableEntry originalEntry = symbolsTable.getEntry(symbolsTableReference);
    String originalLexeme = originalEntry.getLexeme();
    Type type = originalEntry.getType();

    if (isNegated == 1) {
        SymbolsTableEntry entry = new SymbolsTableEntry();
        entry.setLexeme("-" + originalLexeme);
        entry.setType(type);
        symbolsTableReference = symbolsTable.addNumericConstant(entry);
    } else {
        long originalValue = Long.parseLong(originalLexeme);
        if (type.equals(Type.INT) && originalValue > CompilerConstants.MAX_POSITIVE_INT) {
            type = Type.LONG;
            originalEntry.setType(type);
        }
        if (type.equals(Type.LONG) && originalValue > CompilerConstants.MAX_POSITIVE_LONG) {
            this.yyerror("Constante positiva fuera de rango: " + originalValue + ". Maximo permitido: " +
                CompilerConstants.MAX_POSITIVE_LONG + ". Asumiendo el valor: " + CompilerConstants.MAX_POSITIVE_LONG + ".");
            SymbolsTableEntry entry = new SymbolsTableEntry();
            entry.setLexeme(String.valueOf(CompilerConstants.MAX_POSITIVE_LONG));
            entry.setType(type);
            symbolsTableReference = symbolsTable.addNumericConstant(entry);
        }
    }

    return new ParserVal(symbolsTableReference);
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
    this.context.getLogger().logParserError(message);
}
