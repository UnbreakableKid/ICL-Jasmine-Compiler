public class ASTDiv implements ASTNode {

    public static final String BYTECODE = "idiv";
    ASTNode lhs, rhs;

    public int eval(Environment e) {
        return lhs.eval(e) / rhs.eval(e);
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
