// Generated from C:/Users/�������������/IdeaProjects/Lab1/src/com/taras/parser\Grammar.g4 by ANTLR 4.9.1
package com.taras.Grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER=1, INT=2, EXPONENT=3, MULTIPLY=4, DIVIDE=5, SUBTRACT=6, ADD=7, 
		LPAREN=8, RPAREN=9, DIV=10, MOD=11, NMAX=12, NMIN=13, COMA=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NUMBER", "INT", "EXPONENT", "MULTIPLY", "DIVIDE", "SUBTRACT", "ADD", 
			"LPAREN", "RPAREN", "DIV", "MOD", "NMAX", "NMIN", "COMA", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'^'", "'*'", "'/'", "'-'", "'+'", "'('", "')'", "'div'", 
			"'mod'", "'nmax'", "'nmin'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NUMBER", "INT", "EXPONENT", "MULTIPLY", "DIVIDE", "SUBTRACT", 
			"ADD", "LPAREN", "RPAREN", "DIV", "MOD", "NMAX", "NMIN", "COMA", "WS"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21Q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\5\2%\n\2"+
		"\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\2\2\21\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\3"+
		"\5\2\13\f\17\17\"\"\2R\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\3!\3\2\2\2\5\'\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\61\3\2"+
		"\2\2\17\63\3\2\2\2\21\65\3\2\2\2\23\67\3\2\2\2\259\3\2\2\2\27=\3\2\2\2"+
		"\31A\3\2\2\2\33F\3\2\2\2\35K\3\2\2\2\37M\3\2\2\2!$\5\5\3\2\"#\7\60\2\2"+
		"#%\5\5\3\2$\"\3\2\2\2$%\3\2\2\2%\4\3\2\2\2&(\4\62;\2\'&\3\2\2\2()\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*\6\3\2\2\2+,\7`\2\2,\b\3\2\2\2-.\7,\2\2.\n\3"+
		"\2\2\2/\60\7\61\2\2\60\f\3\2\2\2\61\62\7/\2\2\62\16\3\2\2\2\63\64\7-\2"+
		"\2\64\20\3\2\2\2\65\66\7*\2\2\66\22\3\2\2\2\678\7+\2\28\24\3\2\2\29:\7"+
		"f\2\2:;\7k\2\2;<\7x\2\2<\26\3\2\2\2=>\7o\2\2>?\7q\2\2?@\7f\2\2@\30\3\2"+
		"\2\2AB\7p\2\2BC\7o\2\2CD\7c\2\2DE\7z\2\2E\32\3\2\2\2FG\7p\2\2GH\7o\2\2"+
		"HI\7k\2\2IJ\7p\2\2J\34\3\2\2\2KL\7.\2\2L\36\3\2\2\2MN\t\2\2\2NO\3\2\2"+
		"\2OP\b\20\2\2P \3\2\2\2\5\2$)\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}