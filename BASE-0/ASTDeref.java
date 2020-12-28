public class ASTDeref implements ASTNode {


    ASTNode e1;

    public ASTDeref(ASTNode v1) {
        e1 = v1;
    }

    @Override
    public IValue eval(Environment env) {
        IValue v1 = e1.eval(env);
        return ((VRef) v1).get();
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

    }
}
