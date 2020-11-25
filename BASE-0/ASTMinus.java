public class ASTMinus implements ASTNode {

    public static final String BYTECODE = "isub";
    ASTNode lhs, rhs;

    public ASTMinus(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public int eval(Environment e) {
        return lhs.eval(e) - rhs.eval(e);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }
}
