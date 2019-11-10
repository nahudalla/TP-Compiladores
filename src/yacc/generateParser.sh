byaccj -v -Jnoconstruct -Jnorun -Jpackage=tpcompiladores.parser.yacc_generated -Jsemantic=ParserVal AglunaGrammar.y\
&& sed -i 's/return val;/return val.clone();/g' Parser.java\
&& mv ./*.java ../main/java/tpcompiladores/parser/yacc_generated
