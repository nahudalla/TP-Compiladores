package tpcompiladores.lexer.stateMachine;

import tpcompiladores.lexer.semanticActions.*;
import tpcompiladores.lexer.stateMachine.aglunaCharacterFilters.*;
import tpcompiladores.parser.yacc_generated.Parser;

public class AglunaStateMachine {
    // AGLUNA is the name of our toy language.
    // It comes from our names: AGustín, LUcas, NAhuel

    private static final int TOTAL_AMOUNT_OF_STATES = 10;
    private static final int TOTAL_AMOUNT_OF_CHARACTER_CLASSES = 15;

    private AglunaStateMachine () {}

    private static StateMachine stateMachine = null;

    public static StateMachine getStateMachine () {
        if (AglunaStateMachine.stateMachine == null) {
            AglunaStateMachine.stateMachine = createStateMachine();
        }

        return AglunaStateMachine.stateMachine;
    }

    private static StateMachine createStateMachine () {
        StateMachineBuilder builder = new StateMachineBuilder(
                TOTAL_AMOUNT_OF_STATES,
                TOTAL_AMOUNT_OF_CHARACTER_CLASSES
        );

        int LETTER = builder.addCharacterClass(new LetterFilter());
        int DIGIT = builder.addCharacterClass(new DigitFilter());
        int UNDERSCORE = builder.addCharacterClass(new SingleCharacterFilter('_'));
        int COLON = builder.addCharacterClass(new SingleCharacterFilter(':'));
        int EQUALS = builder.addCharacterClass(new SingleCharacterFilter('='));
        int LESS_THAN = builder.addCharacterClass(new SingleCharacterFilter('<'));
        int GREATER_THAN = builder.addCharacterClass(new SingleCharacterFilter('>'));
        int HASH = builder.addCharacterClass(new SingleCharacterFilter('#'));
        int ENDLINE = builder.addCharacterClass(new SingleCharacterFilter('\n'));
        int EOF = builder.addCharacterClass(new EOFFilter());
        int OPEN_CURLY_BRACKET = builder.addCharacterClass(new SingleCharacterFilter('{'));
        int CLOSE_CURLY_BRACKET = builder.addCharacterClass(new SingleCharacterFilter('}'));
        int BACKSLASH = builder.addCharacterClass(new SingleCharacterFilter('\\'));
        int WHITESPACE = builder.addCharacterClass(new OrFilter(
                new SingleCharacterFilter(' '),
                new SingleCharacterFilter('\t')
        ));
        int SPECIAL = builder.addCharacterClass(new ASCIIFilter());

        SemanticAction turnOnRecorder = new TurnOnRecorder();
        SemanticAction turnOffRecorder = new TurnOffRecorder();
        SemanticAction forgetLastRecordedCharacter = new ForgetLastRecordedCharacter();

        builder.addTransition(0, LETTER, new StateTransition(1, turnOnRecorder));

        int[] LETTER_DIGIT_UNDERSCORE = { LETTER, DIGIT, UNDERSCORE };
        builder.addTransition(1, LETTER_DIGIT_UNDERSCORE, new StateTransition(1));

        builder.addDefaultTransition(1, new StateTransition(0, new ComposedSemanticAction(
                turnOffRecorder,
                new ComposedSemanticAction(new UngetLastReadCharacter(), new EmitIdentifierOrReservedWord())
        )));

        builder.addTransition(0, SPECIAL, new StateTransition(0, new EmitSpecialCharacter()));

        builder.addTransition(0, COLON, new StateTransition(2));
        builder.addTransition(2, EQUALS, new StateTransition(0, new EmitToken(Parser.ASSIGNMENT)));
        builder.addDefaultTransition(2, new StateTransition(0, new ComposedSemanticAction(
                new ComposedSemanticAction(
                        new EmitWarning("Falta caracter '=' despues de ':'. Asignacion asumida."),
                        new EmitToken(Parser.ASSIGNMENT)
                ),
                new UngetLastReadCharacter()
        )));

        builder.addTransition(0, LESS_THAN, new StateTransition(3));
        builder.addTransition(3, EQUALS, new StateTransition(0, new EmitToken(Parser.LESS_OR_EQUAL)));
        builder.addTransition(3, GREATER_THAN, new StateTransition(0, new EmitToken(Parser.NOT_EQUAL)));
        builder.addDefaultTransition(3, new StateTransition(0, new ComposedSemanticAction(
                new UngetLastReadCharacter(),
                new EmitToken('<')
        )));

        builder.addTransition(0, GREATER_THAN, new StateTransition(4));
        builder.addTransition(4, EQUALS, new StateTransition(0, new EmitToken(Parser.GREATER_OR_EQUAL)));
        builder.addDefaultTransition(4, new StateTransition(0, new ComposedSemanticAction(
                new UngetLastReadCharacter(),
                new EmitToken('>')
        )));

        builder.addTransition(0, EQUALS, new StateTransition(5));
        builder.addTransition(5, EQUALS, new StateTransition(0, new EmitToken(Parser.EQUALS)));
        builder.addDefaultTransition(5, new StateTransition(0, new ComposedSemanticAction(
                new ComposedSemanticAction(
                        new EmitWarning("Falta caracter '=' despues de '='. Operador de igualdad asumido."),
                        new UngetLastReadCharacter()
                ),
                new EmitToken(Parser.EQUALS)
        )));

        builder.addTransition(0, HASH, new StateTransition(6));
        builder.addTransition(6, ENDLINE, new StateTransition(0));
        builder.addTransition(6, EOF, new StateTransition(0, new UngetLastReadCharacter()));
        builder.addDefaultTransition(6, new StateTransition(6));

        builder.addTransition(0, OPEN_CURLY_BRACKET, new StateTransition(7, new ComposedSemanticAction(
                turnOnRecorder,
                forgetLastRecordedCharacter
        )));
        builder.addTransition(7, BACKSLASH, new StateTransition(8, forgetLastRecordedCharacter));
        builder.addDefaultTransition(8, new StateTransition(7));
        builder.addTransition(7, CLOSE_CURLY_BRACKET, new StateTransition(0, new ComposedSemanticAction(
                new ComposedSemanticAction(
                        turnOffRecorder,
                        forgetLastRecordedCharacter
                ),
                new EmitStringConstant()
        )));
        builder.addTransition(7, EOF, new StateTransition(0, new ComposedSemanticAction(
                new ComposedSemanticAction(
                        turnOffRecorder,
                        new UngetLastReadCharacter()
                ),
                new ComposedSemanticAction(
                        new EmitWarning("Cadena multilinea terminada por fin de archivo."),
                        new EmitStringConstant()
                )
        )));
        builder.addDefaultTransition(7, new StateTransition(7));
        builder.addTransition(7, ENDLINE, new StateTransition(7, forgetLastRecordedCharacter));
        builder.addTransition(8, ENDLINE, new StateTransition(7, forgetLastRecordedCharacter));

        builder.addTransition(0, DIGIT, new StateTransition(9, turnOnRecorder));
        builder.addTransition(9, DIGIT, new StateTransition(9));
        builder.addDefaultTransition(9, new StateTransition(0, new ComposedSemanticAction(
                turnOffRecorder,
                new ComposedSemanticAction(
                        new UngetLastReadCharacter(),
                        new EmitNumericConstant()
                )
        )));

        int[] WHITESPACE_ENDLINE = { WHITESPACE, ENDLINE };
        builder.addTransition(0, WHITESPACE_ENDLINE, new StateTransition(0));
        builder.addTransition(0, EOF, new StateTransition(0, new EmitToken(Parser.EOF)));

        builder.addDefaultTransition(0, new StateTransition(0, new EmitInvalidCharacterError()));

        return builder.build();
    }
}
