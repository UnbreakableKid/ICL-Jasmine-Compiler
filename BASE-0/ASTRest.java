import Exceptions.TypeError;

public class ASTRest implements ASTNode {

    public static final String BYTECODE = "idiv";
    ASTNode lhs, rhs;


    public ASTRest(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(env);
            if (v2 instanceof VInt) {
                if(((VInt) v2).getVal()==0)
                    throw new Error("Division by zero!");
                return new VInt(((VInt) v1).getVal() % ((VInt) v2).getVal());
            }
            throw new Error(" %:argument is not an integer");
        }
        throw new Error(" %:argument is not an integer");

    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType left = lhs.typeCheck(env);
        IType right = rhs.typeCheck(env);

        if(left instanceof TInt && right instanceof TInt)
            return new TInt();

        throw new TypeError("%: argument type is not an integer");
    }
}
