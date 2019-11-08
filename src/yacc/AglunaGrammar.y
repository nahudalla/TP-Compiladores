%{
import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.symbolsTable.*;
import tpcompiladores.symbolsTable.Type;
import tpcompiladores.syntacticTree.*;
import tpcompiladores.syntacticTree.comparators.*;
import tpcompiladores.parser.ParserVal;
import java.util.ArrayList;
import java.io.IOException;
%}

%token ID NUMERIC_CONST CONST_STRING IF ELSE END_IF PRINT
%token INT BEGIN END LONG DO WHILE CLASS EXTENDS ASSIGNMENT
%token LESS_OR_EQUAL NOT_EQUAL GREATER_OR_EQUAL EQUALS UNTIL VOID

%%
inicio : programa ;

programa : lista_sentencias_declarativas sentencia_ejecutable ;

lista_sentencias_declarativas : sentencia_declarativa lista_sentencias_declarativas | /* empty */ ;

sentencia_declarativa : opciones_sentencia_declarativa ';';

opciones_sentencia_declarativa : declaracion_variables
                               | declaracion_clase ;

declaracion_variables : declaracion_variable | declaracion_objeto;

declaracion_variable : capturar_numero_linea tipo_var lista_identificadores {
    SymbolsTableEntry.setUse($3.tableRefs, SymbolsTableEntryUse.VARIABLE);
    SymbolsTableEntry.setType($3.tableRefs, new Type($2.type.getName()));
    logSyntacticStructure($1.ival, "Declaracion de variables");
};

tipo_var : INT { $$.type = Type.INT; }
         | LONG  { $$.type = Type.LONG; }
;

declaracion_objeto : capturar_numero_linea ID lista_identificadores {
    SymbolsTableEntry.setUse($3.tableRefs, SymbolsTableEntryUse.OBJECT);
    SymbolsTableEntry.setType($3.tableRefs, new Type($2.tableRef.getLexeme()));
    logSyntacticStructure($1.ival, "Declaracion de objetos");
};

lista_identificadores
  : ID { $$.tableRefs = new ArrayList<>(); $$.tableRefs.add($1.tableRef); }
  | ID ',' lista_identificadores { $$ = $3; $$.tableRefs.add($1.tableRef); };

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
                   | sentencia_print
                   | asignacion
                   | llamada_metodo_clase;

bloque_if : IF condicion bloque_sentencias else_if END_IF { $$.sval = "If" + $4.sval; }
          | IF error END_IF { yyerror("Error en sentencia 'if'."); }
          ;

else_if : ELSE bloque_sentencias {$$.sval = " (con else)";} | /* empty */;

bloque_sentencias : sentencia | bloque_lista_sentencias;

bloque_do_until : DO bloque_sentencias UNTIL condicion {$$.sval = "Bloque 'do' 'until'";}
                | DO error UNTIL condicion { yyerror("Error en sentencia 'do..until'"); }
                | DO bloque_sentencias UNTIL error { yyerror("Error en comparacion de 'do..until'"); }
                | DO error UNTIL error { yyerror("Error en sentencia 'do..until'"); };

sentencia_print : PRINT '(' CONST_STRING ')' { $$.sval = "Print"; }
                | PRINT error { yyerror("Error en sentencia 'print'"); };

expresion : expresion '+' termino | expresion '-' termino | termino ;

termino : termino '*' factor | termino '/' factor | factor ;

factor : ID { $$.tree = new LeafTree($1.tableRef); }
       | number
       | ref_miembro_clase ;

number : capturar_numero_linea number_negation NUMERIC_CONST {
    SymbolsTableEntry entry = processNumericConstant($2.bval, $3.tableRef);

    $$.tree = new LeafTree(entry);

    logSyntacticStructure($1.ival, "Constante " + entry.getType() + ": " + entry.getLexeme());
};

number_negation : '-' {$$.bval = true;} | /* empty */;

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

asignacion : izq_asignacion ASSIGNMENT expresion { $$.sval = "Asignacion"; }
           | izq_asignacion ASSIGNMENT { yyerror("Falta parte derecha de asignacion"); }
           | izq_asignacion ASSIGNMENT error { yyerror("Error en parte derecha de asignacion."); };

izq_asignacion : ID | ref_miembro_clase ;

llamada_metodo_clase : ref_miembro_clase '(' ')' { $$.sval = "Llamada a metodo de clase"; }
                     | ref_miembro_clase '(' error { yyerror("Error en llamada a metodo de clase"); };

condicion : '(' comparacion ')' { $$ = $2; logSyntacticStructure(getLineNumber(), "Condicion"); }
          | comparacion ')' { yyerror("Falta parentesis de inicio de condicion"); }
          | '(' comparacion { yyerror("Falta parentesis de cierre de condicion"); }
          | '(' ')' { yyerror("Falta comparacion"); }
          | comparacion { yyerror("Faltan los parentesis para la comparacion"); }
          | '(' error ')' { yyerror("Comparacion invalida"); };

comparacion
  : expresion LESS_OR_EQUAL expresion { $$.tree = new LessOrEqualComparisonTree($1.tree, $2.tree); }
  | expresion NOT_EQUAL expresion { $$.tree = new NotEqualComparisonTree($1.tree, $2.tree); }
  | expresion GREATER_OR_EQUAL expresion { $$.tree = new GreaterOrEqualComparisonTree($1.tree, $2.tree); }
  | expresion EQUALS expresion { $$.tree = new EqualsComparisonTree($1.tree, $2.tree); }
  | expresion '<' expresion { $$.tree = new LessThanComparisonTree($1.tree, $2.tree); }
  | expresion '>' expresion { $$.tree = new GreaterThanComparisonTree($1.tree, $2.tree); }
;

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
    if (message == null) return;

    this.context.getLogger().logSyntacticStructure(line, message);
}

public void setSymbolsTableReference (SymbolsTableEntry reference) {
    this.yylval = new ParserVal(reference);
}

private SymbolsTableEntry processNumericConstant (boolean isNegated, SymbolsTableEntry originalEntry) {
    SymbolsTable symbolsTable = this.context.getSymbolsTable();
    String symbolsTableReference = originalEntry.getIdentifier();
    String originalLexeme = originalEntry.getLexeme();
    Type type = originalEntry.getType();

    if (isNegated) {
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

    return symbolsTable.getEntry(symbolsTableReference);
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
