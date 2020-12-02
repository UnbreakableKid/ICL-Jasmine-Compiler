public class ASTMul implements ASTNode {

    public static final String BYTECODE = "imul";
    ASTNode lhs, rhs;

    public ASTMul(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment env) {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(env);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getval() * ((VInt) v2).getval());
            }
            throw new Error(" +:argument is not an integer");
        }
        throw new Error(" +:argument is not an integer");

    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }
}
