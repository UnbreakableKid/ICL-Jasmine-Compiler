import java.util.Map;

class ASTDef implements ASTNode {
    Map<String, ASTNode> vars;
    ASTNode body;

    public ASTDef(Map<String, ASTNode> vars, ASTNode r) {
        this.vars = vars;
        this.body = r;
    }

    public int eval(Environment e) {

        int v1;
        e.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            v1 = var.getValue().eval(e);
            e.assoc(var.getKey(), v1);
        }
        int val = body.eval(e);
        e.endScope();
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

    }
}
