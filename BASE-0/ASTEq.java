public class ASTEq implements ASTNode {

    public static final String BYTECODE = "imul";
    ASTNode lhs, rhs;

    public ASTEq(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment env) {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        if (v1 instanceof VInt && v2 instanceof VInt){
            return new VBool(((VInt) v1).getval() == ((VInt) v2).getval());
        }

        if (v1 instanceof VBool && v2 instanceof VBool){
            return new VBool(((VBool) v1).getval() == ((VBool) v2).getval());
        }
        return new VBool(false);
    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }
}
