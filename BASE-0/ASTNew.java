public class ASTNew implements ASTNode {

    private static final String BYTECODE = "iadd";
    ASTNode lhs;

    public ASTNew(ASTNode l) {
        lhs = l;

    }

    public void compile(CodeBlock c, Environment e) {

    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        return new TRef(lhs.typeCheck(env));
    }

//    public int eval(Environment e) {
//        return lhs.eval(e) + rhs.eval(e);
//    }

    public IValue eval(Environment env) {

        return new VRef(lhs.eval(env));

    }
}
