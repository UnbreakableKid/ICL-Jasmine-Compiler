import Exceptions.TypeError;

public class ASTDiv implements ASTNode {

    public static final String BYTECODE = "idiv";
    ASTNode lhs, rhs;


    public ASTDiv(ASTNode l, ASTNode r) {
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
                return new VInt(((VInt) v1).getVal() / ((VInt) v2).getVal());
            }
            throw new Error(" /:argument is not an integer");
        }
        throw new Error(" /:argument is not an integer");

    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit(BYTECODE);
    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType t1 = lhs.typeCheck(env);

        if(t1 instanceof TInt){
            IType v2 = rhs.typeCheck(env);
            if(v2 instanceof TInt)
                return new TInt();
        }
        throw new TypeError("/: argument is not an integer");
    }
}
