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

    public int eval(Environment e) {
        return lhs.eval(e) + rhs.eval(e);
    }
}
