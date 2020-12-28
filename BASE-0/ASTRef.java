public class ASTRef implements ASTNode {

    public static final String BYTECODE = "sipush ";
    ASTNode value;

    public ASTRef(ASTNode v) {
        value = v;
    }

    @Override
    public IValue eval(Environment env) {
        return new VRef(value.eval(env));
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

    }
}
