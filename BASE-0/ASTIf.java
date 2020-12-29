public class ASTIf implements ASTNode {

    public static final String BYTECODE = "imul";
    ASTNode condition, body, elsebody;

    public ASTIf(ASTNode condition, ASTNode body, ASTNode elsebody) {
        this.condition = condition;
        this.body = body;
        this.elsebody = elsebody;
    }

    @Override
    public IValue eval(Environment env) {

        if ( ((VBool) condition.eval(env)).getVal())
            return body.eval(env);
        else
            if(elsebody != null)
                return elsebody.eval(env);
            else return null;
    }
    @Override
    public void compile(CodeBlock c, Environment e) {
        int labels = c.genLabels(3);

        String trueB = "L" + labels;
        String elseB = "L" + labels+1;
        String endB = "L" + labels+2;

        condition.compile(c,e);
        body.compile(c,e);
        c.emit("goto " + endB);
        c.emit(elseB + ":");
        elsebody.compile(c,e);
        c.emit(endB + ":");

    }
}
