// Generated from src/main/java/com/calclogic/parse/Comb.g4 by ANTLR 4.8

	
package com.calclogic.parse; 

import com.calclogic.lambdacalculo.*;	
import java.util.LinkedList;	
	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CombLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONSTANT_C=1, PHI=2, LAMBDA=3, PERIOD=4, K=5, CB=6, O_PAR=7, C_PAR=8, 
		COMMA=9, C_BRACKET=10, O_BRACKET2=11, C_BRACKET2=12, ASSIGN=13, VARIABLE=14, 
		A=15, I=16, L=17, S=18, U=19, Si=20, WHITESPACE=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DIGITS", "C", "X", "CONSTANT_C", "PHI", "LAMBDA", "PERIOD", "K", "CB", 
			"O_PAR", "C_PAR", "COMMA", "C_BRACKET", "O_BRACKET2", "C_BRACKET2", "ASSIGN", 
			"VARIABLE", "A", "I", "L", "S", "U", "Si", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'\\Phi_{'", "'\\lambda'", "'.'", "'K'", null, "'('", "')'", 
			"','", "'}'", "'['", "']'", "':='", null, "'A^{'", "'I^{'", "'L^{'", 
			"'S^{'", "'U'", "'S'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONSTANT_C", "PHI", "LAMBDA", "PERIOD", "K", "CB", "O_PAR", "C_PAR", 
			"COMMA", "C_BRACKET", "O_BRACKET2", "C_BRACKET2", "ASSIGN", "VARIABLE", 
			"A", "I", "L", "S", "U", "Si", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CombLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Comb.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u008e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\5\2<\n\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\6\31\u0089\n\31\r\31\16\31\u008a"+
		"\3\31\3\31\2\2\32\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21\7\23\b\25\t\27\n\31"+
		"\13\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\3\2\5\3\2\62"+
		";\4\2EEee\4\2ZZzz\2\u008d\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\3;\3\2\2\2\5=\3\2\2\2\7?\3\2\2\2\tA\3\2\2\2\13H\3\2\2\2\rO\3\2\2"+
		"\2\17W\3\2\2\2\21Y\3\2\2\2\23[\3\2\2\2\25]\3\2\2\2\27_\3\2\2\2\31a\3\2"+
		"\2\2\33c\3\2\2\2\35e\3\2\2\2\37g\3\2\2\2!i\3\2\2\2#l\3\2\2\2%s\3\2\2\2"+
		"\'w\3\2\2\2){\3\2\2\2+\177\3\2\2\2-\u0083\3\2\2\2/\u0085\3\2\2\2\61\u0088"+
		"\3\2\2\2\63\67\4\63;\2\64\66\t\2\2\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3"+
		"\2\2\2\678\3\2\2\28<\3\2\2\29\67\3\2\2\2:<\7\62\2\2;\63\3\2\2\2;:\3\2"+
		"\2\2<\4\3\2\2\2=>\t\3\2\2>\6\3\2\2\2?@\t\4\2\2@\b\3\2\2\2AB\5\5\3\2BC"+
		"\7a\2\2CD\7}\2\2DE\3\2\2\2EF\5\3\2\2FG\7\177\2\2G\n\3\2\2\2HI\7^\2\2I"+
		"J\7R\2\2JK\7j\2\2KL\7k\2\2LM\7a\2\2MN\7}\2\2N\f\3\2\2\2OP\7^\2\2PQ\7n"+
		"\2\2QR\7c\2\2RS\7o\2\2ST\7d\2\2TU\7f\2\2UV\7c\2\2V\16\3\2\2\2WX\7\60\2"+
		"\2X\20\3\2\2\2YZ\7M\2\2Z\22\3\2\2\2[\\\4de\2\\\24\3\2\2\2]^\7*\2\2^\26"+
		"\3\2\2\2_`\7+\2\2`\30\3\2\2\2ab\7.\2\2b\32\3\2\2\2cd\7\177\2\2d\34\3\2"+
		"\2\2ef\7]\2\2f\36\3\2\2\2gh\7_\2\2h \3\2\2\2ij\7<\2\2jk\7?\2\2k\"\3\2"+
		"\2\2lm\5\7\4\2mn\7a\2\2no\7}\2\2op\3\2\2\2pq\5\3\2\2qr\7\177\2\2r$\3\2"+
		"\2\2st\7C\2\2tu\7`\2\2uv\7}\2\2v&\3\2\2\2wx\7K\2\2xy\7`\2\2yz\7}\2\2z"+
		"(\3\2\2\2{|\7N\2\2|}\7`\2\2}~\7}\2\2~*\3\2\2\2\177\u0080\7U\2\2\u0080"+
		"\u0081\7`\2\2\u0081\u0082\7}\2\2\u0082,\3\2\2\2\u0083\u0084\7W\2\2\u0084"+
		".\3\2\2\2\u0085\u0086\7U\2\2\u0086\60\3\2\2\2\u0087\u0089\7\"\2\2\u0088"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\31\2\2\u008d\62\3\2\2\2\6\2\67"+
		";\u008a\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}