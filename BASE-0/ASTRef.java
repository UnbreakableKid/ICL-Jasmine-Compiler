public class ASTRef implements ASTNode {

    public static final String BYTECODE = "sipush ";
    ASTNode ref;

    public ASTRef(ASTNode v) {
        ref = v;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VRef(ref.eval(env));
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        c.emit("new ref_int");
        c.emit("dup");
        c.emit("invokespecial ref_int/<init>()V");
        c.emit("dup");
        ref.compile(c,e);
        c.emit("putfield ref_int/v I");

    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        return new TRef(ref.typeCheck(env));
    }
}
