public class ASTPrintln implements ASTNode {

    private static final String BYTECODE_1 ="getstatic java/lang/System/out Ljava/io/PrintStream;";
    private static final String BYTECODE_2 ="invokestatic java/lang/String/valueOf(I)Ljava/lang/String;";
    private static final String BYTECODE_3 ="invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";

    ASTNode content;

    public ASTPrintln(ASTNode v) {
        content = v;
    }

    public IValue eval(Environment<IValue> env) {
        IValue v = null;
        if(content!=null) {
            v = content.eval(env);
            System.out.println(v.stringify());
        }
        else System.out.println();
        return v;
    }

    public void compile(CodeBlock c, Environment e) {
        c.emit(BYTECODE_1);
        content.compile(c, e);
        c.emit(BYTECODE_2);
        c.emit(BYTECODE_3);
    }

}
