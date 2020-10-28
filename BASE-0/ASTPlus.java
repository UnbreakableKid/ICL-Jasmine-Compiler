public class ASTPlus implements ASTNode {

    ASTNode lhs, rhs;

    public ASTPlus(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public void compile(CodeBlock c) {
        lhs.compile(c);
        rhs.compile(c);
        c.emit("iadd");
    }

    public int eval(Environment e) {
        int v1 = lhs.eval(e);
        int v2 = rhs.eval(e);
        return v1 + v2;
    }
}
