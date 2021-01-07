/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** ID lister. */
public class Parser implements ParserConstants {

  static final String DEFAULT_FOLDER = "BASE-0/files/%s.txt";

  /** Main entry point. */
  public static void main(String []args) throws IOException {

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

  private static void compiler(Parser parser, String filename) throws IOException {
        Environment<Integer> env = new Environment<>();
        ASTNode exp;
        CodeBlock codeBlock = new CodeBlock();
        BufferedWriter out;

        out = new BufferedWriter(new FileWriter(CodeBlock.DEFAULT_FOLDER + "ref_int.j"));
        out.write(".class ref_int\n");
        out.write(".super java/lang/Object\n");
        out.write(".field public v I;\n");
        out.write(".method public <init>()V\n");
                out.write("\taload_0\n");
                out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
                out.write("\treturn\n");
        out.write(".end method \n");
        out.flush();
        out.close();
        try{
            exp = parser.Start();
            exp.compile(codeBlock, env);
            codeBlock.dump(filename);
            //codeBlock.code.clear();
        }catch(Exception e) {System.out.println("Syntax Error!");}
  }

  private static void interpreter(Parser parser){
        Environment<IValue> env = new Environment<>();
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
                    IValue x = exp.eval(env);
                    if(x != null)
                        System.out.println(x.stringify());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
            } catch(Exception e){
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
  }

  static final public ASTNode Start() throws ParseException {ASTNode t;
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
    case PRINT:
    case PRINTLN:
    case BOOLEAN:
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

  static final public ASTNode Exp() throws ParseException {ASTNode t1, t2;
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
      if (jj_2_1(2)) {
        ;
      } else {
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
        jj_la1[6] = jj_gen;
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
      if (jj_2_2(2)) {
        ;
      } else {
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
      case REST:{
        op = jj_consume_token(REST);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Fact();
if (op.kind == TIMES)
                  t1 = new ASTMul(t1,t2);
              else if(op.kind == DIV)
                  t1 = new ASTDiv(t1,t2);
              else t1 = new ASTRest(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Comp() throws ParseException {ASTNode t1, t2;
    t1 = Exp();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:
      case AND:
      case EQ:
      case LEQ:
      case BEQ:
      case LT:
      case BT:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
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
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token n, type;
  IType ty;
  ASTNode t, t1, t2, elset = null;
  Map<String, ASTNode> vars = new HashMap<>();
    Map<String, IType> types = new HashMap<>();
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
    case BOOLEAN:{
      n = jj_consume_token(BOOLEAN);
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
      t1 = Seq();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ELSE:{
        jj_consume_token(ELSE);
        t2 = Seq();
elset = t2;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(End);
t = new ASTIf(t,t1,elset);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      t1 = Comp();
      jj_consume_token(DO);
      t2 = Seq();
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
    case PRINTLN:{
      jj_consume_token(PRINTLN);
      if (jj_2_3(2)) {
        t1 = EA();
elset = t1;
      } else {
        ;
      }
t = new ASTPrintln(elset);
      break;
      }
    case PRINT:{
      jj_consume_token(PRINT);
      t1 = EA();
t = new ASTPrint(t1);
      break;
      }
    case Def:{
      jj_consume_token(Def);
      label_6:
      while (true) {
        n = jj_consume_token(Id);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COLON:{
          jj_consume_token(COLON);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case INT:
          case BOOL:
          case REF:{
            ty = TYP();
types.put(n.image, ty);
            break;
            }
          default:
            jj_la1[11] = jj_gen;
            ;
          }
          jj_consume_token(EQ);
          t1 = Exp();
vars.put(n.image, t1);
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case Id:{
          ;
          break;
          }
        default:
          jj_la1[13] = jj_gen;
          break label_6;
        }
      }
      jj_consume_token(In);
      t2 = Seq();
      jj_consume_token(End);
t = new ASTDef(vars, types , t2);
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = Exp();
      jj_consume_token(RPAR);
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public IType TYP() throws ParseException {Token n;
  IType t, t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      n = jj_consume_token(INT);
t = new TInt();
      break;
      }
    case BOOL:{
      n = jj_consume_token(BOOL);
t = new TBool();
      break;
      }
    case REF:{
      n = jj_consume_token(REF);
      t1 = TYP();
t = new TRef(t1);
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_3R_Exp_228_6_25()
 {
    if (jj_3R_EA_239_4_9()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_314_11_12()
 {
    if (jj_scan_token(Id)) return true;
    return false;
  }

  static private boolean jj_3R_Comp_274_3_23()
 {
    if (jj_3R_Exp_228_6_25()) return true;
    return false;
  }

  static private boolean jj_3_2()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(25)) {
    jj_scanpos = xsp;
    if (jj_scan_token(30)) {
    jj_scanpos = xsp;
    if (jj_scan_token(28)) return true;
    }
    }
    if (jj_3R_Fact_299_9_8()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_350_17_24()
 {
    if (jj_scan_token(Id)) return true;
    return false;
  }

  static private boolean jj_3R_Fact_309_11_11()
 {
    if (jj_scan_token(Num)) return true;
    return false;
  }

  static private boolean jj_3R_Fact_354_11_22()
 {
    if (jj_scan_token(LPAR)) return true;
    if (jj_3R_Exp_228_6_25()) return true;
    return false;
  }

  static private boolean jj_3_3()
 {
    if (jj_3R_EA_239_4_9()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_350_10_21()
 {
    if (jj_scan_token(Def)) return true;
    Token xsp;
    if (jj_3R_Fact_350_17_24()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_Fact_350_17_24()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_Fact_347_11_20()
 {
    if (jj_scan_token(PRINT)) return true;
    if (jj_3R_EA_239_4_9()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_301_9_10()
 {
    if (jj_scan_token(MINUS)) return true;
    if (jj_scan_token(Num)) return true;
    return false;
  }

  static private boolean jj_3R_Term_255_6_7()
 {
    if (jj_3R_Fact_299_9_8()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_2()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_Fact_299_9_8()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_Fact_301_9_10()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_309_11_11()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_314_11_12()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_319_10_13()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_321_10_14()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_324_9_15()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_329_9_16()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_333_9_17()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_337_9_18()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_342_11_19()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_347_11_20()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_350_10_21()) {
    jj_scanpos = xsp;
    if (jj_3R_Fact_354_11_22()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_Fact_342_11_19()
 {
    if (jj_scan_token(PRINTLN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_1()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(23)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    if (jj_3R_Term_255_6_7()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_337_9_18()
 {
    if (jj_scan_token(DEREF)) return true;
    if (jj_3R_Fact_299_9_8()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_333_9_17()
 {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_Fact_299_9_8()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_329_9_16()
 {
    if (jj_scan_token(WHILE)) return true;
    if (jj_3R_Comp_274_3_23()) return true;
    return false;
  }

  static private boolean jj_3R_EA_239_4_9()
 {
    if (jj_3R_Term_255_6_7()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_1()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_Fact_324_9_15()
 {
    if (jj_scan_token(IF)) return true;
    if (jj_3R_Comp_274_3_23()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_321_10_14()
 {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_Fact_299_9_8()) return true;
    return false;
  }

  static private boolean jj_3R_Fact_319_10_13()
 {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
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
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x1,0xa17e9c80,0x0,0x0,0x0,0x0,0x1800000,0x52000000,0xc000000,0xc000000,0x2000,0x70,0x0,0x200000,0xa17e9880,0x70,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x2,0x0,0x20,0x800,0x18,0x18,0x0,0x0,0x3c4,0x3c4,0x0,0x0,0x400,0x0,0x0,0x0,};
	}
  static final private JJCalls[] jj_2_rtns = new JJCalls[3];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

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
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
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
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[44];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 16; i++) {
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
	 for (int i = 0; i < 44; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
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

  static private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 3; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
