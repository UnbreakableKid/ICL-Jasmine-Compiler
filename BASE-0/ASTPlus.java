public class ASTPlus implements ASTNode {

    private static final String BYTECODE = "iadd";
    ASTNode lhs, rhs;

    public ASTPlus(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }

//    public int eval(Environment e) {
//        return lhs.eval(e) + rhs.eval(e);
//    }

    public IValue eval(Environment env) {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(env);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getval() + ((VInt) v2).getval());
            }
            throw new Error(" +:argument is not an integer");
        }
        throw new Error(" +:argument is not an integer");

    }
}
