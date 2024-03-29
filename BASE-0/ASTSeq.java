public class ASTSeq implements ASTNode {


    ASTNode exp1, exp2;

    public ASTSeq(ASTNode e1, ASTNode e2) {
        this.exp1 = e1;
        this.exp2 = e2;
    }
    @Override
    public IValue eval(Environment env) {
        exp1.eval(env);
        return exp2.eval(env);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        exp1.compile(c,e);
        c.emit("pop");
        exp2.compile(c,e);

    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        exp1.typeCheck(env);
        return exp2.typeCheck(env);
    }
}
