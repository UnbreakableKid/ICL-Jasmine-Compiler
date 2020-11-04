public class ASTDiv implements ASTNode {

    ASTNode lhs, rhs;

    public int eval(Environment e) {
        int v1 = lhs.eval(e);

        int v2 = rhs.eval(e);

        return v1 / v2;
    }

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("idiv");
    }
}
