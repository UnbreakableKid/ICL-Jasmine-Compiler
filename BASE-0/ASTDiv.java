public class ASTDiv implements ASTNode {

    public static final String BYTECODE = "idiv";
    ASTNode lhs, rhs;

    public IValue eval(Environment env) {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(env);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getval() / ((VInt) v2).getval());
            }
            throw new Error(" +:argument is not an integer");
        }
        throw new Error(" +:argument is not an integer");

    }

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }
}
