public class ASTAssign implements ASTNode {

    ASTNode e1, e2;

    public ASTAssign(ASTNode e1, ASTNode e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = e1.eval(env);
        if (v1 instanceof VRef) {
            IValue v2 = e2.eval(env);
            ((VRef) v1).set(v2);
            return v2;
        }
        throw new Error("assignment	:=	:lhs is not a reference");
    }

    @Override
    public void compile(CodeBlock c, Environment e) {


        e2.compile(c,e);
        c.emit("dup");
        e1.compile(c,e);
        c.emit("checkcast ref_int");

        c.emit("swap");
        c.emit("putfield ref_int/v I");
    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        return new TRef(e2.typeCheck(env));
    }
}
