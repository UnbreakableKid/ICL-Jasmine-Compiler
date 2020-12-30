/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/** ID lister. */
public class Parser implements ParserConstants {

  static final String DEFAULT_FOLDER = "BASE-0/files/%s.txt";

  /** Main entry point. */
  public static void main(String []args) {

        String filename = null;

        switch(args.length){
        case 0: break;
        case 1:
            System.out.println("Wrong arguments: "+args[0]+".\nTry '-c File_Name' to compile or just run to interpreter.");
            System.exit(1);
        case 2:
            if(!args[0].equalsIgnoreCase("-c") ){
                System.out.println("Flag '-c' expected to compile, but "+args[0]+" received");
                System.exit(1);
            }
            filename = String.format(DEFAULT_FOLDER,args[1]);
            break;
        default:
            System.out.println("Wrong arguments.\nTry '-c File_Name' to compile or just run to interpreter.");
            System.exit(1);
        }

        Parser parser;

        if(filename != null){
            try{
                parser = new Parser(new FileInputStream(filename));
            }
            catch(FileNotFoundException e){
                System.out.println(e.getMessage());
                System.exit(1);
                return;
            }
            compiler(parser,args[1]);
        }
        else{
            parser = new Parser(System.in);
            interpreter(parser);
        }
  }

  private static void compiler(Parser parser, String filename){
        Environment<Integer> env = new Environment<>();
        ASTNode exp;
        CodeBlock codeBlock = new CodeBlock();
        try{
            exp = parser.Start();
            exp.compile(codeBlock, env);
            codeBlock.dump(filename);
            //codeBlock.code.clear();
        }catch(Exception e) {System.out.println("Syntax Error!");}
  }

  private static void interpreter(Parser parser){
        Environment env = new Environment<IValue>();
        ASTNode exp;
        System.out.println("\nInterpreter: ");
        while(true){
            try{
                System.out.print("> ");

                exp = parser.Start();

                if(exp == null){
                    System.out.println("Shutting down...");
                    System.exit(1);
                }
                   try {
                    VInt x = (VInt) exp.eval(env);

                    System.out.println(x.getVal());
                    }catch (Exception e){
                        System.out.println(e);
                    }
            } catch(Exception e){
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
  }

  static final public ASTNode Start() throws ParseException {ASTNode t, t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXIT:{
      jj_consume_token(EXIT);
{if ("" != null) return null;}
      break;
      }
    case Def:
    case NEW:
    case IF:
    case WHILE:
    case BOOL:
    case DEREF:
    case Id:
    case Num:
    case MINUS:
    case NOT:
    case LPAR:{
      t = Seq();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 0:{
        jj_consume_token(0);
        break;
        }
      case EL:{
        jj_consume_token(EL);
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return t;}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Seq() throws ParseException {ASTNode t, t1;
    t = Exp();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMICOLON:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMICOLON);
      t1 = Exp();
t = new ASTSeq(t, t1);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FINAL:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(FINAL);
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Exp() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = EA();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQUALS:
    case ASSIGN:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUALS:{
        jj_consume_token(EQUALS);
        t2 = EA();
t1 = new ASTEq(t1, t2);
        break;
        }
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        t2 = EA();
t1 = new ASTAssign(t1, t2);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode EA() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == PLUS)
                         t1 = new ASTPlus(t1,t2);
                    else
                         t1 = new ASTMinus(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Term() throws ParseException {Token op;
 ASTNode t1, t2;
    t1 = Fact();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:
      case DIV:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        op = jj_consume_token(TIMES);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Fact();
if (op.kind == TIMES)
                  t1 = new ASTMul(t1,t2);
              else
                  t1 = new ASTDiv(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Comp() throws ParseException {ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OR:
    case AND:
    case EQ:
    case LEQ:
    case BEQ:
    case LT:
    case BT:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:{
        jj_consume_token(EQ);
        t2 = Exp();
t1 = new ASTEq(t1,t2);
        break;
        }
      case LT:{
        jj_consume_token(LT);
        t2 = Exp();
t1 = new ASTLess(t1,t2);
        break;
        }
      case BT:{
        jj_consume_token(BT);
        t2 = Exp();
t1 = new ASTBigger(t1,t2);
        break;
        }
      case LEQ:{
        jj_consume_token(LEQ);
        t2 = Exp();
t1 = new ASTLessOrEqual(t1,t2);
        break;
        }
      case BEQ:{
        jj_consume_token(BEQ);
        t2 = Exp();
t1 = new ASTBiggerOrEqual(t1,t2);
        break;
        }
      case AND:{
        jj_consume_token(AND);
        t2 = Exp();
t1 = new ASTAnd(t1,t2);
        break;
        }
      case OR:{
        jj_consume_token(OR);
        t2 = Exp();
t1 = new ASTOr(t1,t2);
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token n, m;
  ASTNode t, t1, t2;
  Map<String, ASTNode> vars = new HashMap<>();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MINUS:{
      jj_consume_token(MINUS);
      n = jj_consume_token(Num);
VInt z = new VInt(Integer.parseInt(n.image));
                z.v = -z.v;
                t = new ASTNum(z.getVal());
      break;
      }
    case Num:{
      n = jj_consume_token(Num);
VInt z = new VInt(Integer.parseInt(n.image));
                t = new ASTNum(z.getVal());
      break;
      }
    case Id:{
      n = jj_consume_token(Id);
t = new ASTId(n.image);
      break;
      }
    case BOOL:{
      n = jj_consume_token(BOOL);
t = new ASTBool(Boolean.parseBoolean(n.image));
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      t1 = Fact();
t = new ASTNot(t1);
      break;
      }
    case IF:{
      jj_consume_token(IF);
      t = Comp();
      jj_consume_token(THEN);
      t1 = Exp();
      jj_consume_token(ELSE);
      t2 = Exp();
      jj_consume_token(End);
t = new ASTIf(t,t1,t2);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      t1 = Comp();
      jj_consume_token(DO);
      t2 = Exp();
      jj_consume_token(End);
t = new ASTWhile(t1, t2);
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      t1 = Fact();
t = new ASTRef(t1);
      break;
      }
    case DEREF:{
      jj_consume_token(DEREF);
      t1 = Fact();
t = new ASTDeref(t1);
      break;
      }
    case Def:{
      jj_consume_token(Def);
      label_5:
      while (true) {
        n = jj_consume_token(Id);
        jj_consume_token(EQ);
        t1 = Exp();
vars.put(n.image, t1);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case Id:{
          ;
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          break label_5;
        }
      }
      jj_consume_token(In);
      t2 = Seq();
      jj_consume_token(End);
t = new ASTDef(vars, t2);
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = Exp();
      jj_consume_token(RPAR);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x20000001,0xa2f1390,0x0,0x0,0x80000000,0x80000000,0x300000,0x300000,0x4400000,0x4400000,0x41800000,0x41800000,0x40000,0xa2f1310,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x2,0x80,0x1,0x1,0x0,0x0,0x0,0x0,0x3c,0x3c,0x0,0x0,};
	}

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[40];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 14; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 40; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
