/* The following code was generated by JFlex 1.6.1 */

/*------------ Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package Analizadores.Haskell;
import java_cup.runtime.Symbol;
import java.util.LinkedList;

import Analizadores.Errores;

/*------------ Opciones y Declaraciones ---------*/

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>Haskell_Lexico.jflex</tt>
 */
public class HaskellLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 51, 50, 52, 52, 49,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    51, 25,  3,  0, 26,  0, 21,  4, 31, 32,  9,  7, 34,  8,  2, 10, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 33, 35, 23, 24, 22,  0, 
     0, 43,  5, 37, 13, 38, 48, 46, 47, 41,  5,  5, 39, 11, 42, 12, 
    19, 16, 17, 15, 18, 36, 45,  5, 44,  5,  5, 27,  0, 28,  0,  6, 
     0, 43,  5, 37, 13, 38, 48, 46, 47, 41,  5,  5, 39, 11, 42, 12, 
    19, 16, 17, 15, 18, 36, 45,  5, 44,  5,  5, 29, 20, 30,  0,  0, 
     0,  0,  0,  0,  0, 52,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\1\1\3\1\4\1\5\1\6"+
    "\1\7\2\3\1\1\4\3\2\1\1\10\1\11\1\12"+
    "\1\1\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\3\3\1\1\2\3\1\0\2\25"+
    "\2\0\1\26\4\0\1\27\1\0\3\3\1\0\5\3"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\4\3"+
    "\1\0\1\37\1\3\1\37\1\0\1\3\1\2\1\40"+
    "\3\0\2\41\1\42\1\0\2\3\1\43\1\0\1\43"+
    "\4\3\1\44\1\0\2\3\1\0\1\3\1\45\1\46"+
    "\1\3\1\0\1\3\2\47\3\0\2\50\1\51\2\52"+
    "\1\3\1\53\1\3\2\54\1\3\2\55\1\3\2\56"+
    "\1\57\1\0\1\60\4\3\1\61\2\62\2\3\1\63"+
    "\1\64\1\3\1\65";

  private static int [] zzUnpackAction() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\65\0\152\0\237\0\324\0\u0109\0\u013e\0\65"+
    "\0\65\0\65\0\u0173\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c"+
    "\0\u02b1\0\u02e6\0\u031b\0\u0350\0\u0385\0\u03ba\0\u03ef\0\65"+
    "\0\65\0\65\0\65\0\65\0\65\0\65\0\65\0\65"+
    "\0\65\0\u0424\0\u0459\0\u048e\0\u04c3\0\u04f8\0\u052d\0\u0562"+
    "\0\u0597\0\u05cc\0\u0601\0\237\0\65\0\u0636\0\u066b\0\u06a0"+
    "\0\u06d5\0\65\0\u070a\0\u073f\0\u0774\0\u07a9\0\u07de\0\u0813"+
    "\0\u0848\0\u087d\0\u08b2\0\u08e7\0\65\0\65\0\65\0\65"+
    "\0\65\0\65\0\65\0\u091c\0\u0951\0\u0986\0\u09bb\0\u09f0"+
    "\0\65\0\u0a25\0\u0109\0\u0a5a\0\u0a8f\0\u0601\0\65\0\u0ac4"+
    "\0\u0af9\0\u0b2e\0\65\0\u0109\0\u0109\0\u0b63\0\u0b98\0\u0bcd"+
    "\0\65\0\u0c02\0\u0109\0\u0c37\0\u0c6c\0\u0ca1\0\u0cd6\0\u0109"+
    "\0\u0d0b\0\u0d40\0\u0d75\0\u0daa\0\u0ddf\0\u0109\0\u0109\0\u0e14"+
    "\0\u0e49\0\u0e7e\0\65\0\u0109\0\u0eb3\0\u0ee8\0\u0f1d\0\65"+
    "\0\u0109\0\u0109\0\65\0\u0109\0\u0f52\0\u0109\0\u0f87\0\65"+
    "\0\u0109\0\u0fbc\0\65\0\u0109\0\u0ff1\0\65\0\u0109\0\65"+
    "\0\u1026\0\65\0\u105b\0\u1090\0\u10c5\0\u10fa\0\65\0\65"+
    "\0\u0109\0\u112f\0\u1164\0\u0109\0\u0109\0\u1199\0\u0109";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\2\1\4\1\5\1\6\1\2\1\7"+
    "\1\10\1\11\1\12\1\13\1\6\1\14\1\15\1\16"+
    "\1\6\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\6\1\42\1\43\1\44"+
    "\1\45\1\46\1\6\1\47\5\6\1\50\1\51\1\52"+
    "\67\0\1\3\1\53\62\0\3\54\1\55\61\54\4\56"+
    "\1\0\6\56\1\57\2\56\2\60\3\56\1\61\41\56"+
    "\1\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\4\6\1\0\10\6\13\0\1\62\56\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\5\6\20\0\4\6"+
    "\1\63\1\64\1\6\1\65\5\6\5\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\2\6\1\66"+
    "\1\6\1\0\10\6\50\0\1\67\21\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\1\70\3\6"+
    "\1\0\10\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\5\6\20\0\2\6\1\71\1\6\1\0\10\6"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\4\6\1\0\6\6\1\72\1\6\5\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\2\6\1\73\2\6"+
    "\20\0\4\6\1\0\2\6\1\74\5\6\30\0\1\75"+
    "\65\0\1\76\67\0\1\77\64\0\1\100\64\0\1\101"+
    "\64\0\1\102\1\103\34\0\1\6\3\0\2\6\4\0"+
    "\3\6\1\0\5\6\20\0\4\6\1\0\2\6\1\104"+
    "\5\6\5\0\1\6\3\0\2\6\4\0\3\6\1\0"+
    "\5\6\20\0\3\6\1\105\1\0\1\6\1\106\6\6"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\2\6\1\107\1\6\1\0\10\6\17\0\1\110"+
    "\44\0\1\111\5\0\1\6\3\0\2\6\4\0\1\112"+
    "\2\6\1\0\5\6\20\0\4\6\1\0\7\6\1\113"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\114\1\115"+
    "\4\6\20\0\4\6\1\0\10\6\66\0\1\51\63\0"+
    "\1\50\1\51\65\0\1\52\2\0\1\116\67\0\1\117"+
    "\64\0\1\117\7\0\1\120\54\0\1\117\13\0\1\121"+
    "\50\0\1\117\7\0\1\122\122\0\1\123\13\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\5\6\20\0\4\6"+
    "\1\0\1\6\1\124\6\6\5\0\1\6\3\0\2\6"+
    "\4\0\3\6\1\0\5\6\20\0\4\6\1\0\3\6"+
    "\1\125\4\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\126\1\127\4\6\20\0\1\6\1\130\2\6\1\0"+
    "\10\6\17\0\1\131\31\0\1\132\20\0\1\6\3\0"+
    "\2\6\4\0\1\133\2\6\1\0\5\6\20\0\1\6"+
    "\1\134\2\6\1\0\10\6\5\0\1\6\3\0\2\6"+
    "\4\0\3\6\1\0\5\6\20\0\4\6\1\0\4\6"+
    "\1\135\3\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\5\6\20\0\2\6\1\136\1\6\1\0\10\6"+
    "\5\0\1\6\3\0\2\6\4\0\1\6\1\137\1\6"+
    "\1\0\5\6\20\0\4\6\1\0\10\6\5\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\2\6\1\140\2\6"+
    "\20\0\4\6\1\0\10\6\5\0\1\6\3\0\2\6"+
    "\4\0\3\6\1\141\1\142\4\6\20\0\3\6\1\143"+
    "\1\0\10\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\144\1\145\4\6\20\0\4\6\1\0\10\6\5\0"+
    "\1\6\3\0\2\6\4\0\2\6\1\146\1\0\5\6"+
    "\20\0\4\6\1\0\10\6\5\0\1\6\3\0\2\6"+
    "\4\0\3\6\1\0\3\6\1\147\1\6\20\0\4\6"+
    "\1\0\1\6\1\150\6\6\27\0\1\151\42\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\4\6\1\152\20\0"+
    "\4\6\1\0\10\6\51\0\1\153\20\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\1\6\1\154"+
    "\2\6\1\0\10\6\21\0\1\155\70\0\1\156\65\0"+
    "\1\157\107\0\1\160\20\0\1\6\3\0\2\6\4\0"+
    "\3\6\1\0\5\6\20\0\1\6\1\161\2\6\1\0"+
    "\10\6\5\0\1\6\3\0\2\6\4\0\3\6\1\0"+
    "\5\6\20\0\1\6\1\162\2\6\1\0\10\6\51\0"+
    "\1\163\20\0\1\6\3\0\2\6\4\0\3\6\1\0"+
    "\5\6\20\0\1\6\1\164\2\6\1\0\10\6\5\0"+
    "\1\6\3\0\2\6\4\0\3\6\1\0\5\6\20\0"+
    "\2\6\1\165\1\6\1\0\10\6\5\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\4\6\1\0"+
    "\1\6\1\166\6\6\5\0\1\6\3\0\2\6\4\0"+
    "\2\6\1\167\1\0\5\6\20\0\4\6\1\0\10\6"+
    "\52\0\1\170\17\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\5\6\20\0\2\6\1\171\1\6\1\0\10\6"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\1\6\1\172\2\6\1\0\10\6\52\0\1\173"+
    "\17\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\2\6\1\174\1\6\1\0\10\6\5\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\5\6\20\0\4\6"+
    "\1\0\5\6\1\175\2\6\25\0\1\176\44\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\2\6\1\177\2\6"+
    "\20\0\4\6\1\0\10\6\10\0\1\200\102\0\1\201"+
    "\46\0\1\202\61\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\2\6\1\203\2\6\20\0\4\6\1\0\10\6"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\0\5\6"+
    "\20\0\1\204\3\6\1\0\10\6\5\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\1\205\3\6"+
    "\1\0\10\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\3\6\1\206\1\6\20\0\4\6\1\0\10\6"+
    "\10\0\1\207\61\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\210\1\211\4\6\20\0\4\6\1\0\10\6\5\0"+
    "\1\6\3\0\2\6\4\0\3\6\1\0\5\6\20\0"+
    "\1\6\1\212\2\6\1\0\10\6\5\0\1\6\3\0"+
    "\2\6\4\0\3\6\1\0\5\6\20\0\3\6\1\213"+
    "\1\0\10\6\5\0\1\6\3\0\2\6\4\0\3\6"+
    "\1\0\5\6\20\0\4\6\1\0\6\6\1\214\1\6"+
    "\5\0\1\6\3\0\2\6\4\0\3\6\1\0\3\6"+
    "\1\215\1\6\20\0\4\6\1\0\10\6\5\0\1\6"+
    "\3\0\2\6\4\0\3\6\1\0\5\6\20\0\4\6"+
    "\1\0\2\6\1\216\5\6\5\0\1\6\3\0\2\6"+
    "\4\0\3\6\1\0\2\6\1\217\2\6\20\0\4\6"+
    "\1\0\10\6\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4558];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\5\1\3\11\15\1\12\11\6\1\1\0"+
    "\2\1\2\0\1\11\4\0\1\11\1\0\3\1\1\0"+
    "\5\1\7\11\4\1\1\0\1\11\2\1\1\0\2\1"+
    "\1\11\3\0\1\11\2\1\1\0\2\1\1\11\1\0"+
    "\6\1\1\0\2\1\1\0\4\1\1\0\1\1\1\11"+
    "\1\1\3\0\1\11\2\1\1\11\4\1\1\11\2\1"+
    "\1\11\2\1\1\11\1\1\1\11\1\0\1\11\4\1"+
    "\2\11\7\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    //----> Codigo de usuario en sintaxis java
    public static LinkedList<Analizadores.Errores> Err = new LinkedList<Analizadores.Errores>();


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public HaskellLexico(java.io.Reader in) {
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(SimbolosHaskell.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.out.println("ErrorLexico: "+yytext()+"Linea: "+yyline+" Columna: "+yycolumn);
                                Analizadores.Errores err = new Analizadores.Errores("Lexico: ","No es parte del lenguaje ",yytext(),yyline,yycolumn);
                                Err.add(err);

                                Errores.ErrorSemantico("Lexico: No es parte del Lenguaje Haskell -" +yytext()+ "- ", 0, 0);
            }
          case 54: break;
          case 2: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.entero, yyline, yycolumn, yytext());
            }
          case 55: break;
          case 3: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.id, yyline, yycolumn, yytext());
            }
          case 56: break;
          case 4: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mas, yyline, yycolumn, yytext());
            }
          case 57: break;
          case 5: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menos, yyline, yycolumn, yytext());
            }
          case 58: break;
          case 6: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.por, yyline, yycolumn, yytext());
            }
          case 59: break;
          case 7: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.div, yyline, yycolumn, yytext());
            }
          case 60: break;
          case 8: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mayorq, yyline, yycolumn, yytext());
            }
          case 61: break;
          case 9: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menorq, yyline, yycolumn, yytext());
            }
          case 62: break;
          case 10: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.igual, yyline, yycolumn, yytext());
            }
          case 63: break;
          case 11: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.dolar, yyline, yycolumn, yytext());
            }
          case 64: break;
          case 12: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.corAbre, yyline, yycolumn, yytext());
            }
          case 65: break;
          case 13: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.corCierra, yyline, yycolumn, yytext());
            }
          case 66: break;
          case 14: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.llaveAbre, yyline, yycolumn, yytext());
            }
          case 67: break;
          case 15: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.llaveCierra, yyline, yycolumn, yytext());
            }
          case 68: break;
          case 16: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.parenAbre, yyline, yycolumn, yytext());
            }
          case 69: break;
          case 17: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.parenCierra, yyline, yycolumn, yytext());
            }
          case 70: break;
          case 18: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.dospuntos, yyline, yycolumn, yytext());
            }
          case 71: break;
          case 19: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.coma, yyline, yycolumn, yytext());
            }
          case 72: break;
          case 20: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.puntoycoma, yyline, yycolumn, yytext());
            }
          case 73: break;
          case 21: 
            { 
            }
          case 74: break;
          case 22: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.cadena, yyline, yycolumn, yytext());
            }
          case 75: break;
          case 23: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.concatena, yyline, yycolumn, yytext());
            }
          case 76: break;
          case 24: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.or, yyline, yycolumn, yytext());
            }
          case 77: break;
          case 25: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.and, yyline, yycolumn, yytext());
            }
          case 78: break;
          case 26: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mayorigualq, yyline, yycolumn, yytext());
            }
          case 79: break;
          case 27: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menorigualq, yyline, yycolumn, yytext());
            }
          case 80: break;
          case 28: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.igualigual, yyline, yycolumn, yytext());
            }
          case 81: break;
          case 29: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.diferente, yyline, yycolumn, yytext());
            }
          case 82: break;
          case 30: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.indice, yyline, yycolumn, yytext());
            }
          case 83: break;
          case 31: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.si, yyline, yycolumn, yytext());
            }
          case 84: break;
          case 32: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.caracter, yyline, yycolumn, yytext());
            }
          case 85: break;
          case 33: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.min, yyline, yycolumn, yytext());
            }
          case 86: break;
          case 34: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.max, yyline, yycolumn, yytext());
            }
          case 87: break;
          case 35: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sum, yyline, yycolumn, yytext());
            }
          case 88: break;
          case 36: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.par, yyline, yycolumn, yytext());
            }
          case 89: break;
          case 37: 
            { //System.out.println("Reconocio: end");  
                                   return new Symbol(SimbolosHaskell.fin, yyline, yycolumn, yytext());
            }
          case 90: break;
          case 38: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.let, yyline, yycolumn, yytext());
            }
          case 91: break;
          case 39: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.asc, yyline, yycolumn, yytext());
            }
          case 92: break;
          case 40: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.desc, yyline, yycolumn, yytext());
            }
          case 93: break;
          case 41: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.decc, yyline, yycolumn, yytext());
            }
          case 94: break;
          case 42: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.succ, yyline, yycolumn, yytext());
            }
          case 95: break;
          case 43: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.entonces, yyline, yycolumn, yytext());
            }
          case 96: break;
          case 44: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.caso, yyline, yycolumn, yytext());
            }
          case 97: break;
          case 45: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sino, yyline, yycolumn, yytext());
            }
          case 98: break;
          case 46: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.impr, yyline, yycolumn, yytext());
            }
          case 99: break;
          case 47: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.residuo, yyline, yycolumn, yytext());
            }
          case 100: break;
          case 48: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.pot, yyline, yycolumn, yytext());
            }
          case 101: break;
          case 49: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sqrt, yyline, yycolumn, yytext());
            }
          case 102: break;
          case 50: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.revers, yyline, yycolumn, yytext());
            }
          case 103: break;
          case 51: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.tam, yyline, yycolumn, yytext());
            }
          case 104: break;
          case 52: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.product, yyline, yycolumn, yytext());
            }
          case 105: break;
          case 53: 
            { //System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.calcular, yyline, yycolumn, yytext());
            }
          case 106: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
