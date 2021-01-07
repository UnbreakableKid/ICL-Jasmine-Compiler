import Exceptions.TypeError;

public class ASTEq implements ASTNode {

    public static final String BYTECODE = "if_icmpeq";
    ASTNode lhs, rhs;

    public ASTEq(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        if (v1 instanceof VInt && v2 instanceof VInt){
            return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());
        }
        if (v1 instanceof VBool && v2 instanceof VBool){
            return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());
        }
        throw new TypeError("TypeError: Illegal arguments with relational operators...");
    }
    public void compile(CodeBlock c, Environment e) {

        lhs.compile(c, e);

        rhs.compile(c, e);

        c.emitNoEnter(BYTECODE);

        int x = c.genLabels(1);

        c.emit("TRUE" + x);
        c.emit("sipush 0");
        c.emit("goto exit" + x);

        c.emit("TRUE" +(x) + ":");
        c.emit("sipush 1");

        c.emit("exit" + x+ ":");

    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType left = lhs.typeCheck(env);
        IType right = rhs.typeCheck(env);

        if(left instanceof TInt && right instanceof TInt)
            return new TInt();
        if(left instanceof TBool && right instanceof TBool)
            return new TBool();

        throw new TypeError("==: argument type is not matching");
    }
}
