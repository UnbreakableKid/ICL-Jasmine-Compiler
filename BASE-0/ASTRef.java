public class ASTRef implements ASTNode {

    public static final String BYTECODE = "sipush ";
    ASTNode ref;

    public ASTRef(ASTNode v) {
        ref = v;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VRef(ref.eval(env));
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

    }
}
