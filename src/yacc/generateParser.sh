byaccj -v -Jnoconstruct -Jnorun -Jpackage=tpcompiladores.parser.yacc_generated AglunaGrammar.y && (
  mv ./*.java ../main/java/tpcompiladores/parser/yacc_generated
)
