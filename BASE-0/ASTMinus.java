public class ASTMinus implements ASTNode {

    ASTNode lhs, rhs;

    public ASTMinus(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public int eval(Environment e) {
        int v1 = lhs.eval(e);
        int v2 = rhs.eval(e);
        return v1 - v2;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("isub");
    }
}
