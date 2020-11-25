public class ASTMul implements ASTNode {

    public static final String BYTECODE = "imul";
    ASTNode lhs, rhs;

    public ASTMul(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public int eval(Environment e) {
        return lhs.eval(e) * rhs.eval(e);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }
}
