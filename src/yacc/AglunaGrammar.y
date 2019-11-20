%{
import tpcompiladores.CompilerConstants;
import tpcompiladores.CompilerContext;
import tpcompiladores.symbolsTable.*;
import tpcompiladores.syntacticTree.*;
import tpcompiladores.syntacticTree.comparators.*;
import tpcompiladores.syntacticTree.conversions.*;
import tpcompiladores.syntacticTree.do_until.*;
import tpcompiladores.syntacticTree.if_tree.*;
import tpcompiladores.syntacticTree.operators.*;
import tpcompiladores.parser.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
%}

%token ID NUMERIC_CONST CONST_STRING IF ELSE END_IF PRINT
%token INT BEGIN END LONG DO WHILE CLASS EXTENDS ASSIGNMENT
%token LESS_OR_EQUAL NOT_EQUAL GREATER_OR_EQUAL EQUALS UNTIL VOID

%%
inicio : programa ;

programa
  : lista_sentencias_declarativas sentencia_ejecutable
{
  syntacticTree = $2.tree;
};

lista_sentencias_declarativas
  : sentencia_declarativa lista_sentencias_declarativas
  | /* empty */ { $$ = new ParserVal(); };

sentencia_declarativa
  : opciones_sentencia_declarativa ';'
  | error ';' { yyerror("Error en sentencia declarativa."); };

opciones_sentencia_declarativa
  : declaracion_variables
  | declaracion_clase ;

declaracion_variables
  : declaracion_variable
  | declaracion_objeto;

declaracion_variable
  : capturar_numero_linea tipo_var lista_identificadores
{
  $$ = $3;
  SymbolsTableEntry.setUse($3.tableRefs, SymbolsTableEntryUse.VARIABLE);
  SymbolsTableEntry.setType($3.tableRefs, new Type($2.type.getName()));
  logSyntacticStructure($1.ival, "Declaracion de variables");
};

tipo_var
  : INT { $$.type = Type.INT; }
  | LONG  { $$.type = Type.LONG; };

declaracion_objeto
  : capturar_numero_linea ID lista_identificadores
{
  if ($2.tableRef.getUse() == null)
    yyerror("La clase '" + $2.tableRef.getLexeme() + "' no ha sido declarada.");
  SymbolsTableEntry.setUse($3.tableRefs, SymbolsTableEntryUse.OBJECT);
  SymbolsTableEntry.setType($3.tableRefs, new Type($2.tableRef.getLexeme()));
  SymbolsTableEntry.setKlass($3.tableRefs, $2.tableRef.getKlass());
  logSyntacticStructure($1.ival, "Declaracion de objetos");
};

lista_identificadores
  : ID { $$.tableRefs = new ArrayList<>(); $$.tableRefs.add($1.tableRef); }
  | ID ',' lista_identificadores { $$ = $3; $$.tableRefs.add($1.tableRef); };

sentencia_ejecutable
  : bloque_lista_sentencias
  | /* empty */ { $$ = new ParserVal(); };

bloque_lista_sentencias
  : BEGIN capturar_numero_linea lista_sentencias END
{
  $$ = $3;
  logSyntacticStructure($2.ival, "Bloque de sentencias");
};

lista_sentencias
  : sentencia lista_sentencias { $$.tree = new SentenceTree($1.tree, $2.tree); }
  | /* empty */ { $$ = new ParserVal(); };

sentencia
  : capturar_numero_linea opciones_sentencia ';' { $$ = $2; logSyntacticStructure($1.ival, $2.sval); }
  | error ';' { yyerror("Error en sentencia ejecutable"); };

capturar_numero_linea
  : { $$.ival = getLineNumber(); };

opciones_sentencia
  : bloque_do_until
  | bloque_if
  | sentencia_print
  | asignacion
  | llamada_metodo_clase;

bloque_if
  : IF condicion bloque_sentencias else_if END_IF
{
  $$.tree = new IfTree(
      new IfComparisonTree($2.tree),
      new IfBranchesTree(
          new IfThenBranchTree($3.tree),
          new IfElseBranchTree($4.tree)
      )
  );
  $$.sval = "If" + $4.sval;
}
  | IF error END_IF { yyerror("Error en sentencia 'if'."); };

else_if
  : ELSE bloque_sentencias { $$.tree = $2.tree; $$.sval = " (con else)";}
  | /* empty */ { $$ = new ParserVal(); };

bloque_sentencias
  : sentencia
  | bloque_lista_sentencias;

bloque_do_until
  : DO bloque_sentencias UNTIL condicion
{
  $$.tree = new DoUntilTree($2.tree, new DoUntilComparisonTree($4.tree));
  $$.sval = "Bloque 'do' 'until'";
}
  | DO error UNTIL condicion { yyerror("Error en sentencia 'do..until'"); }
  | DO bloque_sentencias UNTIL error { yyerror("Error en comparacion de 'do..until'"); }
  | DO error UNTIL error { yyerror("Error en sentencia 'do..until'"); };

sentencia_print
  : PRINT '(' CONST_STRING ')'
{
  if ($3.tableRef.getUse() == null) {
    $3.tableRef.setUse(SymbolsTableEntryUse.CONSTANT);
    $3.tableRef.setType(Type.STRING);
  }

  $$.tree = new PrintTree(new LeafTree($3.tableRef));

  $$.sval = "Print";
}
  | PRINT error { yyerror("Error en sentencia 'print'"); };

expresion
  : expresion '+' termino { $$.tree = new AdditionTree($1.tree, $3.tree); }
  | expresion '-' termino { $$.tree = new SubtractionTree($1.tree, $3.tree); }
  | termino;

termino
  : termino '*' factor { $$.tree = new MultiplicationTree($1.tree, $3.tree); }
  | termino '/' factor { $$.tree = new DivisionTree($1.tree, $3.tree); }
  | factor;

factor
  : ID { $$.tree = new LeafTree($1.tableRef); }
  | number
  | ref_atributo_clase ;

number
  : capturar_numero_linea number_negation NUMERIC_CONST
{
  SymbolsTableEntry entry = processNumericConstant($2.bval, $3.tableRef);
  entry.setUse(SymbolsTableEntryUse.CONSTANT);

  $$.tree = new LeafTree(entry);

  logSyntacticStructure($1.ival, "Constante " + entry.getType() + ": " + entry.getLexeme());
};

number_negation
  : '-' {$$.bval = true;}
  | /* empty */ { $$ = new ParserVal(); };

declaracion_clase
  : CLASS error { yyerror("Error en declaracion de clase."); }
  | CLASS ID capturar_numero_linea extends_clase BEGIN declaraciones_cuerpo_clase END
{
  Klass klass = new Klass($2.tableRef.getLexeme());

  $2.tableRef.setUse(SymbolsTableEntryUse.CLASS);
  $2.tableRef.setKlass(klass);

  List<SymbolsTableEntry> extends_entries = $4.tableRefs;
  String withExtends = "";

  if (extends_entries != null) {
    withExtends = " (con extends)";
    Klass[] klasses = new Klass[extends_entries.size()];
    int i = 0;

    for (SymbolsTableEntry entry : extends_entries) {
      klasses[i++] = entry.getKlass();
    }

    klass.setExtendedClasses(klasses);
  }

  klass.setMembers($6.tableRefs);

  logSyntacticStructure($2.ival, "Declaracion clase" + withExtends);
};

extends_clase
  : EXTENDS lista_identificadores { $$ = $2; }
  | /* empty */ { $$ = new ParserVal(); };

declaraciones_cuerpo_clase
  : declaracion_variables ';' declaraciones_cuerpo_clase { $$ = $3; $3.tableRefs.addAll($1.tableRefs); }
  | declaracion_metodo ';' declaraciones_cuerpo_clase { $$ = $3; $3.tableRefs.add($1.tableRef); }
  | error ';' declaraciones_cuerpo_clase { $$ = $3; yyerror("Error en declaracion de miembro de clase"); }
  | /* empty */ { $$ = new ParserVal(); $$.tableRefs = new ArrayList<>(); };

declaracion_metodo
  : capturar_numero_linea VOID ID '(' ')' bloque_lista_sentencias
{
  $$.tableRef = $3.tableRef;
  $$.tableRef.setUse(SymbolsTableEntryUse.METHOD);
  $$.tableRef.setTree($6.tree);

  logSyntacticStructure($1.ival, "Declaracion de metodo de clase");
};

ref_miembro_clase
  : ID capturar_numero_linea '.' ID
{
  $$.objTableRef = $1.tableRef;
  $$.tableRef = $4.tableRef;

  if (!$1.tableRef.isObject()) {
    logSemanticError("El identificador '" + $1.tableRef.getLexeme() + "' no es un objeto.");
  } else if (!$1.tableRef.getKlass().hasMember($4.tableRef)) {
    logSemanticError(
      "El identificador '"+$4.tableRef.getLexeme()+
      "' no es miembro de la clase '"+$1.tableRef.getKlass().getName()+"'."
    );
  }
}

ref_atributo_clase : ref_miembro_clase { $$.tree = new AttributeReferenceTree($1.objTableRef, $1.tableRef); }

asignacion
  : izq_asignacion ASSIGNMENT expresion {
    $$.tree = new AssignmentTree($1.tree, $3.tree);
    $$.sval = "Asignacion";
}
  | izq_asignacion ASSIGNMENT { yyerror("Falta parte derecha de asignacion"); }
  | izq_asignacion ASSIGNMENT error { yyerror("Error en parte derecha de asignacion."); };

izq_asignacion
  : ID
{
  if (!SymbolsTableEntryUse.VARIABLE.equals($1.tableRef.getUse()))
    yyerror("El atributo ''" + $1.tableRef.getLexeme() + "' no está referenciado correctamente.");
  $$.tree = new LeafTree($1.tableRef);
}
  | ref_atributo_clase ;

llamada_metodo_clase
  : ref_miembro_clase '(' ')' { $$.tree = new MethodCallTree($1.tableRef); $$.sval = "Llamada a metodo de clase"; }
  | ref_miembro_clase '(' error { yyerror("Error en llamada a metodo de clase"); };

condicion
  : '(' comparacion ')' { $$ = $2; logSyntacticStructure(getLineNumber(), "Condicion"); }
  | comparacion ')' { yyerror("Falta parentesis de inicio de condicion"); }
  | '(' comparacion { yyerror("Falta parentesis de cierre de condicion"); }
  | '(' ')' { yyerror("Falta comparacion"); }
  | comparacion { yyerror("Faltan los parentesis para la comparacion"); }
  | '(' error ')' { yyerror("Comparacion invalida"); };

comparacion
  : expresion LESS_OR_EQUAL expresion { $$.tree = ComparisonTree.lessOrEquals($1.tree, $3.tree); }
  | expresion NOT_EQUAL expresion { $$.tree = ComparisonTree.notEquals($1.tree, $3.tree); }
  | expresion GREATER_OR_EQUAL expresion { $$.tree = ComparisonTree.greaterOrEquals($1.tree, $3.tree); }
  | expresion EQUALS expresion { $$.tree = ComparisonTree.equals($1.tree, $3.tree); }
  | expresion '<' expresion { $$.tree = ComparisonTree.less($1.tree, $3.tree); }
  | expresion '>' expresion { $$.tree = ComparisonTree.greater($1.tree, $3.tree); }
;

%%
public static final short EOF = 0;

private CompilerContext context;
private SyntacticTree syntacticTree;

public Parser (CompilerContext context) {
    this.context = context;
    context.setParser(this);
}

private void logSemanticError (String message) {
    this.context.getLogger().logSemanticError(message);
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

public ParsingResult parse () {
    int code = this.yyparse();

    return new ParsingResult(code, this.syntacticTree);
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
