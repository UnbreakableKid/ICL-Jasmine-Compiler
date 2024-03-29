import Exceptions.TypeError;

public class ASTPrint implements ASTNode {

    private static final String BYTECODE_1 ="getstatic java/lang/System/out Ljava/io/PrintStream;";
    private static final String BYTECODE_2 ="invokevirtual java/io/PrintStream/print(I)V";
    private static final String BYTECODE_3 ="invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";

    ASTNode content;

    public ASTPrint(ASTNode v) {
        content = v;
    }

    public IValue eval(Environment<IValue> env) {
        IValue v = content.eval(env);
        System.out.print(v.stringify());
        return v;
    }

    public void compile(CodeBlock c, Environment e) {
        content.compile(c, e);
        c.emit("dup");
        c.emit(BYTECODE_1);
        c.emit("swap");
        c.emit(BYTECODE_2);
    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType v = content.typeCheck(env);

        if(v instanceof TInt)
            return new TInt();
        if(v instanceof TBool)
            return new TBool();

        throw new TypeError("Illegal type to [print]");
    }

}
