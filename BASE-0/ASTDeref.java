import Exceptions.TypeError;

public class ASTDeref implements ASTNode {


    ASTNode v;

    public ASTDeref(ASTNode v) {
        this.v = v;
    }

    @Override
    public IValue eval(Environment env) {
        IValue v1 = v.eval(env);
        if(v1 instanceof VRef)
            return ((VRef) v1).get();
        else
            throw new TypeError(String.format("TypeError: %s is not a reference!", v1.getClass().getName()));
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

    }
}
