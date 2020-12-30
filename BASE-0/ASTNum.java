public class ASTNum implements ASTNode {

    public static final String BYTECODE = "sipush ";
    int val;

    public ASTNum(int n) {
        val = n;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        return new VInt( val);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        c.emit(BYTECODE + val);
    }
}
