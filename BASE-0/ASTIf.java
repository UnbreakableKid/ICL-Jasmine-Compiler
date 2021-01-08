import Exceptions.TypeError;

public class ASTIf implements ASTNode {

    public static final String BYTECODE = "imul";
    ASTNode condition, body, elsebody;

    public ASTIf(ASTNode condition, ASTNode body, ASTNode elsebody) {
        this.condition = condition;
        this.body = body;
        this.elsebody = elsebody;
    }

    @Override
    public IValue eval(Environment<IValue> env) {

        if ( ((VBool) condition.eval(env)).getVal())
            return body.eval(env);
        else if(elsebody != null)
            return elsebody.eval(env);
        return null;
    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        int labels = c.genLabels(3);

        String trueB = "L" + labels+1;
        String endB = "L" + labels+2;

        condition.compile(c,e);
        c.emit("ifgt " + trueB);

        elsebody.compile(c,e);

        c.emit("goto " + endB);
        c.emit(trueB + ":");

        body.compile(c,e);

        c.emit(endB + ":");

    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType t1 = condition.typeCheck(env);

        if (t1 instanceof TBool){
            IType tt = body.typeCheck(env);
            if(elsebody==null)
                return tt;

            IType te = elsebody.typeCheck(env);
            if(tt.getType().compareTo(te.getType()) == 0)
                return te;

            throw new TypeError("if: mismatch in then else	branch types");
        }
        throw new TypeError("if: condition	is non boolean");
    }
}
