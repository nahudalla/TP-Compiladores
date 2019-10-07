@echo off

yacc -v -Jnoconstruct -Jnorun -Jpackage=tpcompiladores.parser.yacc_generated AglunaGrammar.y
move *.java ../main/java/tpcompiladores/parser/yacc_generated
