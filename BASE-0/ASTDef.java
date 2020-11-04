import java.util.ArrayList;

class ASTDef implements ASTNode {
    String id;
    ASTNode init;
    ASTNode body;

    public ASTDef(String i, ASTNode l, ASTNode r) {
        id = i;
        init = l;
        body = r;
    }

    public int eval(Environment e) {

        int v = init.eval(e);
        e.beginScope();
        e.assoc(id, v);
        int val = body.eval(e);
        e.endScope();
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        
        init.compile(c, e);
        e.beginScope();
        e.assoc(id);

    }
}
