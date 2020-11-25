public class ASTNum implements ASTNode {

    public static final String BYTECODE = "sipush ";
    int val;

    public ASTNum(int n) {
        val = n;
    }

    @Override
    public int eval(Environment e) {
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        c.emit(BYTECODE + val);

    }
}
