public class ASTNum implements ASTNode {

    public static final String BYTECODE = "sipush ";
    IValue val;

    public ASTNum(IValue n) {
        val = n;
    }

    @Override
    public IValue eval(Environment e) {
        return ((VInt) val);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        c.emit(BYTECODE + val);

    }
}
